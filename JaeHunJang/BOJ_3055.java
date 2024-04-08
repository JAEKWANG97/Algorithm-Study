import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 3055. 탈출 / 30분 
public class BOJ_3055 {
	static StringBuilder sb = new StringBuilder();
	static int N, M, deltas[][] = {{1,0},{-1,0},{0,1},{0,-1}};
	static char map[][];
	static Queue<Pos> water;
	
	static class Pos {
		int r, c, step;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
			step = 0;
		}

		@Override
		public String toString() {
			return "Pos [r=" + r + ", c=" + c + ", step=" + step + "]";
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	static void init() throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken()); 
		map = new char[N][M];
		
		water = new ArrayDeque<>();
		Pos start = null;
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'S') start = new Pos(i,j);
				if (map[i][j] == '*') water.offer(new Pos(i, j));
			}
		}

		solve(start);
	}
	
	static void solve(Pos start) throws Exception {
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(start);
		boolean visited[][] = new boolean[N][M];
		visited[start.r][start.c] = true;
		
		while(!q.isEmpty()) {
			fillWater();
//			printArr(map);
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Pos cur = q.poll();
//				System.out.println(cur);
				if (map[cur.r][cur.c] == 'D') {
					sb.append(cur.step);
					return;
				}
				
				for (int d = 0; d < deltas.length; d++) {
					int nr = cur.r + deltas[d][0];
					int nc = cur.c + deltas[d][1];
					
					
					if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;				
					if (map[nr][nc] == 'X' || map[nr][nc] == '*') continue;
					if (visited[nr][nc]) continue;
					visited[nr][nc] = true;
					
					Pos next = new Pos(nr,nc);
					next.step = cur.step+1;
					q.offer(next);
				}
			}
		}
		
		sb.append("KAKTUS");
	}
	
	static void fillWater() {
		Queue<Pos> newWater = new ArrayDeque<>();
		while(!water.isEmpty()) {
			Pos cur = water.poll();
			for (int d = 0; d < deltas.length; d++) {
				int nr = cur.r + deltas[d][0];
				int nc = cur.c + deltas[d][1];
				
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;				
				if (map[nr][nc] == 'X' || map[nr][nc] == '*' || map[nr][nc] == 'D') continue;
				map[nr][nc] = '*';
				newWater.offer(new Pos(nr, nc));
			}
		}
		while(!newWater.isEmpty()) {
			water.offer(newWater.poll());
		}
	}
	
	static void printArr(char map[][]) {
		for (int i = 0; i < map.length; i++) {
			System.out.println(map[i]);
		}
		System.out.println();
	}
}