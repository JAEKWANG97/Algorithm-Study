import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2293 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N, K;
    private static int[] coins;

    private static int[] dp;

    public static void main(String[] args) throws IOException {
        init();

        check();

        printAnswer();
    }

    private static void check() {
        for (int k = 1; k <= K; k++) {
            if (k % coins[0] == 0) {
                dp[k] = 1;
            }
        }

        for (int i = 1; i < N; i++) {
            int coin = coins[i];

            if (K < coin) {
                return;
            }

            dp[coin]++;

            for (int k = coin + 1; k <= K; k++) {
                dp[k] += dp[k - coin];
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(coins);

        dp = new int[K + 1];
    }

    private static void printAnswer() {
        System.out.println(dp[K]);
    }
}
