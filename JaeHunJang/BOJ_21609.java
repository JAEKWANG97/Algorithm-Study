import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 21609. 상어 중학교 / 120분
public class BOJ_21609 {
	static StringBuilder sb = new StringBuilder();
	static int N, M, map[][], mCnt, totalScore, deltas[][] = {{1,0},{-1,0},{0,1},{0,-1}};
	static List<Integer>[] list;
	static List<Pos> blockGroup;
	static Pos flag;
	static boolean[][] visited;
	
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
	
	static void init() throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		blockGroup = new ArrayList<>();
		flag = null;
		mCnt = 0;
		totalScore = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		solve();
	}

	static void solve() {
		while(true) {
			findBlockGroup();
			if (flag == null) break;

			calcScore();
			doGravity();
			rotateMap();
			doGravity();
			initProcess();
		}
		sb.append(totalScore);
	}

	static void rotateMap() {
		int[][] copyMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copyMap[N-1-j][i] = map[i][j];
			}
		}
		map = copyMap;
	}

	static void initProcess() {
		blockGroup.clear();
		flag = null;
	}
	static void doGravity() {
		for (int j = 0; j < N; j++) {
			for (int k = 0; k < N; k++) {
				for (int i = N-1; i > 0; i--) {
					if (map[i][j] == -9 && map[i - 1][j] != -1) {
						int temp = map[i - 1][j];
						map[i - 1][j] = map[i][j];
						map[i][j] = temp;
					}
				}
			}
		}
	}

	static void calcScore() {
		for(Pos p : blockGroup) {
			map[p.r][p.c] = -9; // 블록 제거
		}

		totalScore += Math.pow(blockGroup.size(), 2);
	}
	
	static void findBlockGroup() {
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] <= 0) continue;
				bfs(new Pos(i, j), map[i][j]);
			}
		}
	}
	
	static void bfs(Pos start, int m) {
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(start);
		visited[start.r][start.c] = true;
		List<Pos> tempGroup = new ArrayList<>();

		while(!q.isEmpty()) {
			Pos cur = q.poll();
			tempGroup.add(cur);

			for (int d = 0; d < deltas.length; d++) {
				int nr = cur.r + deltas[d][0];
				int nc = cur.c + deltas[d][1];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;
				if (map[nr][nc] < 0) continue; // 검은색 블록 -1, 제거된 블록 -9
				if (map[nr][nc] == 0 || map[nr][nc] == m) { // 무지개 블록이거나 같은 색일때
					visited[nr][nc] = true;
					q.offer(new Pos(nr, nc));
				}
			}
		}
		
		int cnt = 0;

		Pos min = null;
		for (Pos p : tempGroup) {
			int i = p.r;
			int j = p.c;
			if (min == null && map[i][j] == m) { // 행과 열번호가 가장 작은 블록을 기준블록으로 선택
				min = p;
			}
			if (map[i][j] == 0) {
				cnt++; // 무지개 블록 숫자
				visited[i][j] = false; // 다음 탐색을 위해 방문 초기화
			}
		}
		
		if (tempGroup.size() >= 2) {
			if (flag == null || (blockGroup.size() < tempGroup.size()) // 블록 그룹이 더 클때
					|| (blockGroup.size() == tempGroup.size() && mCnt < cnt) // 블록 수가 같으면서 무지개 블록이 많을 때
					|| (blockGroup.size() == tempGroup.size() && mCnt == cnt && flag.r < min.r) // 행번호가 클때
					|| (blockGroup.size() == tempGroup.size() && mCnt == cnt && flag.r == min.r && flag.c < min.c) // 열번호가 클때 
					) {
				blockGroup.clear();
				blockGroup.addAll(tempGroup);
				mCnt = cnt;
				flag = min;
			}
		}
	}
	
	static void printArr(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println();
	}
}