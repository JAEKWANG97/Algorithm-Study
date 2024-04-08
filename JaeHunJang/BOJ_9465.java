import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

// 9465. 스티커 / 90분 
public class BOJ_9465 {
	static StringBuilder sb = new StringBuilder();
	static int N, map[][], dp[][];
	
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	static void init() throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[2][N+1];
			
			for (int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			solve();
		}
		
	}

	
	static void solve() {
		dp = new int[2][N+1];
		dp[0][1] = map[0][1];
		dp[1][1] = map[1][1];
		
		for (int i = 2; i <= N; i++) {
			dp[0][i] = Math.max(map[0][i] + dp[1][i-1], Math.max(dp[0][i-1], dp[1][i-1]));
			dp[1][i] = Math.max(map[1][i] + dp[0][i-1], Math.max(dp[0][i-1], dp[1][i-1]));
		}
		
		sb.append(Math.max(dp[0][N], dp[1][N])).append("\n");
	}		
}