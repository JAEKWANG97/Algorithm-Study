package swexpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1949 {
    static int N, K, maxLen;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            visited = new boolean[N][N];
            maxLen = 0;

            int highest = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    highest = Math.max(highest, map[i][j]);
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == highest) {
                        dfs(i, j, 1, false);
                    }
                }
            }

            System.out.println("#" + tc + " " + maxLen);
        }
    }

    static void dfs(int x, int y, int len, boolean cut) {
        maxLen = Math.max(maxLen, len);
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                if (map[nx][ny] < map[x][y]) {
                    dfs(nx, ny, len + 1, cut);
                } else if (!cut && map[nx][ny] - K < map[x][y]) {
                    int original = map[nx][ny];
                    map[nx][ny] = map[x][y] - 1;
                    dfs(nx, ny, len + 1, true);
                    map[nx][ny] = original;
                }
            }
        }

        visited[x][y] = false;
    }
}
