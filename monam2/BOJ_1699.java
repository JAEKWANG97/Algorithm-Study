import java.util.Scanner;

public class BOJ_1699 { //백준 1699 제곱수의 합

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] dp = new int[n+1];
		for (int i = 0; i < n+1; i++) {
			dp[i] = i;
		}
		
		for (int i = 2; i <n+1; i++) {
			for (int j = 1; j < i+1; j++) {
				int square = j * j;
				if (square > i) {
					break;
				}
				
				if (dp[i] > dp[i-square] + 1) {
					dp[i] = dp[i-square] + 1;
				}
			}
		}
		System.out.println(dp[n]);
	}//main
}//class
