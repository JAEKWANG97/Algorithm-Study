import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2579 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		System.out.println(solution(n, arr));
	}

	private static int solution(int n, int[] arr) {
		int[] dp = new int[n];

		dp[0] = arr[0];
		if (n == 1) {
			return dp[0];
		}

		dp[1] = arr[0] + arr[1];
		if (n == 2) {
			return dp[1];
		}

		dp[2] = arr[2] + Math.max(arr[0], arr[1]);
		if (n == 3) {
			return dp[2];
		}

		for (int i = 3; i < n; i++) {
			dp[i] = arr[i] + Math.max(dp[i - 3] + arr[i - 1], dp[i - 2]);
		}
		return dp[n - 1];
	}
}
