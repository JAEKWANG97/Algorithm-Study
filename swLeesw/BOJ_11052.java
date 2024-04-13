import java.util.*;
import java.io.*;

public class BOJ_11052 {
	
	static int n, arr[], dp[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[n + 1];
		dp = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= n; i++) {
			dp[1][i] = dp[1][i - 1] + arr[1];
		}
		
		for (int i = 2; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				dp[i][j] = dp[i - 1][j];
				
				if (i <= j) { //j부턴
					int sum = 0;
					int cnt = 1;
					for (int k = i; k <= j; k += i) {//i부터 j까지
						sum += arr[i];
						dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - i * cnt] + sum);
						cnt++;
					}

				}
			}
		}
		
		
		int sol = 0;
		
		for (int i = 1; i <= n; i++) {
			sol = Math.max(sol, dp[n][i]);
		}
		System.out.println(sol);
	}
	
}
