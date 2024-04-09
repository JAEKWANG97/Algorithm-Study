import java.util.*;
import java.io.*;

public class BOJ_9465 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 테스트 케이스 수
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=0; tc<T; tc++) {
			// 카드 열 수
			int N = Integer.parseInt(br.readLine());
			// 카드 점수 입력
			int[][] cards = new int[2][N + 1];
			for(int i=0; i<2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for(int j=1; j<=N; j++) {
					cards[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[][] dp = new int[2][N + 1];
			// 1번째는 1번째 열 값으로 초기화
			dp[0][1] = cards[0][1];
			dp[1][1] = cards[1][1];
			// N 번째 열 까지 최대 점수를 DP로 구하기
			for(int i=2; i<=N; i++) {
				dp[0][i] = Math.max(cards[0][i] + dp[1][i-1], Math.max(dp[0][i-1], dp[1][i-1]));
				dp[1][i] = Math.max(cards[1][i] + dp[0][i-1], Math.max(dp[0][i-1], dp[1][i-1]));
			}
			// N열에 기록된 점수 중 큰 값 출력
			System.out.println(Math.max(dp[0][N], dp[1][N]));
		}
	}

}