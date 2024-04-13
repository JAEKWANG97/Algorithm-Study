import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16234 {
    static LinkedList<LinkedList<int[]>> alli = new LinkedList<>();
    static int N, L, R, map[][], now[], nxtY, nxtX;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }
        boolean visited[][];
        Queue<int[]> queue = new LinkedList<>();
        LinkedList<Integer> allisum = new LinkedList<Integer>();
        int allicnt;
        boolean flag;
        for (int day = 0; day <= 2000; day++) {
            queue.clear();
            allisum.clear();
            alli.clear();
            visited = new boolean[N][N];
            allicnt = -1;
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (visited[y][x]) continue;
                    flag = true;
                    visited[y][x] = true;
                    // 나 주변에 연합 될 수 있는지 확인!
                    for (int i = 0; i < 4; i++) {
                        if (0 <= y+dy[i] && y+dy[i] < N && 0 <= x+dx[i] && x+dx[i] < N && !visited[y+dy[i]][x+dx[i]]) {
                            if (L <= Math.abs(map[y][x] - map[y+dy[i]][x+dx[i]]) && Math.abs(map[y][x] - map[y+dy[i]][x+dx[i]]) <= R){
                                flag = false;
                                break;
                            }
                        }
                    }
                    if (flag) continue;
                    queue.add(new int[]{y, x});
                    alli.add(new LinkedList<>());
                    allisum.add(map[y][x]);
                    alli.get(++allicnt).add(new int[]{y, x});
                    while (queue.size() != 0) {
                        now = queue.poll();
                        for (int i = 0; i < 4; i++) {
                            nxtY = now[0]+dy[i];
                            nxtX = now[1]+dx[i];
                            if (0 <= nxtY && nxtY < N && 0 <= nxtX && nxtX < N && !visited[nxtY][nxtX] && L <= Math.abs(map[now[0]][now[1]] - map[nxtY][nxtX]) && Math.abs(map[now[0]][now[1]] - map[nxtY][nxtX]) <= R ) {
                                visited[nxtY][nxtX] = true;
                                int[] tmp = {nxtY, nxtX};
                                alli.get(allicnt).add(tmp);
                                allisum.set(allicnt, allisum.get(allicnt) + map[nxtY][nxtX]);
                                queue.add(tmp);
                            }
                        }
                    }
                }
            }
            if (allicnt < 0) {
                System.out.println(day);
                break;
            }
            // 회차 끝났으니 바꿔주기
            for (int i = 0; i <= allicnt; i++) {
                LinkedList<int[]> al = alli.get(i);
                for (int idx = 0; idx < al.size(); idx++) {
                    now = al.get(idx);
                    map[now[0]][now[1]] = allisum.get(i)/al.size();
                }
            }
            // 출력
//            for (int i = 0; i < N; i++) {
//                System.out.println(Arrays.toString(map[i]));
//            }
//            System.out.println("================================");
        }
    }
}

