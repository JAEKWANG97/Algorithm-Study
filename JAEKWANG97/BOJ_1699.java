import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();

        dp(n);

    }

    private static void dp(int n) {
        int[] dp = new int[n + 1];

        dp[1] = 1;
        dp[0] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = i;
            for (int j = 0; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], 1 + dp[i - j * j]);
            }
        }

        System.out.println(dp[n]);
    }
}
