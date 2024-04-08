package algorithm_study.day_03_27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11052 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] price = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			price[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] dp = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(i == 1) {
					dp[i][j] = price[i] * j;
				} else {
				
					if(i > j) {
						dp[i][j] = dp[i - 1][j];
					} 
				
					else if(i == j) {
						dp[i][j] = Math.max(dp[i - 1][j], price[i]);
					} 
					
					else {
						dp[i][j] = Math.max(dp[i][j - i] + price[i], dp[i - 1][j]);
					}
				}
			}
		}
		
		System.out.println(dp[N][N]);
		
	}

}
