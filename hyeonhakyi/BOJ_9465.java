package ex0408;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9465 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		
		String[] str;
		
		for(int tc = 0; tc < t; tc++) {
			int n = Integer.parseInt(br.readLine());
			int[][] arr = new int[n+1][2];
			int[][] dp = new int[n+1][2];
			
			for(int i = 0; i < 2; i++) {
				str = br.readLine().split(" ");
				for(int j = 1; j <= n; j++) {
					arr[j][i] = Integer.parseInt(str[j-1]);
				}
			}
			
			dp[1][0] = arr[1][0];
			dp[1][1] = arr[1][1];
			
			for(int i = 2; i <= n; i++) {
				dp[i][0] = Math.max(dp[i-1][1], dp[i-2][1]) + arr[i][0];
				dp[i][1] = Math.max(dp[i-1][0], dp[i-2][0]) + arr[i][1];
			}
			System.out.println(Math.max(dp[n][1], dp[n][0]));
		}//testCase end
	}//main end
}//class end
