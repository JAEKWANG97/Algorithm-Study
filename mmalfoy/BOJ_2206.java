
import java.util.*;
import java.io.*;

public class BOJ_2206 {
	static int N, M;
	static int[] dx = new int[] { -1, 1, 0, 0};
	static int[] dy = new int[] { 0, 0, -1, 1};
	static int[][] G;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		G = new int[N][M];
		
		for (int n = 0; n < N; n++) {
			String line = br.readLine();
			for (int m = 0; m < M; m++) {
				G[n][m] = Character.getNumericValue(line.charAt(m));
			}
		}
		boolean[][][] visited = new boolean[N][M][2];
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] {0, 0, 1, 0});
		visited[0][0][0] = true;
		visited[0][0][1] = true;
		int min = bfs(q, visited);
		System.out.println(min);
	}
	
	static int bfs(Queue<int[]> q, boolean[][][] visited) {
		int min = Integer.MAX_VALUE;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			if (cur[0] == N - 1 && cur[1] == M - 1) {
				min = cur[2];
				break;
			}
			
			for (int i = 0; i < 4; i++) {
				int ny = cur[0] + dy[i];
				int nx = cur[1] + dx[i];
				
				if (ny < 0 || ny > N-1 || nx < 0 || nx > M-1 || visited[ny][nx][cur[3]] ) {
					continue;
				}
				
				if (G[ny][nx] == 1 && cur[3] != 0) {
					continue;
				}
				
				if (G[ny][nx] == 1) {
					if (cur[3] == 0) {
						visited[ny][nx][1] = true;
						q.offer(new int[] {ny, nx, cur[2] + 1, 1});															
					}
				} else {					
					visited[ny][nx][cur[3]] = true;
					q.offer(new int[] {ny, nx, cur[2] + 1, cur[3]});	
				}
			}
		}

		return min == Integer.MAX_VALUE ? -1 : min; 
	}
}