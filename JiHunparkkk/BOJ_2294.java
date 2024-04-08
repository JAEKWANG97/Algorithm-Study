import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n, k;
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		int[] money = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			money[i] = Integer.parseInt(br.readLine());
		}

		int[] dp = new int[k + 1];
		Arrays.fill(dp, 1000000);
		dp[0] = 0;

		for (int i = 1; i <= n; i++) {
			for (int j = money[i]; j <= k; j++) {
				dp[j] = Math.min(dp[j], dp[j - money[i]] + 1);
			}
		}

		if (dp[k] == 1000000) {
			System.out.println(-1);
		} else {
			System.out.println(dp[k]);
		}
	}
}
