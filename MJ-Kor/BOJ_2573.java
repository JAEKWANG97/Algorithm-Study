package algorithm_study.day_03_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;



public class BOJ_2573 {
	
	private static class Ice {
		int r, c;
		
		public Ice(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	private static int[] dr = {-1, 0, 1, 0};
	private static int[] dc = {0, 1, 0, -1};
	private static int N, M;
	private static int[][] map;
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int year = 0;
		while (true) {
			int result = countIsland();
			if (result >= 2) {
				break;
			} else if (result == 0) {
				year = 0;
				break;
			}
			bfs();
			year++;
		}

		System.out.println(year);
	}
	
	public static int countIsland() {
		boolean[][] visited = new boolean[N][M];
		
		int cnt = 0;
		
		for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (!visited[r][c] && map[r][c] > 0) {
                    dfs(r, c, visited);
                    cnt++;
                }
            }
        }
		
		return cnt;
	}
	
	public static void dfs(int r, int c, boolean[][] visited) {
		visited[r][c] = true;
		
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
				if (!visited[nr][nc] && map[nr][nc] > 0) {
					dfs(nr, nc, visited);
				}
			}
		}
	}
	
	public static void bfs() {
		Queue<Ice> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		
		for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] > 0) {
                    q.add(new Ice(r, c));
                    visited[r][c] = true;
                }
            }
        }
		
		while (!q.isEmpty()) {
			Ice ice = q.poll();
			int ocean = 0;
			for (int i = 0; i < 4; i++) {
				int nr = ice.r + dr[i];
				int nc = ice.c + dc[i];
				if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
					if (!visited[nr][nc] && map[nr][nc] == 0) {
						ocean++;
					}
				}
			}
			if (map[ice.r][ice.c] - ocean < 0) {
				map[ice.r][ice.c] = 0;
			} else {
				map[ice.r][ice.c] -= ocean;
			}
		}
	}

}
