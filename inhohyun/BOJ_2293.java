import java.util.Scanner;

public class BOJ_2293 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		int[] coin = new int[n+1];
		for(int i = 1; i <= n; i ++) {
			coin[i] = sc.nextInt();
		}
		
		//coin을 가지고 k를 만들 수 있는 경우의 수
		int[] dp = new int[k+1];
		//bottom up
		dp[0] = 1;
		for(int i = 1; i <= n; i++) {
			for(int j = coin[i]; j <= k; j++) {
				//j번째 경우의 수는 j-coin번쨰
				dp[j] += dp[j - coin[i]];
			}
		}
		
		System.out.println(dp[k]);
	}

}
