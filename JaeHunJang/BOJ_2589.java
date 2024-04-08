import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

// 보물섬 - 30분
public class BOJ_2589 {
	static StringBuilder sb = new StringBuilder();
	static int N, M, maxDist;
	static char map[][];
	
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	private static void init() throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 보물섬 맵의 크기
		M = Integer.parseInt(st.nextToken());
		
		HashSet<Pos> set = new HashSet<>(); // 육지의 좌표를 담을 set
		
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'L') set.add(new Pos(i, j)); // 육지 좌표들만 추출
			}
		}
		
		maxDist = 0;
		
		solve(set);
	}
	
	private static void solve(HashSet<Pos> set) throws Exception {
		for (Pos p : set) { // 육지의 각 좌표를 시작점으로 순회 끝지점을 찾기
			bfs(p);
		}
		
		sb.append(maxDist);
	}
	
	private static void bfs(Pos start) {
		int deltas[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
		boolean visited[][] = new boolean[N][M];
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(start);
		visited[start.r][start.c] = true;
		
		int dist = 0; // 시작지점부터 끝지점까지의 거리
		while(!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Pos cur = q.poll();
				
				for (int d = 0; d < deltas.length; d++) {
					int nr = cur.r + deltas[d][0];
					int nc = cur.c + deltas[d][1];
					
					if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
					if (visited[nr][nc]) continue;
					if (map[nr][nc] == 'W') continue;
					visited[nr][nc] = true;
					q.offer(new Pos(nr, nc));
				}				
			}
			dist++;
		}
		
		maxDist = Math.max(maxDist, dist-1); // 최장 거리를 갱신
	}
}

