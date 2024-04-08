package algorithm_study.day_03_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4179 {

	private static class Point{
		int r, c, jf;

		public Point(int r, int c, int jf) {
			super();
			this.r = r;
			this.c = c;
			this.jf = jf;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", jf=" + jf + "]";
		}
		
	}
	
	private static class QueueData{
		Point point;
		int breadth;
		
		public QueueData(Point point, int breadth) {
			super();
			this.point = point;
			this.breadth = breadth;
		}

		@Override
		public String toString() {
			return "QueueData [point=" + point + ", breadth=" + breadth + "]";
		}
		
	}
	
	private static int[] dr = {-1, 0, 1, 0};
	private static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char map[][] = new char[R][C];
		
		Point jihoon = null;
		List<Point> fires = new ArrayList<>();
		
		for (int r = 0; r < R; r++) {
			String line = br.readLine();
			for (int c = 0; c < C; c++) {
				char in = line.charAt(c);
				if(in == 'J') jihoon = new Point(r, c, 0);
				if(in == 'F') fires.add(new Point(r, c, 1));
				map[r][c] = in;
			}
		}
		
		bfs(R, C, jihoon, fires, map);
	}

	private static void bfs(int R, int C, Point jihoon, List<Point> fires, char[][] map) {
		boolean[][][] visited = new boolean[2][R][C];
		Queue<QueueData> queue = new ArrayDeque<>();
		
		queueInit(queue, jihoon, fires, visited);
		while(!queue.isEmpty()) {
			QueueData curr = queue.poll();
			if(curr.point.jf == 1) {
				int r = curr.point.r;
				int c = curr.point.c;
				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					if(nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[1][nr][nc] && map[nr][nc] != '#') {
						visited[1][nr][nc] = true;
						queue.offer(new QueueData(new Point(nr, nc, curr.point.jf), curr.breadth + 1));
					}
				}
			}
			if(curr.point.jf == 0) {
				int r = curr.point.r;
				int c = curr.point.c;
				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					if(nr < 0 || nr >= R || nc < 0 || nc >= C) {
						System.out.println(curr.breadth + 1);
						System.exit(0);
					}
					if(nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[1][nr][nc] && !visited[0][nr][nc] && map[nr][nc] != '#') {
						visited[0][nr][nc] = true;
						queue.offer(new QueueData(new Point(nr, nc, curr.point.jf), curr.breadth + 1));
					}
				}
			}
		}
		System.out.println("IMPOSSIBLE");
	}

	private static void queueInit(Queue<QueueData> queue, Point jihoon, List<Point> fires, boolean[][][] visited) {
		for(Point fire : fires) {
			queue.offer(new QueueData(fire, 0));
			visited[1][fire.r][fire.c] = true;
		}
		queue.offer(new QueueData(jihoon, 0));
		visited[0][jihoon.r][jihoon.c] = true;
	}
	
}
