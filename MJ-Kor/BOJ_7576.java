import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576 {
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int answer = 1;
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		int[][] map; 
		
		Queue<int[]> queue = new ArrayDeque<>();
		
		int C = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		boolean[][] visited = new boolean[R][C];
		map = new int[R][C];
		
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 1) {
					visited[r][c] = true;
					queue.offer(new int[] {r, c});
				}
			}
		}

		while(!queue.isEmpty()) {
			int[] curr = queue.poll();
			if(map[curr[0]][curr[1]] > answer) answer = map[curr[0]][curr[1]];
			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + dr[i];
				int nc = curr[1] + dc[i];
				if(nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[nr][nc] && map[nr][nc] == 0) {
					visited[nr][nc] = true;
					map[nr][nc] = map[curr[0]][curr[1]] + 1;
					queue.offer(new int[] {nr, nc});
				}
			}
		}
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if(map[r][c] == 0) {
					System.out.println(-1);
					System.exit(0);
				}
			}
		}
		
		System.out.println(answer - 1);
	}
}
