import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 17070. 파이프 옮기기 1 / 50분 
public class BOJ_17070 {
	static StringBuilder sb = new StringBuilder();
	static int N, map[][], cnt, deltas[][][] = {
			{{0,1},{1,1}},
			{{1,0},{1,1}},
			{{0,1},{1,0},{1,1}}
			};
	
	static class Pos {
		int r, c;
		public Pos (int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static class Pipe {
		Pos start, end;
		public Pipe (Pos start, Pos end) {
			this.start = start;
			this.end = end;
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
		map = new int[N][N];
		cnt = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		if (map[N-1][N-1] == 1) sb.append(0); // 도착지점에 벽이 있으면 갈 수 없음
		else solve();
	}
	
	static void solve() {
		Queue<Pipe> q = new ArrayDeque<>();
		q.offer(new Pipe(new Pos(0, 0), new Pos(0, 1)));
		
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				Pipe cur = q.poll();
				
				if (cur.end.r == N-1 && cur.end.c == N-1) {
					cnt++;
					continue;
				}
				
				int dir = getPipeDir(cur);
				
				for (int d = 0; d < deltas[dir].length; d++) {
					int nr = cur.end.r + deltas[dir][d][0];
					int nc = cur.end.c + deltas[dir][d][1];
					
					if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					Pipe next = new Pipe(cur.end, new Pos(nr, nc));
					if (!canMove(next)) continue; 
					q.offer(next);
				}
			}
		}
		
		sb.append(cnt);
	}
	static boolean canMove(Pipe p) {
		int dir = getPipeDir(p);
		
		if (map[p.end.r][p.end.c] == 1) return false; // 도착지점에 벽 확인
		if (dir == 2) { // 대각선 이동인 경우 추가 확인
			for (int d = 0; d < deltas[dir].length-1; d++) { // 마지막 대각선 방향으로는 확인할 필요 없어서 -1
				int nr = p.end.r - deltas[dir][d][0];
				int nc = p.end.c - deltas[dir][d][1];
				
				if (map[nr][nc] == 1) return false;
			}
		}
		
		return true;
	}
	
	static int getPipeDir(Pipe pipe) {
		if (pipe.start.r == pipe.end.r) return 0; // 가로
		else if (pipe.start.c == pipe.end.c) return 1; // 세로
		else return 2; // 대각선
	}
}