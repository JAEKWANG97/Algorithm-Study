import java.util.Scanner;

public class BOJ_2156 {

    private static int N;
    private static int[] wines;

    private static int[] dp;

    public static void main(String[] args) {
        init();

        makeDPTable();

        printAnswer();
    }

    private static void makeDPTable() {
        dp[1] = wines[1];

        if (N >= 2) {
            dp[2] = wines[1] + wines[2];
        }

        if (N >= 3) {
            for (int i = 3; i <= N; i++) {
                dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 3] + wines[i - 1] + wines[i], dp[i - 2] + wines[i]));
            }
        }
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        wines = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            wines[i] = sc.nextInt();
        }

        dp = new int[N + 1];
    }

    private static void printAnswer() {
        System.out.println(dp[N]);
    }
}
