package algorithm_study.day_04_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1699 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[100001];
		dp[1] = 1;
		
		for (int i = 2; i <= N; i++) {
			dp[i] = i;
			for (int j = 1; j * j <= i; j++) {
				dp[i] = Math.min(dp[i - (j*j)] + 1, dp[i]);
			}
		}
		System.out.println(dp[N]);
	}
}
