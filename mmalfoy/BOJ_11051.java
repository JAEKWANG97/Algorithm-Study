import java.io.*;
import java.util.*;
public class BOJ_11051 {
	static int[][] dp = new int[1001][1001];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N  = Integer.parseInt(st.nextToken());
		int K  = Integer.parseInt(st.nextToken());
		dp[1][0] = 1;
		dp[1][1] = 1;
		System.out.println(nCk(N, K = Integer.min(K, N - K)) % 10007);
	}
	
	static int nCk(int N, int K) {
		if (K == 0 || N == K) {
			return 1;
		}
		
		if (dp[N][K] == 0) {
			dp[N][K] = nCk(N - 1, K -1) % 10007 + nCk(N - 1, K) % 10007;
		}
		
		return dp[N][K];
	}
}
