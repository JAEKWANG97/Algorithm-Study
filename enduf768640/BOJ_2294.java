import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2294 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N, K;
    private static int[] coins;

    private static int[] dp;

    public static void main(String[] args) throws IOException {
        init();

        makeDPTable();

        printAnswer();
    }

    private static void makeDPTable() {
        for (int coin : coins) {
            for (int i = coin; i <= K; i++) {
                if (dp[i - coin] == Integer.MAX_VALUE) {
                    continue;
                }

                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
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

        coins = Arrays.stream(coins)
                .distinct()
                .sorted()
                .toArray();

        dp = new int[K + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
    }

    private static void printAnswer() {
        if (dp[K] == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        System.out.println(dp[K]);
    }
}
