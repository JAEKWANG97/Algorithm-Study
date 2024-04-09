import java.io.*;
import java.util.*;

public class BOJ_9465 {

	
	static int t, n, arr[][], dp[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		t = Integer.parseInt(br.readLine());
		
		while (t-- > 0) {
			n = Integer.parseInt(br.readLine());
			
			arr = new int[2][n];
			dp = new int[2][n];
			
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			if (n == 1) {
				int sol = 0;
				sol = Math.max(sol, arr[0][0]);
				sol = Math.max(sol, arr[1][0]);
				System.out.println(sol);
				continue;
			}
			
			dp[0][0] = arr[0][0];
			dp[1][0] = arr[1][0];
			dp[0][1] = arr[1][0] + arr[0][1];
			dp[1][1] = arr[0][0] + arr[1][1];
			
			for (int j = 2; j < n; j++) {
				for (int i = 0; i < 2; i++) {
					dp[i][j] = dp[(i + 1) % 2][j - 1] + arr[i][j];
					dp[i][j] = Math.max(dp[i][j], dp[i][j - 2] + arr[i][j]);
					dp[i][j] = Math.max(dp[i][j], dp[(i + 1) % 2][j - 2] + arr[i][j]);
				}
			}
			
			int sol = 0;
			sol = Math.max(sol, dp[0][n - 1]);
			sol = Math.max(sol, dp[1][n - 1]);
			
			
			System.out.println(sol);
			
		}
		
		
	}
	
}
