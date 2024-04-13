import java.io.*;
import java.util.*;

public class BOJ_1890 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine().trim());
		int[][] G = new int[N][N];

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int m = 0; m < N; m++) {
				G[n][m] = Integer.parseInt(st.nextToken());
			}
		}

		long[][] dp = new long[N][N];
		dp[0][0] = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
//				if (dp[i][j] == 0) {
//					continue;
//				}
				if (G[i][j] == 0) {
					break;
				}
				
				if (G[i][j] + j < N) {
					dp[i][G[i][j] + j] += dp[i][j];
				}

				if (G[i][j] + i < N) {
					dp[G[i][j] + i][j] += dp[i][j];
				}

			}
		}
		System.out.println(dp[N - 1][N - 1]);
	}
}
