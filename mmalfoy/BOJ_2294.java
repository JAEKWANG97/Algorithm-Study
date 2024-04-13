import java.io.*;
import java.util.*;

public class BOJ_2294 {
	static int[] dp;
	static int INF = 10001;
	static TreeSet<Integer> ts = new TreeSet<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		dp = new int[k + 1];
		Arrays.fill(dp, INF);
		dp[0] = 0;
		
		List<Integer> coins = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			coins.add(Integer.parseInt(br.readLine()));
		}
		
		for (int coin : coins) {
			for (int j = coin; j <= k; j++) {
				dp[j] = Integer.min(dp[j], dp[j - coin] + 1);
			}
		}
		
		System.out.println(dp[k] == INF ? -1 : dp[k]);
	}
	

}
