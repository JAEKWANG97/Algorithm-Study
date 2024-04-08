import java.io.*;
import java.util.*;


public class BOJ_2294 {
	
	static int N, K, arr[], dp[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N];
		dp = new int[K + 1];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		for (int i = 1; i <= K ;i++) {
			dp[i] = 1000000000;
		}
		//dp[n]=min(dp[n],dp[n-동전의 가치]+1)
		
		for (int i = 1; i <= K; i++) {
			for (int j = 0;  j < N; j++) {
				if (i - arr[j] >= 0) {
					dp[i] = Math.min(dp[i], dp[i - arr[j]] + 1);					
				}
			}
		}
		
//		for (int i = 0; i <= K; i++) {
//			System.out.println(dp[i]);
//		}
		if (dp[K] == 1000000000) {
			System.out.println(-1);
			
		} else {
			System.out.println(dp[K]);
			
		}
	}
	

	
	
	
}
