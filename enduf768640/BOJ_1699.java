import java.util.Scanner;

public class BOJ_1699 {

    private static int N;

    private static int[] dp;

    public static void main(String[] args) {
        init();

        makeDPTable();

        printAnswer();
    }

    private static void makeDPTable() {
        for (int i = 1; i <= N; i++) {
            dp[i] = i;
        }

        for (int i = 2; i <= Math.sqrt(N); i++) {
            for (int j = i * i; j <= N; j++) {
                dp[j] = Math.min(dp[j], dp[j - (i * i)] + 1);
            }
        }
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        dp = new int[N + 1];
    }

    private static void printAnswer() {
        System.out.println(dp[N]);
    }
}
