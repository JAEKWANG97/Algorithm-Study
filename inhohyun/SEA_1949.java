import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class SEA_1949 {
    static class xy {
        int x, y;

        xy(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, k, max;
    static int[] dx = { 0, 0, -1, 1 }, dy = { -1, 1, 0, 0 };
    static boolean visited[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            max = 0;
            int[][] map = new int[n][n];
            // map 입력 받기
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 출발할 좌표 저장하기
            List<xy> start = new LinkedList<>();
            int maxN = SearhMax(map);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (maxN == map[i][j]) {
                        start.add(new xy(i, j));
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int d = 0; d < k; d++) {
                        map[i][j] -= 1;
                        // start에 있는 값들로 탐색 하기
                        for (int a = 0; a < start.size(); a++) {
                            visited = new boolean[n][n];

                            dfs(start.get(a).x, start.get(a).y, 1, map);
                        }
                    }


                    map[i][j] += k;

                }
            }

            System.out.println("#" + tc + " " + max);
        }

    }

    // 가장 긴 등산로의 길이 찾기
    static void dfs(int x, int y, int len, int[][] map) {
        max = Math.max(max, len);
//		print(map);
        for (int d = 0; d < 4; d++) {
            int di = x + dx[d];
            int dj = y + dy[d];
            if (di < 0 || di >= n || dj < 0 || dj >= n)
                continue;

            // 맵 복사해서 사용하기
            if (map[x][y] > map[di][dj] && !visited[di][dj]) { // 갈 수 있다면
                visited[di][dj] = true;
                dfs(di, dj, len + 1, map);
                visited[di][dj] = false;
            }
            // 가려는 곳이 더 낮지 않아서 갈 수 없다면
//			else if(copyMap[x][y] <= copyMap[di][dj] && !visited[di][dj]) {
//
//				//만약 땅을 파면 갈 수 있는지 체크
//				if(!used && copyMap[x][y] > copyMap[di][dj] - k) {
//					//땅을 파는 부분에서 반례가 있나
//
//					for(int dig = 1; dig <= k; dig++) {
//						visited[di][dj] = true;
//						copyMap[di][dj] = copyMap[x][y] - dig; // 1부터 k번만큼까지 파주기
//						dfs(di,dj,len+1, copyMap, true);
//
//						visited[di][dj] = false;
//					}
//					copyMap[di][dj] = copyMap[x][y] + k; //다시 메꾸기?
//
//				}
//
//			}
        }
    }

    static int SearhMax(int[][] map) {
        int m = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] > m) { // 호출부에 조건 추가하기

                    m = map[i][j];
                }
            }
        }
        return m;
    }

    // map 찍어보기
    static void print(int[][] map) {
        System.out.println("---------------------");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}
