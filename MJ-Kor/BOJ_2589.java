package algorithm_study.day_03_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2589 {
	
	private static class Point{
		int r, c, b;

		public Point(int r, int c, int b) {
			super();
			this.r = r;
			this.c = c;
			this.b = b;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", b=" + b + "]";
		}
		
	}
	
	private static int[] dr = {-1, 0, 1, 0};
	private static int[] dc = {0, 1, 0, -1};
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int max = Integer.MIN_VALUE;
		
		char[][] map = new char[R][C];
		for (int r = 0; r < R; r++) {
			String str = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = str.charAt(c);
			}
		}
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if(map[r][c] == 'L') {
					max = Math.max(bfs(map, r, c), max);
				}
			}
		}
		
		System.out.println(max);
	}

	private static int bfs(char[][] map, int r, int c) {
		int maxBreadth = 0;
		int R = map.length;
		int C = map[0].length;
		boolean[][] visited = new boolean[R][C];
		Queue<Point> queue = new ArrayDeque<>();
		visited[r][c] = true;
		queue.offer(new Point(r, c, 0));
		while(!queue.isEmpty()) {
			Point curr = queue.poll();
			maxBreadth = Math.max(maxBreadth, curr.b);
			for (int i = 0; i < 4; i++) {
				int nr = curr.r + dr[i];
				int nc = curr.c + dc[i];
				if(nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[nr][nc] && map[nr][nc] == 'L') {
					visited[nr][nc] = true;
					queue.offer(new Point(nr, nc, curr.b + 1));
				}
			}
		}
		return maxBreadth;
	}

}
