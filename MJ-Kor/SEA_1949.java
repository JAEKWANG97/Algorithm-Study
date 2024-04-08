package algorithm_study.day_03_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
1
4 4
8 3 9 5
4 6 8 5
8 1 5 1
4 9 5 5

1
5 1
9 3 2 3 2
6 3 1 7 5
3 4 8 9 9
2 3 7 7 7
7 6 5 5 8

 && !visited[curr.dig][curr.k][nr][nc]
 */


public class SEA_1949 {
	
	private static class Coord{
		// w: 너비, k: 굴착
		int r, c;

		public Coord(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Coord [r=" + r + ", c=" + c + "]";
		}
		
	}

	private static int N, K, max;
	private static int[] dr = {-1, 0, 1, 0};
	private static int[] dc = {0, 1, 0, -1};
	private static int[][] map;
	private static boolean[][][] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			List<Coord> topList = new ArrayList<>();
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			max = Integer.MIN_VALUE;
			int top = Integer.MIN_VALUE;
			
			map = new int[N][N];
			
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					int in = Integer.parseInt(st.nextToken());
					top = Math.max(top, in);
					map[r][c] = in;
				}
			}
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if(map[r][c] == top) { 
						topList.add(new Coord(r, c));
					}
				}
			}
		
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					for (int d = 0; d < K; d++) {
						map[r][c] -= 1;
						for(Coord topCoord : topList) {
							int length = bfs(topCoord);
							max = Math.max(max, length);
						}
					}
					map[r][c] += K;
				}
			}
			
			System.out.println("#" + test_case + " " + max);
		}
	}

	private static int bfs(Coord coord) {
		int result = Integer.MIN_VALUE;
		int[][] visited = new int[N][N];
		Queue<Coord> queue = new ArrayDeque<>();
		queue.offer(coord);
		visited[coord.r][coord.c] = 1; 
		
		while(!queue.isEmpty()) {
			Coord curr = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nr = curr.r + dr[i];
				int nc = curr.c + dc[i];
				if(nr >= 0 && nr < N && nc >= 0 && nc < N && map[curr.r][curr.c] > map[nr][nc]) {
					if(visited[nr][nc] < visited[curr.r][curr.c] + 1) {
						visited[nr][nc] = visited[curr.r][curr.c] + 1;
						result = Math.max(result, visited[nr][nc]);
					}
					queue.offer(new Coord(nr, nc));
				}
			}
		}
		
		return result;
	}
}


