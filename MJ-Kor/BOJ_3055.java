package algorithm_study.day_04_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3055 {
	
	private static class Point{
		int r, c, status;

		public Point(int r, int c, int status) {
			super();
			this.r = r;
			this.c = c;
			this.status = status;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", status=" + status + "]";
		}
	}
	
	private static class QueueData{
		Point p;
		int breadth;
		
		public QueueData(Point p, int breadth) {
			super();
			this.p = p;
			this.breadth = breadth;
		}
		
		@Override
		public String toString() {
			return "QueueData [p=" + p + ", breadth=" + breadth + "]";
		}
	}
	
	private static int[] dr = {-1, 0, 1, 0};
	private static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[R][C];
		
		Point hedgehog = null;
		List<Point> water = new ArrayList<>();
		
		for (int r = 0; r < R; r++) {
			String row = br.readLine();
			for (int c = 0; c < C; c++) {
				char value = row.charAt(c);
				map[r][c] = value;
				if(value == 'S') {
					hedgehog = new Point(r, c, 0);
				}
				if(value == '*') {
					water.add(new Point(r, c, 1));
				}
			}
		}
		bfs(map, hedgehog, water, R, C);
	}

	private static void bfs(char[][] map, Point hedgehog, List<Point> water, int R, int C) {
		// 0: 고슴도치, 1: 물
		boolean[][][] visited = new boolean[2][R][C];
		Queue<QueueData> queue = new ArrayDeque<>();
		for(Point p : water) {
			visited[1][p.r][p.c] = true;
			queue.offer(new QueueData(p, 0));
		}
		visited[0][hedgehog.r][hedgehog.c] = true;
		queue.offer(new QueueData(hedgehog, 0));
		while(!queue.isEmpty()) {
			QueueData curr = queue.poll();
			if(curr.p.status == 0 && map[curr.p.r][curr.p.c] == 'D') {
				System.out.println(curr.breadth);
				System.exit(0);
			}
			if(curr.p.status == 0) {
				for (int i = 0; i < 4; i++) {
					int nr = curr.p.r + dr[i];
					int nc = curr.p.c + dc[i];
					if(indexValid(nr, nc, R, C) && !visited[0][nr][nc] && !visited[1][nr][nc]) {
						if(map[nr][nc] == '.' || map[nr][nc] == 'D') { 
							visited[0][nr][nc] = true;
							Point np = new Point(nr, nc, 0);
							queue.offer(new QueueData(np, curr.breadth + 1));
						}
					}
				}
			} else {
				for (int i = 0; i < 4; i++) {
					int nr = curr.p.r + dr[i];
					int nc = curr.p.c + dc[i];
					if(indexValid(nr, nc, R, C) && !visited[1][nr][nc] && map[nr][nc] != 'X' && map[nr][nc] != 'D' ) {
						visited[1][nr][nc] = true;
						Point np = new Point(nr, nc, 1);
						queue.offer(new QueueData(np, curr.breadth + 1));
					}
				}
			}
		}
		
		System.out.println("KAKTUS");
	}
	
	private static boolean indexValid(int nr, int nc, int R, int C) {
		if(nr >= 0 && nr < R && nc >= 0 && nc < C) return true;
		return false;
	}

}
