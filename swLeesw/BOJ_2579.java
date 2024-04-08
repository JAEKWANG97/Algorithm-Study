import java.util.*;
import java.io.*;

public class BOJ_2579 {
	
	static int n, arr[], dp[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr = new int[n];
		dp = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		dp[0] = arr[0]; //1번 계단
		dp[1] = arr[1] + arr[0];//2번 계단 최댓값
		dp[2] = Math.max(arr[0] + arr[2], arr[1] + arr[2]);//3번 계단 최댓값 (1, 3), (2, 3)중 최선
		
		for (int i = 3; i < n; i++) {
			//i번째 계단 = max(i - 3번째 누적 합 + i - 1번째 계단 + i번째 계단
			//, i - 2번째 누적 합 + i번째 계단
			dp[i] = Math.max(dp[i - 3] + arr[i - 1] + arr[i], dp[i - 2] + arr[i]);
		}
		System.out.println(dp[n - 1]);
	}
	
}
