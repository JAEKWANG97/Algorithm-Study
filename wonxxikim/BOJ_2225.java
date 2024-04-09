
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		long mod = 1000000000;
		
		long[][] dp = new long[N+1][K+1];
		for(int i = 0 ; i<N+1 ; i++) {
			dp[i][1] = 1;
		}
		for(int i = 1; i<K+1; i++) {
			dp[0][i]= 1;
		}
		
		for(int i = 1 ; i<=N ; i++) {
			for(int j = 2; j<=K ; j++) {
				dp[i][j] = (dp[i][j-1]+dp[i-1][j])%mod;
			}
		}
		System.out.println(dp[N][K]%mod);
	}

}

