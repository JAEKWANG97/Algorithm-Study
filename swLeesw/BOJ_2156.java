import java.util.*;
import java.io.*;

public class BOJ_2156 {

	static int n, arr[], dp[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		dp = new int[n];

		
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		if (n == 1) {
			System.out.println(arr[0]);
			return;
		} else if (n == 2) {
			System.out.println(arr[0] + arr[1]);
			return;
		}
		
		dp[0] = arr[0];
		dp[1] = arr[0] + arr[1];
		dp[2] = Math.max(arr[0] + arr[1], arr[0] + arr[2]);
		dp[2] = Math.max(dp[2], arr[1] + arr[2]);
		for (int i = 3; i < n; i++) {
			dp[i] = Math.max(dp[i - 3] + arr[i - 1], dp[i - 2]) + arr[i];
			dp[i] = Math.max(dp[i - 1], dp[i]);
		}
		
		System.out.println(dp[n - 1]);
	}
	
}
