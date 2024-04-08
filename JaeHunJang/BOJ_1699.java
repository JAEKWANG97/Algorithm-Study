import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

// 1699. 제곱수의 합 / 80분 
public class BOJ_1699 {
	static StringBuilder sb = new StringBuilder();
	static int N, count, dp[];
	
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	static void init() throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		count = 0;
		
		solve();
	}

	
	static void solve() {
		dp = new int[100001];
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 3;
		
		for(int i = 4; i <= N; i++) {
			dp[i] = i;
			
			for (int j = 0; j*j <= i; j++) {
				dp[i] = Math.min(dp[i], dp[i-j*j] + 1);
			}
		}
		
		sb.append(dp[N]);
	}		
}