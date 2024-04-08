
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 상어 초등학교 / 90분
public class BOJ_21608 {
	static StringBuilder sb = new StringBuilder();
	static int N, map[][], favorite[][], deltas[][] = {{1,0}, {-1,0}, {0,1}, {0,-1}};
	static Queue<Integer> seq;
	static Pos[] positions;
	
	static class Pos {
		int r, c, blankCnt;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
			blankCnt = 0;
		}

		@Override
		public String toString() {
			return "Pos [r=" + r + ", c=" + c + ", blankCnt=" + blankCnt + "]";
		}
		
	}

	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	private static void init() throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		seq = new ArrayDeque<>(); // 자리를 정할 순서
		map = new int[N][N]; // 자리 map
		
		positions = new Pos[N*N+1]; // 학생의 위치
		favorite = new int[N*N+1][4]; // 좋아하는 학생의 번호
		
		for (int i = 1; i <= N*N; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			seq.offer(idx);
			for (int j = 0; j < 4; j++) {
				favorite[idx][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		solve();
	}
	
	static void solve() throws Exception {
		while(!seq.isEmpty()) {
			int student = seq.poll();
			Pos cur = findPosition(favorite[student]);
			positions[student] = cur;
			map[cur.r][cur.c] = student; 
		}
//		printMap(map);
		sb.append(calcScore());
	}
	
	
	static boolean isAdjacent(int r1, int c1, int r2, int c2) {
		return Math.abs(r2 - r1) + Math.abs(c2 - c1) <= 1;
	}
	
	static int countBlankAdjacent(int r, int c) {
		int cnt = 0;
		for (int d = 0; d < deltas.length; d++) {
			int nr = r + deltas[d][0];
			int nc = c + deltas[d][1];
			
			if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			if (map[nr][nc] == 0) cnt++;
		}
		
		return cnt;
	}
	
	static Pos findPosition(int[] favorite) {
		int adjmap[][] = new int[N][N];
		
		int max = 0;
		for (int i = 0; i < 4; i++) {
			Pos cur = positions[favorite[i]];
			
			if (cur == null) continue;
			for (int d = 0; d < deltas.length; d++) {
				int nr = cur.r + deltas[d][0];
				int nc = cur.c + deltas[d][1];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				if (map[nr][nc] != 0) continue;
				adjmap[nr][nc]++;
				max = Math.max(max, adjmap[nr][nc]);
			}
		}
		
		PriorityQueue<Pos> pq = new PriorityQueue<>(new Comparator<Pos>() {
			public int compare(Pos o1, Pos o2) {
				if (Integer.compare(o2.blankCnt, o1.blankCnt) == 0) {
					if (Integer.compare(o1.r, o2.r) == 0) {
						return Integer.compare(o1.c, o2.c);
					}
					return Integer.compare(o1.r, o2.r);
				}
				return Integer.compare(o2.blankCnt, o1.blankCnt);
			}
		});
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (adjmap[i][j] == max) {
					if (map[i][j] == 0) {
						Pos p = new Pos(i, j);
						p.blankCnt = countBlankAdjacent(i, j);
						pq.offer(p);
					}
				}
			}
		}
		
		return pq.poll();
	}
	
	static int calcScore() {
		int score = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int fCnt = 0;
				for (int f = 0; f < favorite[map[i][j]].length; f++) {
					fCnt += countFavoriteAdjacent(i, j, favorite[map[i][j]][f]);
//					if (s == 0) continue;

				}
				score += Math.pow(10, fCnt-1);
			}
		}
		return score;
	}
	
	static int countFavoriteAdjacent(int r, int c, int f) {
		int cnt = 0;
		for (int d = 0; d < deltas.length; d++) {
			int nr = r + deltas[d][0];
			int nc = c + deltas[d][1];
			
			if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			if (map[nr][nc] == f) cnt++;
		}
		
		return cnt;
	}
	
	static void printMap(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println();
	}
}
