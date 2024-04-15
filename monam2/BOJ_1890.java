import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1890 { // 백준 1890 점프 - 60분

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n][n];
		long[][] dp = new long[n][n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp[0][0] = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (dp[i][j] > 0 && arr[i][j] > 0) {
					int jump = arr[i][j];
					if (j + jump < n) {
						dp[i][j+jump] += dp[i][j];
					}
					if (i + jump < n) {
						dp[i+jump][j] += dp[i][j];
					}
				}
			}
		}
		
		System.out.println(dp[n-1][n-1]);
		
	}//main
}//class
