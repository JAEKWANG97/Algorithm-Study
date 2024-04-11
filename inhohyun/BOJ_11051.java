import java.util.Scanner;

public class BOJ_11051 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		long[][] dp = new long[n+1][1001];

		for(int i=0; i<=n; i++){
			for(int j=0; j<=i; j++){
				if(j == 0)
					dp[i][j] = 1;
				else
					dp[i][j] = (dp[i-1][j-1] + dp[i-1][j]) % 10007;
			}
		}
		
		System.out.println(dp[n][k] % 10007);
	}
}
