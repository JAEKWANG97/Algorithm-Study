import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_Main_11060 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N= Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		int[] dp = new int[N+1];
		Arrays.fill(dp, Integer.MAX_VALUE-1);
		st = new StringTokenizer(br.readLine());
		for(int n=1;n<=N;n++) {
			arr[n] = Integer.parseInt(st.nextToken());
		}
		dp[1] = 0;
		for(int n=1;n<=N;n++) {
			if(arr[n]==0)continue;
			for(int k=n+1; k<=Math.min(n+arr[n], N);k++) {
				if(dp[k]>dp[n]+1) dp[k] = dp[n]+1;
			}
		}
		System.out.println(dp[N]!=Integer.MAX_VALUE-1?dp[N]:-1);
	}

}
