import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Main_1890 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int map[][] = new int[N][N];
		long dp[][] = new long[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp[0][0] = 1;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				int k = map[i][j];
				if(k==0) break;
				if(i+k<N)dp[i+k][j]+=dp[i][j];
				if(j+k<N)dp[i][j+k]+=dp[i][j];
			}
		}
		System.out.println(dp[N-1][N-1]);

	}

}
