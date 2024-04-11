import java.util.Scanner;

public class BOJ_11051 { //백준 11051 이항 계수2 - 30분
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		int[][] dp = new int[1001][1001];
		for (int i = 0; i < n+1; i++) {
			for (int j = 0; j < i+1; j++) {
				if (i==j || j==0) dp[i][j] = 1;
				else dp[i][j] = (dp[i-1][j-1] + dp[i-1][j])%10007;
			}
		}
		
		System.out.println(dp[n][k]);
	}//main
}//class
