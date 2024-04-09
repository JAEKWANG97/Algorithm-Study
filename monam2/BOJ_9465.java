import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9465 { //백준 9465 스티커 - 60분
	/*
	 * 대각선 보기
	 * 1칸, 2칸 후
	 * 3칸부터는 1칸 + 2칸으로 구성 가능
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			int[][] arr = new int[2][100001];
			int[][] dp = new int[2][100001];
			
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dp[0][1] = arr[0][1];
			dp[1][1] = arr[1][1];
			
			for (int j = 2; j <= n; j++) {
				dp[0][j] = Math.max(dp[1][j-1], dp[1][j-2]) + arr[0][j];
				dp[1][j] = Math.max(dp[0][j-1], dp[0][j-2]) + arr[1][j];
			}
			
			System.out.println(Math.max(dp[0][n], dp[1][n]));
			
		}//for T
	}//main
}//class
