import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 2294. 동전 2 / 90분
public class BOJ_2294 {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	static void init() throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int coins[] = new int[N];
		for (int i = 0; i < N; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(coins);
		
		int dp[] = new int[K+1]; // dp 배열은 각 index가 k원를 나타내며 k원을 만들 수 있는 동전 수를 저장
        Arrays.fill(dp, 2*K);
		dp[0] = 0;
		
		for (int i = 0; i < N; i++) {
			for (int k = coins[i]; k <= K; k++) {
				dp[k] = Math.min(dp[k-coins[i]]+1, dp[k]);
			}
		}

		if (dp[K] == 2*K) sb.append(-1);
		else sb.append(dp[K]);
	}

}