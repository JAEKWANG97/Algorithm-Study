import java.util.*;
import java.io.*;


public class BOJ_7576 {

	static int n, m, arr[][], toma, sol;
	static Queue<int[]> que = new ArrayDeque<>();
	static int dx[] = {1, -1, 0, 0};
	static int dy[] = {0, 0, 1, -1};
	
	static boolean isRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < n && x < m) {
			return true;
		}
		return false;
	}
	
	
	static void bfs() {
		while (!que.isEmpty()) {
			int[] cur = que.poll();
			toma--;
			if (sol < arr[cur[0]][cur[1]]) {
				sol = arr[cur[0]][cur[1]];
			}
			for (int i = 0; i < 4; i++) {
				int ny = cur[0] + dy[i];
				int nx = cur[1] + dx[i];
				if (isRange(ny, nx) && arr[ny][nx] == 0) {
					que.offer(new int[] {ny, nx});
					arr[ny][nx] = arr[cur[0]][cur[1]] + 1;
				}
				
			}
		}
		
		if (toma == 0) {
			System.out.println(sol - 1);
		} else {
			System.out.println(-1);
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 0) toma++;
				if (arr[i][j] == 1) {
					que.offer(new int[] {i, j});
					toma++;
				}
			}
		}
		
		bfs();
	}
	
}
