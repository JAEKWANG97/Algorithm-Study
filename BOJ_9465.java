package algorithm_study.april.day_04_16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9465 {

	private static int[][] stickers;
	private static int[][] dp;
	private static int n;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			n = Integer.parseInt(br.readLine());
			stickers = new int[2][n + 1];
			dp = new int[2][n + 1];
			for (int r = 0; r < 2; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 1; c <= n; c++) {
					stickers[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			dp[0][0] = 0;
			dp[1][0] = 0;
			dp[0][1] = stickers[0][1];
			dp[1][1] = stickers[1][1];
			
			for (int i = 2; i <= n; i++) {
				dp[0][i] = Math.max(dp[1][i - 1] + stickers[0][i], dp[1][i - 2] + stickers[0][i]);
				dp[1][i] = Math.max(dp[0][i - 1] + stickers[1][i], dp[0][i - 2] + stickers[1][i]);
			}
			
			System.out.println(Math.max(dp[0][n], dp[1][n]));
		}
	}

}

