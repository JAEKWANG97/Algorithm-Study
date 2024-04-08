import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_Main_2294 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int dp[] = new int[K+1];
		Arrays.fill(dp,100000000);
		dp[0] = 0;
		for(int n=0;n<N;n++) {
			int num = Integer.parseInt(br.readLine());
			for(int k=0;k<=K;k++) {
				if(0<=k-num) {
					dp[k] = Math.min(dp[k],dp[k-num]+1);
				}
			}
		}
		System.out.println(dp[K]!=100000000?dp[K]:-1);
	}

}
