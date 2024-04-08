import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 불! - 50분
public class BOJ_4179 {
	static StringBuilder sb = new StringBuilder();
	static int R, C;
	static char map[][];
	static List<Pos> fires;
	
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Pos [r=" + r + ", c=" + c + "]";
		}
	}
		
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	private static void init() throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // 맵의 크기
		C = Integer.parseInt(st.nextToken());
		
		Pos start = null;
		fires = new ArrayList<>();
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'J') start = new Pos(i, j);
				if (map[i][j] == 'F') fires.add(new Pos(i, j));
			}
		}
		
		solve(start);
	}
	
	private static void solve(Pos start) throws Exception {
		int time = bfs(start);
		if (time == -1) sb.append("IMPOSSIBLE");
		else sb.append(time);
	}
	
	private static int bfs(Pos start) {
		int deltas[][] = {{1,0}, {-1,0}, {0,1}, {0,-1}};
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(start);
		
		boolean visited[][] = new boolean[R][C];
		visited[start.r][start.c] = true;
		
		int time = 1;
		while(!q.isEmpty()) {
			expandFire(); // 불을 먼저 확산시켜서 탈출 경로 제한
			
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Pos cur = q.poll();
//				System.out.println(cur);
				if (cur.r == 0 || cur.r == R-1 || cur.c == 0 || cur.c == C-1) return time; // 미로 끝에 도달하면 탈출
				
				for (int d = 0; d < deltas.length; d++) {
					int nr = cur.r + deltas[d][0];
					int nc = cur.c + deltas[d][1];
					
					if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
					
					if (visited[nr][nc]) continue;
					if (map[nr][nc] == '#' || map[nr][nc] == 'F') continue;
					q.offer(new Pos(nr, nc));
					visited[nr][nc] = true;
				}
			}
//			printMap();
			time++;
		}
		
		return -1; // 끝에 도달하지 못하고 bfs가 종료되면 탈출 불가
	}
	
	private static void expandFire() {
		int deltas[][] = {{1,0}, {-1,0}, {0,1}, {0,-1}};
		int size = fires.size();
		List<Pos> newFires = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			Pos cur = fires.get(i);
			for (int d = 0; d < deltas.length; d++) {
				int nr = cur.r + deltas[d][0];
				int nc = cur.c + deltas[d][1];
				
				if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue; 
				if (map[nr][nc] == '#' || map[nr][nc] == 'F') continue;
				map[nr][nc] = 'F';
				newFires.add(new Pos(nr, nc));
			}
		}
		fires = newFires; // 새로 확산한 불만 리스트로 가지고 있음.
	}
	
	private static void printMap() {
		for (int i = 0; i < map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println();
	}
}

