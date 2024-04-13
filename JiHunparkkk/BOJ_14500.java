import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int n, m, answer;
    private static int[][] board;
    private static boolean[][] visited;
    private static int[][] dxy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, board[i][j]);
                visited[i][j] = false;
            }
        }

        System.out.println(answer);
    }

    private static void dfs(int x, int y, int cnt, int sum) {
        if (cnt == 4) {
            answer = Math.max(sum, answer);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dxy[i][0];
            int ny = y + dxy[i][1];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                continue;
            }

            if (!visited[nx][ny]) {
                if (cnt == 2) {
                    visited[nx][ny] = true;
                    dfs(x, y, cnt + 1, sum + board[nx][ny]);
                    visited[nx][ny] = false;
                }

                visited[nx][ny] = true;
                dfs(nx, ny, cnt + 1, sum + board[nx][ny]);
                visited[nx][ny] = false;
            }
        }
    }
}

