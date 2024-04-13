package algorithm_study.day_03_28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 기존 풀이는 배열의 크기가 N, N 딱맞게 되어있어서 인덱서 예외처리를 하느라 코드가 길어졌음
 * N + 1, N + 1 크기의 2차원 배열과 0행과 0열을 0으로 만들어 준다면 코드를 간략화할 수 있음
 * 입력도 그대로 받아주면 됨 -1 할 필요 없음
 * @author SSAFY
 *
 */

public class BOJ_11660 {

	public static int[][] dp;
	public static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		dp = new int[N + 1][N + 1];
		arr = new int[N + 1][N + 1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int e = Integer.parseInt(st.nextToken());
				arr[i][j] = e;
				dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + e;
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int target;
			if (x1 == x2 && y1 == y2) {
				target = arr[x2][y2];
			}
			else {
				target = dp[x2][y2] - dp[x2][y1 - 1] - dp[x1 - 1][y2] + dp[x1 - 1][y1 - 1];
			}
			
			System.out.println(target);
		}
		
	}
}
