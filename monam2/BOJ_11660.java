import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_11660 { //백준 11660 구간 합 구하기5 - 80분
	public static void main(String[] args) throws IOException {
		// 2차원 DP. 점화식도 같음 DP[n] = DP[n-1] + arr[n]
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
	
		int[][] nums = new int[n+1][n+1];
		for (int i = 1; i < n+1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < n+1; j++) {
				nums[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[][] pts = new int[m][4];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			pts[i][0] = Integer.parseInt(st.nextToken());
			pts[i][1] = Integer.parseInt(st.nextToken());
			pts[i][2] = Integer.parseInt(st.nextToken());
			pts[i][3] = Integer.parseInt(st.nextToken());
		}
		
		//로직 시작
		int[][] dp = new int[n+1][n+1];
		dp[1][1] = nums[1][1];
		for (int i = 1; i < n+1; i++) {
			for (int j = 1; j < n+1; j++) {
				dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + nums[i][j];
			}
		}
		for (int k = 0; k < m; k++) {
			int startX = pts[k][0];
			int startY = pts[k][1];
			int endX = pts[k][2];
			int endY = pts[k][3];
			
			
			
			int result = dp[endX][endY] - dp[startX-1][endY] - dp[endX][startY-1] + dp[startX-1][startY-1];
//			sb.append(result).append(" ").append("\n");
			bw.write(result + "\n");
		}
//		System.out.println(sb);
		bw.flush();
		bw.close();
	}
}
