import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 0; test_case < T; test_case++) {
			int n = Integer.parseInt(br.readLine());
			int[][] arr = new int[3][n + 1];

			for (int i = 1; i <= 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int[][] dp = new int[3][n + 1];
			dp[1][1] = arr[1][1];
			dp[2][1] = arr[2][1];
			int answer = 0;
			// 두 칸뒤, 세 칸뒤
			for (int i = 1; i <= n; i++) {
				if (i == 1) {
					dp[1][i] = dp[2][i - 1] + arr[1][i];
					dp[2][i] = dp[1][i - 1] + arr[2][i];
					answer = Math.max(answer, Math.max(dp[1][i], dp[2][i]));
					continue;
				}
				dp[1][i] = Math.max(dp[2][i - 1], dp[2][i - 2]) + arr[1][i];
				dp[2][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + arr[2][i];
				answer = Math.max(answer, Math.max(dp[1][i], dp[2][i]));
			}

			System.out.println(answer);
		}
	}
}
