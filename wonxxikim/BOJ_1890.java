import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] delta = {{0,1},{1,0}};
	static int N;
	static int[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i = 0 ; i<N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j<N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		long[][] dp = new long[N][N];
		dp[0][0] = 1;

		for(int i = 0 ; i<N ; i++) {
			for(int j = 0 ; j<N ; j++) {
				int jump = map[i][j];
				if(jump==0) continue;
				for(int d=0; d<2 ;d++) {
					int nr = i+delta[d][0]*jump;
					int nc = j+delta[d][1]*jump;
					if(nr<N && nc<N) {
						dp[nr][nc]+=dp[i][j];
					}
				}
			}
		}
		
		System.out.println(dp[N-1][N-1]);

	}

}
