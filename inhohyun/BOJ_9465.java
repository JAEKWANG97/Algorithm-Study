import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9465 {
	public static void main(String[] args) throws  IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int t = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= t; tc++) {
			int n = Integer.parseInt(br.readLine());
			
			int[][] cost = new int[2][n];
			for(int i = 0; i < 2; i ++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					cost[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[][] dp = new int[2][n];
			
			dp[0][0] = cost[0][0];
			dp[1][0] = cost[1][0];
			
			int max = Math.max(dp[0][0], dp[1][0]);
			
            for (int i = 1; i < n; i++) {
                if (i == 1) { // 두번째 라인은 처음 고른 스티커에 영향 받음
                    dp[0][i] = dp[1][0] + cost[0][1];
                    dp[1][i] = dp[0][0] + cost[1][1];
                    max = Math.max(dp[0][i], dp[1][i]);
                    continue;
                }
                // 다음 수를 고르는 것과 고르지않고 날일자로 선택하는 것중 더 큰 값을 비교
                dp[0][i] = Math.max(dp[1][i-1], dp[1][i-2]) + cost[0][i];
                dp[1][i] = Math.max(dp[0][i-1], dp[0][i-2]) + cost[1][i];
                max = Math.max(max, Math.max(dp[0][i], dp[1][i]));
            }
			
			System.out.println(max);
		}
		
		
	}

}
