import java.io.*;
import java.util.StringTokenizer;

public class BOJ_14500 {
    static int N, M, answer;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = new int[]{1, -1, 0, 0};
    static int[] dy = new int[]{0, 0, 1, -1};

    static void helper(int depth, int x, int y, int sum) {
        if (depth == 4) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx > N - 1 || ny < 0 || ny > M - 1 || visited[nx][ny]) {
                continue;
            }
            if (depth == 1) {
                int nnx = nx + dx[i];
                int nny = ny + dy[i];
                if (nnx >= 0 && nnx <= N - 1 && nny >= 0 && nny <= M - 1 && !visited[nnx][nny]) {
                    visited[nnx][nny] = true;
                    visited[nx][ny] = true;
                    helper(depth + 2, nx, ny, sum + map[nx][ny] + map[nnx][nny]);
                    visited[nnx][nny] = false;
                    visited[nx][ny] = false;
                }
            }

            visited[nx][ny] = true;
            helper(depth + 1, nx, ny, sum + map[nx][ny]);
            visited[nx][ny] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        answer = Integer.MIN_VALUE;
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                helper(1, i, j, map[i][j]);
                visited[i][j] = false;
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}