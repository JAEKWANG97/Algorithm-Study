package algorithm_study.day_03_21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16234 {

	static class Point{
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}
		
	}
	
	private static int N, L, R;
	private static int[][] map;
	private static int[] dr = {-1, 0, 1, 0};
	private static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(migration());
		
	}

	private static int migration() {
		boolean finish = true;
		int day = -1;
		boolean[][] visited = new boolean[N][N];
		do {
			finish = true;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if(visited[r][c]) continue;
					if(makeUnion(r, c, visited)) {
						finish = false;
					}
				}
			}
			visited = new boolean[N][N];
			day++;
		} while(!finish);
		return day;
	}

	private static boolean makeUnion(int r, int c, boolean[][] visited) {
		boolean move = false;
		List<Point> union = new ArrayList<>(); 
		Queue<Point> queue = new ArrayDeque<>();
		visited[r][c] = true;
		queue.offer(new Point(r, c));
		while(!queue.isEmpty()) {
			Point curr = queue.poll();
			union.add(curr);
			for (int i = 0; i < 4; i++) {
				int nr = curr.r + dr[i];
				int nc = curr.c + dc[i];
				if(nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
					if(Math.abs(map[curr.r][curr.c] - map[nr][nc]) >= L && Math.abs(map[curr.r][curr.c] - map[nr][nc]) <= R) {
						visited[nr][nc] = true;
						queue.offer(new Point(nr, nc));
					}
				}
			}
		}
		
		if(union.size() != 1) {
			movePopulation(union);
			move = true;
		}
		return move;
	}

	private static void movePopulation(List<Point> union) {
		int total = 0;
		for(Point p : union) {
			total += map[p.r][p.c];
		}
		
		int population = (int)(total/union.size());
		
		for(Point p : union) {
			map[p.r][p.c] = population;
		}
	}

}
