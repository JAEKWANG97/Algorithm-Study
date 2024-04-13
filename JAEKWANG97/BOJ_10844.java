package backjoon.algoStudy.day10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ_10844 {
    static long count = 0;
    static Set<String> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dp(n);
        System.out.println(count);
    }

    private static void dp(int n) {
        long[][] dp = new long[n + 1][10];

        if (n <= 1) {
            count = 9;
            return;
        }
        dp[1][0] = 0;

        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j + 1] % 1000000000;
                } else if (j == 9) {
                    dp[i][j] = dp[i - 1][j - 1] % 1000000000;
                } else {
                    dp[i][j] = (dp[i - 1][j + 1] + dp[i - 1][j - 1]) % 1000000000;

                }

            }
        }

        for (int i = 0; i < 10; i++) {
            count += dp[n][i];
            count = count % 1000000000;
        }

    }

}