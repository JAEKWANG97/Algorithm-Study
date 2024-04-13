import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2579 { //백준 2579 계단 오르기 - 72분

	//0번째 : 이전에 (1번 밟은 계단, 2번 밟은 계단) 중 더 큰거
	//1번째 : 이전에 안밟은거
	//2번째 : 이전에 한번 밟았던거

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[][] dp = new int[301][3];

		for (int i = 1; i < n+1; i++) {
			int step = Integer.parseInt(br.readLine());
			dp[i][0] = Math.max(dp[i-1][1], dp[i-1][2]);
			dp[i][1] = dp[i-1][0] + step;
			dp[i][2] = dp[i-1][1] + step;
		}
		//마지막 계단을 안밟으면 안되므로, max 비교에서 제외해야 함
		System.out.println(Math.max(dp[n][1], dp[n][2]));
	}//main
}//class
