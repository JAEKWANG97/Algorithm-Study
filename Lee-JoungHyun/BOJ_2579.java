package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][] dp = new long[N][2];
        int tmp = Integer.parseInt(br.readLine());
        dp[0][0] = tmp;
        // 예외처리!!!!!
        if (N > 1) {
            tmp = Integer.parseInt(br.readLine());
            dp[1][0] = tmp;
            dp[1][1] = tmp + dp[0][0];
        }
        for (int i = 2; i < N; i++) {
            tmp = Integer.parseInt(br.readLine());
            dp[i][0] = Math.max(dp[i-2][0], dp[i-2][1]) + tmp;
            dp[i][1] = dp[i-1][0] + tmp;
        }
        System.out.println(Arrays.stream(dp[N-1]).max().getAsLong());
    }
}
