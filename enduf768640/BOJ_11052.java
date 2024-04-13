import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11052 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int[] prices;

    private static int[] dp;

    public static void main(String[] args) throws IOException {
        init();

        dp();

        printAnswer();
    }

    private static void dp() {
        dp[0] = prices[0];
        for (int i = 1; i < N; i++) {
            int temp = 0;

            for (int j = 0; j < i; j++) {
                temp = Math.max(temp, dp[j] + dp[i - j - 1]);
            }

            dp[i] = Math.max(temp, prices[i]);
        }
    }

    private static void printAnswer() {
        System.out.println(dp[N - 1]);
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        prices = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            prices[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N];
    }
}
