package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_21608 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int ans = 0;
        int[][] map = new int[N][N];
        int[][] table = new int[N * N + 1][2];
        int[][] answerMap = new int[N][N];
        // 내가 좋아하는 애들
        LinkedList<Integer>[] list = new LinkedList[N * N + 1];
        for (int i = 0; i < N * N + 1; i++) {
            list[i] = new LinkedList<>();
            Arrays.fill(table[i], -1);
        }

        // 첫 입력은 항상 1,1에 넣기
        st = new StringTokenizer(br.readLine());
        int tmp = Integer.parseInt(st.nextToken());
        map[1][1] = tmp;
        table[tmp][0] = 1;
        table[tmp][1] = 1;
        for (int j = 0; j < 4; j++) {
            int match = Integer.parseInt(st.nextToken());
            list[tmp].add(match);
        }
        for (int i = 1; i < N * N; i++) {
            int graph[] = new int[4];
            st = new StringTokenizer(br.readLine());
            int my = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 4; j++) {
                int match = Integer.parseInt(st.nextToken());
                graph[j] = match;
                list[my].add(match);
            }
            // 우선순위 1, 2 같이 처리
            int[] now = aroundFav(map, graph, table);
            // 우선순위 3 처리
            if (now == null) {
                now = new int[2];
                for (int x = 0; x < N; x++) {
                    for (int y = 0; y < N; y++) {
                        if (map[y][x] == 0) {
                            now[0] = y;
                            now[1] = x;
                            x = N;
                            break;
                        }
                    }
                }
            }
            // 다 처리했으니 now에 배치
            table[my][0] = now[0];
            table[my][1] = now[1];

            map[now[0]][now[1]] = my;
        }
        // 점수 계산
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    if (isIn(ny, nx, N)) {
                        //System.out.println(map[y][x]);
                        if (list[map[y][x]].contains(map[ny][nx])) {
                            answerMap[y][x]++;
                        }
                    }
                }
            }
        }

        System.out.println("배치");
        for(int i = 0; i < N; i++)
            System.out.println(Arrays.toString(map[i]));
        System.out.println("결과");
        for(int i = 0; i < N; i++)
            System.out.println(Arrays.toString(answerMap[i]));


        int answer = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                int temp = answerMap[y][x];
                if (temp == 4) answer += 1000;
                else if (temp == 3) answer += 100;
                else if (temp == 2) answer += 10;
                else if (temp == 1) answer += 1;
            }
        }
        System.out.println(answer);
    }
    // 우선순위 1 처리
    private static int[] aroundFav(int[][] map, int[] fav, int[][] table) {
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};
        int N = map.length;
        LinkedList<int[]> list = new LinkedList<>();
        // 좋아하는 친구 순회
        for (int i = 0; i < 4; i++) {
            if (table[fav[i]][0] != -1) {
                for (int d1 = 0; d1 < 4; d1++) {
                    int searchY = table[fav[i]][0] + dy[d1];
                    int searchX = table[fav[i]][1] + dx[d1];
                    int arroundFav = 0, arroundEmp = 0;
                    if (isIn(searchY, searchX, N) && map[searchY][searchX] == 0) {
                        // 주변 확인
                        for (int d2 = 0; d2 < 4; d2++) {
                            int aroundY = searchY + dy[d2];
                            int aroundX = searchX + dx[d2];
                            if (isIn(aroundY, aroundX, N)) {
                                if (map[aroundY][aroundX] == 0)
                                    arroundEmp++;
                                else {
                                    for (int j = 0; j < 4; j++) {
                                        if (fav[j] == map[aroundY][aroundX])
                                            arroundFav++;
                                    }
                                }
                            }
                        }
                        // 주변 확인 완료
                        list.add(new int[]{arroundFav, arroundEmp, searchX, searchY});
                    }

                }
            }
        }
        if (list.size() == 0) return null;

        list.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0])
                    return o2[0] - o1[0];
                else if (o2[1] != o1[1])
                    return o2[1] - o1[1];
                else if (o1[3] != o2[3])
                    return o1[3] - o2[3];
                return o1[2] - o2[2];
            }
        });
        return new int[]{list.get(0)[3], list.get(0)[2]};
    }
    private static boolean isIn(int y, int x, int N) {
        if (0 <= y && y < N && 0 <= x && x < N)
            return true;
        return false;
    }
}