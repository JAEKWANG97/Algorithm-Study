import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2225 {

    private static final int MOD = 1_000_000_000;

    private static int N, K;

    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        init();

        makeDPTable();

        printAnswer();
    }

    private static void makeDPTable() {
        for (int n = 0; n <= N; n++) {
            dp[1][n] = 1;
        }

        for (int k = 2; k <= K; k++) {
            for (int n = 0; n <= N; n++) {
                for (int i = 0; i <= n; i++) {
                    dp[k][n] += dp[k - 1][i];
                    dp[k][n] %= MOD;
                }
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[K + 1][N + 1];
    }

    private static void printAnswer() {
        System.out.println(dp[K][N] % MOD);
    }
}
