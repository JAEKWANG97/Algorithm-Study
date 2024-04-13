import java.io.*;
import java.util.*;

public class BOJ_11660 {
	
	static int n, m, arr[][], dp[][];
	
	static int arrSum(int sx, int sy, int ex, int ey) {
		
		int allSum = dp[ey][ex];
		int leftSum = dp[ey][sx - 1];
		int rightSum = dp[sy - 1][ex];
		int middleSum = dp[sy - 1][sx - 1];
		
	
		return allSum - leftSum - rightSum + middleSum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n + 1][n + 1];
		dp = new int[n + 1][n + 1];		
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + arr[i][j];
			}
		}
		//누적합 만들기 끝
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int y1 = Integer.parseInt(st.nextToken());
			int x1 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			
			System.out.println(arrSum(x1, y1, x2, y2));
			
		}
		
	}
	
}
