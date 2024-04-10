import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_Main_2156 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr= new int[N+2];
		int[][] dp = new int[N+2][2];
		for(int n=2;n<N+2;n++) {
			arr[n] = Integer.parseInt(br.readLine());
		}
		for(int n=2;n<N+2;n++) {
			dp[n][0] = Math.max(dp[n-1][0], dp[n-1][1]);
			dp[n][1] = Math.max(dp[n-1][0], dp[n-2][0]+arr[n-1])+arr[n];
		}
		System.out.println(Math.max(dp[N+1][0], dp[N+1][1]));
	}

}
