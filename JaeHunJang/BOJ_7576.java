import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576 {
	static StringBuilder sb = new StringBuilder();
	static int M, N, map[][], totalCnt;
	static List<int[]> start;
	
	public static void main(String[] args) throws Exception {
		init();
		solve();
		print();
	}
	static void print() {
		System.out.println(sb.toString());
	}
	
	static void init() throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // 토마토 상자 크기
		N = Integer.parseInt(st.nextToken());
		totalCnt = 0; // 익어야할 토마토 개수
		start = new ArrayList<>(); // 시작 위치 (익은 토마토)
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) totalCnt++;
				else if (map[i][j] == 1) start.add(new int[] {i, j});
			}
		}
	}
	
	static void solve() throws Exception {
		bfs();
	}
	
	static void bfs() {
		int deltas[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
		boolean visited[][] = new boolean[N][M];
		Queue<int[]> q = new ArrayDeque<>();
		for (int[] s : start) {
			q.offer(s);
			visited[s[0]][s[1]] = true;
		}
		
		int time = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				int[] cur = q.poll();
				
				for (int d = 0; d < deltas.length; d++) {
					int nr = cur[0] + deltas[d][0];
					int nc = cur[1] + deltas[d][1];
					
					if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
					if (map[nr][nc] == -1) continue;
					if (visited[nr][nc]) continue;
					visited[nr][nc] = true;
					totalCnt--;
					q.offer(new int[] {nr, nc});
				}
			}
			time++;
		}
		if (totalCnt == 0) sb.append(time-1);
		else sb.append(-1);
	}
}