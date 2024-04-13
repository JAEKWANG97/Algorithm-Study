import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution(n, board));
    }

    private static long solution(int n, int[][] board) {
        long[][] dp = new long[n][n];
        dp[0][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0 || board[i][j] == 0) {
                    continue;
                }
                int jump = board[i][j];

                if (j + jump < n) {
                    dp[i][j + jump] += dp[i][j];
                }
                if (i + jump < n) {
                    dp[i + jump][j] += dp[i][j];
                }
            }
        }

        return dp[n - 1][n - 1];
    }
}
