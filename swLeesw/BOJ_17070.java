import java.util.*;
import java.io.*;

public class BOJ_17070 {

	static int N, arr[][], sol, dp[][][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		dp = new int[N][N][3];//0 : 가 // 1 : 세 // 2 : 대
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 1; i < N - 1; i++) {
			if (arr[0][i] == 1) break; //벽 만나만 브뤸
			dp[0][i][0] = 1;
		}
		
		for (int i = 1; i < N; i++) {
			for (int j = 2; j < N; j++) {
				if (arr[i][j] == 1) continue;
				dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2];
				dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2];
				
				
				if (arr[i - 1][j] == 0 && arr[i][j - 1] == 0) {
					dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];					
				}
			}
		}
		System.out.println(dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2]);
	}
	
}
