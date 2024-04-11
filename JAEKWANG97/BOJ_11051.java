import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int MOD = 10_007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        pascal(n, k);
        sc.close();
    }

    private static void pascal(int n, int k) {
        n++;
        k++;
        int[][] dp = new int[n + 1][n + 1];
        if (n <= 1) {
            System.out.println(n);
            return;
        }

        for (int i = 1; i <= n; i++) {
            dp[i][1] = 1;
            dp[i][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 2; j < n; j++) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % MOD;
            }
        }
        // for (int i = 0; i <= n; i++) {
        // System.out.println(Arrays.toString(dp[i]));
        // }
        System.out.println(dp[n][k]);
    }
}
