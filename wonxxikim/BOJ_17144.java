import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] delta = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static int[][] delta2 = { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };

	static class air {
		int x;
		int y;
		int n;

		public air(int x, int y, int n) {
			this.x = x;
			this.y = y;
			this.n = n;
		}

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int[][] room = new int[R][C];
		int fresh_x = 0;
		int fresh_y = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				if (room[i][j] == -1) {
					fresh_x = i;
				}
			}
		}
		for (int t = 0; t < T; t++) {
			Queue<air> q = new LinkedList<>();
			for (int i = 0; i < R; i++) { // 미세먼지 번식
				for (int j = 0; j < C; j++) {
					if (room[i][j] > 0) {
						q.add(new air(i, j,room[i][j]));
						room[i][j] = 0;
					}
				}
			}
			while (!q.isEmpty()) {
				air cur = q.poll();
				int cnt = 0;
				int mod = cur.n/5;
				for (int d = 0; d < 4; d++) {
					int nr = cur.x + delta[d][0];
					int nc = cur.y + delta[d][1];
					if (nr >= 0 && nr < R && nc >= 0 && nc < C && room[nr][nc] != -1) {
						cnt++;
						room[nr][nc] += mod;
					}
				}
				room[cur.x][cur.y] +=( cur.n- (mod* cnt));

			}
			// 공기정화
			int temp = 0;
			int d = 0;
			int x = fresh_x;
			int y = fresh_y;
			while (d < 4) { // 아래 청정기
				int nr = x + delta[d][0];
				int nc = y + delta[d][1];
				if (nr == fresh_x - 1 || nr == R || nc == -1 || nc == C) {
					d++;
					continue;
				}

				int tem_2 = room[nr][nc];
				room[nr][nc] = temp;
				temp = tem_2;
				x = nr;
				y = nc;
			}
			room[fresh_x][0] = -1;
			temp = 0;
			d = 0;
			x = fresh_x - 1;
			y = fresh_y;
			while (d < 4) { // 위 청정기
				int nr = x + delta2[d][0];
				int nc = y + delta2[d][1];
				if (nr == fresh_x || nr == -1 || nc == -1 || nc == C) {
					d++;
					continue;
				}
				int tem_2 = room[nr][nc];
				room[nr][nc] = temp;
				temp = tem_2;
				x = nr;
				y = nc;
			}

			room[fresh_x-1][0] = -1;
			
		

		}
		int result = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (room[i][j] > 0)
					result += room[i][j];
			}

		}
		System.out.println(result);
	}

}
