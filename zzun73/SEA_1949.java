import java.io.*;
import java.util.*;

class SEA_1949 {
    static int T, N, K, topValue, answer;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = new int[]{0, 0, 1, -1};
    static int[] dy = new int[]{1, -1, 0, 0};

    static void helper() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == topValue) {
                    dfs(1, i, j, 1);
                }
            }
        }
    }

    static void dfs(int depth, int x, int y, int kCount) {
        visited[x][y] = true;
        answer = Math.max(answer, depth);
        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            //범위 밖 or 이미 방문
            if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1 || visited[nx][ny]) {
                continue;
            }

            if (map[x][y] > map[nx][ny]) {
                dfs(depth + 1, nx, ny, kCount);

            } else if (map[x][y] <= map[nx][ny] && kCount > 0 && map[nx][ny] - map[x][y] < K) {
                int val = map[nx][ny];
                map[nx][ny] = map[x][y] - 1;
                dfs(depth + 1, nx, ny, kCount - 1);
                map[nx][ny] = val;
            }
        }
        visited[x][y] = false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            answer = 0;
            map = new int[N][N];
            visited = new boolean[N][N];
            topValue = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    topValue = Math.max(topValue, map[i][j]);
                }
            }
            helper();
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}