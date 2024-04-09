import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Main_2225 {
	static int mod = 1000000000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K  = Integer.parseInt(st.nextToken());
		int dp[][] = new int[N+1][K+1];
		for(int k=0;k<=K;k++)dp[0][k]=1;
		for(int n=0;n<=N;n++)dp[n][1]=1;
		for(int n=1;n<=N;n++) {
			for(int k=1;k<=K;k++) {
				dp[n][k] = (dp[n-1][k]+dp[n][k-1])%mod;
			}
		}
		System.out.println(dp[N][K]);
	}

}
