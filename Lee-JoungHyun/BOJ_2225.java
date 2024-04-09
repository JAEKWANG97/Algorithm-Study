import java.util.*;

public class BOJ_2225 {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		int n = s.nextInt(); // n 입력받음
		int k = s.nextInt(); // k 입력받음
		
		int[][] dp = new int[201][201]; // k번 더해서 n이 되는 경우의 수
		
		for(int i=1;i<=k;i++) {
			dp[i][0]=1;
		}
		for(int i=1;i<=k;i++) {
			for(int j=1;j<=n;j++) {
				dp[i][j] = (dp[i][j-1] + dp[i-1][j])%1000000000; // 1000000000으로 나누는 걸 출력할 때 주면 틀렸다고 뜸.
			}
		}
		System.out.println(dp[k][n]);
	}

}