import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

// 벽 부수고 이동하기 - 120분
public class BOJ_2206 {
	static StringBuilder sb = new StringBuilder();
	static int N, M, minDist;
	static char map[][];
	
	static class Pos {
		int r, c, breakCnt;

		public Pos(int r, int c, int breakCnt) {
			this.r = r;
			this.c = c;
			this.breakCnt = breakCnt;
		}

		@Override
		public String toString() {
			return "Pos [r=" + r + ", c=" + c + ", breakCnt=" + breakCnt + "]";
		}
	}
	
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	private static void init() throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 맵의 크기
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		minDist = -1;
		
		solve();
	}
	
	private static void solve() throws Exception {
		bfs(new Pos(0, 0, 0));
		sb.append(minDist);
	}
	
	private static void bfs(Pos start) {
		int deltas[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
		boolean visited[][][] = new boolean[2][N][M];
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(start);
		visited[start.breakCnt][start.r][start.c] = true;
		
		int dist = 1; // 시작지점부터 끝지점까지의 거리
		while(!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Pos cur = q.poll();
				if (cur.r == N-1 && cur.c == M-1) {
					minDist = dist; // 도달하면 가능
					return;
				}
				for (int d = 0; d < deltas.length; d++) {
					int nr = cur.r + deltas[d][0];
					int nc = cur.c + deltas[d][1];
					
					if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
					if (visited[cur.breakCnt][nr][nc]) continue;
					if (map[nr][nc] == '1' && cur.breakCnt > 0) continue; // 벽 부술수 없으면 넘어가기
					visited[cur.breakCnt][nr][nc] = true; 
					if (map[nr][nc] == '1') q.offer(new Pos(nr, nc, cur.breakCnt+1)); // 벽 부수고 넘어가기
					else q.offer(new Pos(nr, nc, cur.breakCnt)); // 그냥 넘어가기
				}
			}
			dist++;
		}
	}
}
