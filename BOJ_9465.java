import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1 ; tc<T+1 ; tc++) {
			int n = Integer.parseInt(br.readLine());
			int[][] score = new int[2][n+1];
			for(int i = 0 ; i<2 ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 1 ; j<n+1 ; j++) {
					score[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int[][] dp = new int[2][n+1];
			
			dp[0][1] = score[0][1];
			dp[1][1] = score[1][1];
			
			for(int i = 2 ; i<n+1; i++) {
				dp[0][i] = Math.max(dp[1][i-1], dp[1][i-2])+score[0][i];
				dp[1][i] = Math.max(dp[0][i-1], dp[0][i-2]) +score[1][i];
			}
			
			System.out.println(Math.max(dp[0][n],dp[1][n]));
		}
		
	}

}
