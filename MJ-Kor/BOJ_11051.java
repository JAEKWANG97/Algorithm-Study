package algorithm_study.april.day_04_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11051 {
	
	private static int mod = 10007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[N + 1][N + 1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				if(j == 0 || j == i) {
					dp[i][j] = 1;
					if(i == N && j == K) {
						System.out.println(dp[i][j]);
						System.exit(0);
					}
					continue;
				} 
				
				dp[i][j] = ((dp[i - 1][j - 1])%mod + (dp[i - 1][j])%mod)%mod;
				if(i == N && j == K) {
					System.out.println(dp[i][j]);
					System.exit(0);
				}
			}
		}
	}

}
