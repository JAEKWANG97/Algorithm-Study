import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 상범 빌딩 - 30분
public class BOJ_6593 {
	static StringBuilder sb = new StringBuilder();
	static int L, R, C;

	static class Pos {
		int l, r, c;

		public Pos(int l, int r, int c) {
			this.l = l;
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
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			if (L == 0 && R == 0 && C == 0) return; // 종료

			char[][][] map = new char[L][R][C];
			Pos start = null;
			for (int i = 0; i < L; i++) {
				for (int j = 0; j < R; j++) {
					map[i][j] = br.readLine().toCharArray();
					for (int k = 0; k < C; k++) {
						if (map[i][j][k] == 'S') start = new Pos(i, j, k);
					}
				}
				br.readLine();
			}

			solution(map, start);

		}
	}

	private static void solution(char[][][] map, Pos start) {
		int[] deltaL = {1, -1, 0, 0, 0, 0};
		int[] deltaR = {0, 0, 1, -1, 0, 0};
		int[] deltaC = {0, 0, 0, 0, 1, -1};
		int time = 0;
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(start);
		boolean[][][] visited = new boolean[L][R][C];
		visited[start.l][start.r][start.c] = true;

		while(!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Pos cur = q.poll();
				if (map[cur.l][cur.r][cur.c] == 'E') {
					sb.append("Escaped in ").append(time).append(" minute(s).\n");
					return;
				}

				for (int d = 0; d < deltaL.length; d++) {
					int nl = cur.l + deltaL[d];
					int nr = cur.r + deltaR[d];
					int nc = cur.c + deltaC[d];
					if (nl < 0 || nl >= L || nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
					if (visited[nl][nr][nc]) continue;
					if (map[nl][nr][nc] == '#') continue;
					visited[nl][nr][nc] = true;
					q.offer(new Pos(nl, nr, nc));
				}
			}

			time++;
		}

		sb.append("Trapped!\n");
	}
}
