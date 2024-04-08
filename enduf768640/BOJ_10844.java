import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class BOJ_10844 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static BigInteger[][] dp;

    private static BigInteger answer = BigInteger.ZERO;

    public static void main(String[] args) throws IOException {
        init();
        countNumber();
        printAnswer();
    }

    private static void countNumber() {
        dp[1][0] = BigInteger.ZERO;
        for (int end = 1; end <= 9; end++) {
            dp[1][end] = BigInteger.ONE;
        }

        for (int length = 2; length <= N; length++) {
            dp[length][0] = dp[length - 1][1];

            for (int end = 1; end <= 8; end++) {
                dp[length][end] = dp[length - 1][end - 1].add(dp[length - 1][end + 1]);
            }

            dp[length][9] = dp[length - 1][8];
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        dp = new BigInteger[N + 1][10];
    }

    private static void printAnswer() {
        for (int i = 0; i <= 9; i++) {
            answer = answer.add(dp[N][i]);
        }

        System.out.println(answer.remainder(BigInteger.valueOf(1000000000)));
    }
}
