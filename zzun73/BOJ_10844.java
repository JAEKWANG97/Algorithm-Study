import java.io.*;

public class BOJ_10844 {
    private static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        long[][] dp = new long[N + 1][10];
        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j == 0) {
                    dp[i][j] = (dp[i - 1][j + 1]);
                } else if (j == 9) {
                    dp[i][j] = (dp[i - 1][j - 1]);
                } else {
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % MOD;
                }
            }
        }

        long answer = 0;
        for (int i = 0; i <= 9; i++) {
            answer += dp[N][i];
        }

        bw.write(String.valueOf(answer % MOD));
        br.close();
        bw.close();
    }
}