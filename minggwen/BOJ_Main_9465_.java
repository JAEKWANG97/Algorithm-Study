import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Main_9465 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map =  new int[2][N+2];
			int[][] dp = new int[2][N+2];
			for(int i=0;i<2;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=2;j<N+2;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for(int j=2;j<N+2;j++) {
				for(int i=0;i<2;i++) {
					if(i==0) {
						dp[i][j] = Math.max(dp[1][j-1]+map[i][j],dp[1][j-2]+map[i][j]);
					}else {
						dp[i][j] = Math.max(dp[0][j-1]+map[i][j],dp[0][j-2]+map[i][j]);
					}
				}
			}
			System.out.println(Math.max(dp[0][N+1],dp[1][N+1]));
		}

	}

}
