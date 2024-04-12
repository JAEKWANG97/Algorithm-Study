import java.util.Scanner;

public class BOJ_11051 {

    private static final int MOD = 10007;

    private static int N, K;

    private static int[][] dp;

    public static void main(String[] args) {
        init();

        makeDPTable();

        printAnswer();
    }

    private static void makeDPTable() {
        dp = new int[N + 1][N + 1];

        dp[1][0] = 1;
        dp[1][1] = 1;

        for (int i = 2; i <= N; i++) {
            dp[i][0] = 1;

            for (int j = 1; j < i; j++) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % MOD;
            }

            dp[i][i] = 1;
        }
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
    }

    private static void printAnswer() {
        System.out.println(dp[N][K] % MOD);
    }
}
