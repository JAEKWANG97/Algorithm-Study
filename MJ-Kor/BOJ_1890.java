package algorithm_study.april.day_04_13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1890 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());
		int list[][] = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				list[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		long dp[][] = new long[N + 1][N + 1];
		dp[1][1] = 1;

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				int next = list[i][j];
				if (next == 0)
					break;
				if (j + next <= N)
					dp[i][j + next] += dp[i][j];
				if (i + next <= N)
					dp[i + next][j] += dp[i][j];
			}
		}
		System.out.println(dp[N][N]);
	}
}