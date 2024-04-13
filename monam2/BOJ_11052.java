import java.util.Scanner;

public class BOJ_11052 { //백준 11502 카드 구매하기 - 70분
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n+1];
		for (int i = 1; i < n+1; i++) {
			arr[i] = sc.nextInt();
		}
		int[] dp = new int[1001];
		
		for (int i = 1; i < n+1; i++) {
			for (int j = 1; j < i+1; j++) {
				dp[i] = Math.max(dp[i], dp[i-j]+arr[j]);
			}
		}
		
		System.out.println(dp[n]);
	}//main
}//class
