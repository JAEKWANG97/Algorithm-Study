import java.io.*;
import java.util.*;

public class BOJ_11051 {

	static int[][] dp;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n, k;
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		//nCk n == 1일 떄 종료
		
		dp = new int[n + 1][n + 1];
		
		for (int i = 1; i <= n; i++) {
			dp[i][0] = 1;
			dp[i][i] = 1;
		}
		
		
		System.out.println(combi(n, k));	
	}
	
	static int combi(int n, int k) {
		if (dp[n][k] != 0) return dp[n][k];
		return dp[n][k] = (combi(n - 1, k) + combi(n - 1, k - 1)) % 10007;
	}
}
