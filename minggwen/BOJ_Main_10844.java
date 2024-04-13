import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_Main_10844 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		long[] dp = new long[10];
		for(int i=1; i<10;i++) dp[i] = 1;
		long[] newDp = new long[10];
		for(int i=1;i<N;i++) {
			for(int n=0;n<10;n++) {
				if(n==0) newDp[0] = dp[1]%1000000000;
				else if(n==9) newDp[9] = dp[8]%1000000000;
				else newDp[n] = (dp[n-1]+dp[n+1])%1000000000;
			}
			for(int idx = 0; idx<10;idx++) {
				dp[idx] = newDp[idx];
			}
		}
		long result = 0;
		for(long num : dp) {
			result+=num;
			result%=1000000000;
		}
		System.out.println(result);
	}

}
