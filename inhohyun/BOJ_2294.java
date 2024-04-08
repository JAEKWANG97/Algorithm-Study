import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2294 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] coins = new int[n+1];
		for(int i = 1; i <=n; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		} // 코인 입력받기
		
		
		int[] dp = new int[k+1];
		for(int i = 1; i <= k; i++) {
			dp[i] = Integer.MAX_VALUE-1; // 아래 연산에서 Integer 범위를 벗어날 수 있으니 -1
		}
		
		for(int i = 1; i <= n; i++) {
			for(int j = coins[i]; j <=k; j++) {
				dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1); // 경우의 수 최솟값 갱신
			}
		}
		
		if(dp[k] == Integer.MAX_VALUE-1) {
			System.out.println(-1);
		} else {
			System.out.println(dp[k]);
		
		}
		
		
	}
}
