
import java.io.*;
import java.util.*;

public class BOJ_7576 {
	
	static int remainder;
	static int[] dx = new int[] {-1, 1, 0, 0};
	static int[] dy = new int[] {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] table = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		
		Queue<int[]> q = new ArrayDeque<int[]>();
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int m = 0; m < M; m++) {
				int cur = Integer.parseInt(st.nextToken());
				table[n][m] = cur;
				if (cur == -1) {
					visited[n][m] = true;
					continue;
				}
				if (cur == 1) {
					visited[n][m] = true;
					q.offer(new int[] {n ,m, 0});
					continue;
				}
				remainder += 1;
			}
		}
		
		if (q.isEmpty()) {
			System.out.println(-1);
			return;
		}
		if (remainder == 0) {
			System.out.println(0);
			return;
		}
		
		int	result = bfs(q, visited);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j]) {
					System.out.println(-1);
					return;
				}
			}
		}
		
		System.out.println(result);
	}
	// q [n, m, depth]
	// 탐색과 함께 remainder +- 1
	static int bfs(Queue<int[]> q, boolean[][] visited) {
		int depth = 0;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			depth = cur[2];
			
			for (int i = 0; i < 4; i++) {
				int ny = cur[0]+dy[i];
				int nx = cur[1]+dx[i];
				
				if (nx < 0 || nx > visited[0].length-1 || ny < 0 || ny > visited.length-1 ||
						visited[ny][nx]) {
					continue;
				}
				
				visited[ny][nx] = true;
				q.offer(new int[] {ny, nx, cur[2] + 1});
			}
		}
		
		return depth;
	}
}
