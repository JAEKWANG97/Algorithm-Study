import java.util.Arrays;
import java.util.Scanner;

public class BOJ_10844 { //백준 10844 쉬운 계단 수 - 40분
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long mod = 1000000000;
		long[][] dp = new long[n+1][10];
		Arrays.fill(dp[1], 1);
		dp[1][0] = 0;

		for (int i = 2; i <= n; i++) {
			for (int j = 0; j < 10; j++) {
				if (j==0) {
					dp[i][j] = dp[i-1][1] % mod;
				} else if (j==9) {
					dp[i][j] = dp[i-1][8] % mod;
				} else {
					dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % mod;
				}
			}
		}
//		for (int[] is : dp) {
//			System.out.println(Arrays.toString(is));
//		}
		
		long sum = 0;
		for (int i = 0; i < 10; i++) {
			sum += dp[n][i];
		}
		System.out.println(sum%1000000000);
	}//main
}//class
