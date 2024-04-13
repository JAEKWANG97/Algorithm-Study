
import java.util.*;
import java.io.*;

public class BOJ_2573 {
	static int N, M;
	static int[] dy = new int[] {-1, 1, 0, 0};
	static int[] dx = new int[] {0, 0 , -1, 1};
	static int[][] G;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		G = new int[N][M];
		boolean[][] visited; 
		
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int m = 0; m < M; m++) {
				G[n][m] = Integer.parseInt(st.nextToken());
			}
		}

		// 1. 전체 G에 대해 dfs로 탐색.
		// 2. 1이면 녹기 
		// 3. 다시 1.
		// 4. 2이상이면 끝! 
		int result, cnt = 0;
		while (true) {
			visited = new boolean[N][M];
			result = melt(visited);
			if (result == 0) {
				cnt = 0;
				break;
			}
			
			if (result == 1) {
				cnt += 1;
				continue;
			}
			
			if (result >= 2) {
				break;
			}
			System.out.println("잘못된 결과");
			return;
		}
		System.out.println(cnt);
	}
	
	
	// 1. 전체 G에 대해 dfs로 탐색.
	// 2. 녹기 
	static int melt (boolean[][] visited) {
		int cnt = 0;
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (G[n][m] == 0 || visited[n][m]) {
					continue;
				}
				dfs(n, m, visited);
				cnt += 1;
			}
		}
		
		// 덩어리가 1이 아니면 종료
		if (cnt != 1) {
			return cnt;
		}
		
		// 덩어리가 1이므로 녹기
		int [][] g = new int[N][M];
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				g[n][m] = G[n][m];
			}
		}
		
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (G[n][m] == 0) {
					continue;
				}
				
				int water = 0;
				for (int i = 0; i < 4; i++) {
					int ny =  n + dy[i];
					int nx =  m + dx[i];
					
					if (ny < 0 || ny > N - 1 || nx < 0 || nx > M - 1 || G[ny][nx] != 0) {
						continue;
					}
					water += 1;
				}
				g[n][m] = Math.max(0, g[n][m] - water);
			}
		}
		G = g;
		return cnt;
	}
	
	static void dfs(int y, int x, boolean[][] visited) {
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if (ny < 0 || ny > N - 1 || nx < 0 || nx > M - 1 || visited[ny][nx] || G[ny][nx] == 0) {
				continue;
			}
				
			visited[ny][nx] = true;
			dfs(ny, nx, visited);
		}
	}
	
}
