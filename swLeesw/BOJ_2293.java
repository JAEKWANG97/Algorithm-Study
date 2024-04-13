import java.util.*;
import java.io.*;

public class BOJ_2293 {
	
	static int n, k, dp[], arr[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new int[n];
		dp = new int[k + 1];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 1; j <= k; j++) {
				if (j - arr[i] > 0) {
					dp[j] = dp[j - arr[i]] + dp[j];
				} else if (j - arr[i] == 0) {
					dp[j]++;
				}
			}
			
		}
		
		
		System.out.println(dp[k]);
	}
	
}
