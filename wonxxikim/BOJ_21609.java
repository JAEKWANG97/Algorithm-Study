

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] delta = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	static boolean[][] check;
	static int N, M, map[][], startx, starty, startB, startrainbow;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 격자 한변의 크기
		M = Integer.parseInt(st.nextToken()); // 색상의 개수
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int result = 0;
		// 시작할 위치 고르기
		while (true) {
			startx = -1;
			starty = -1;
			startB = 0;
			startrainbow = 0;
			check = new boolean[N][N];
			for (int i = 0; i<N; i++) {
				for (int j = 0; j<N; j++) {
					if (map[i][j] > 0 && ! check[i][j]) {
						check[i][j] = true;
						B(i, j, map[i][j]);
					}
				}
			}
			if(startB<2) break;
			// 제거 및 점수 획득
			if (startB >= 2) {
				remove(startx, starty, map[startx][starty]);
				result += (startB * startB);
			}
			// 중력 작용
			gravity();
			// 반시계방향으로 회전
			rotate();
			// 중력작용
			gravity();
		}
		System.out.println(result);

	}

	public static void B(int i, int j, int thiscolor) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { i, j });
		boolean[][] visit = new boolean[N][N];
		visit[i][j] = true;
		int thisb = 1;
		int thisrainbow = 0;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + delta[d][0];
				int nc = cur[1] + delta[d][1];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visit[nr][nc]) {
					if (map[nr][nc] == 0) {
						visit[nr][nc] = true;
						check[nr][nc] = true;
						q.add(new int[] { nr, nc });
						thisrainbow++;
						thisb++;
					}
					if (map[nr][nc] == thiscolor) {
						visit[nr][nc] = true;
						check[nr][nc] = true;
						q.add(new int[] { nr, nc });
						thisb++;
					}
				}
			}
		}
		if(thisb == startB && startrainbow==thisrainbow) {
			if(startx==i &&starty<j) starty = j;
			if(startx<i) {
				startx = i;
				starty = j;
			}
		}
		if (thisb == startB && startrainbow < thisrainbow) {
			startrainbow = thisrainbow;
			startx = i;
			starty = j;
		}
		if (thisb > startB) {
			startB = thisb;
			startrainbow = thisrainbow;
			startx = i;
			starty = j;
		}

	}

	public static void remove(int i, int j, int thiscolor) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { i, j });
		map[i][j] = -2;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + delta[d][0];
				int nc = cur[1] + delta[d][1];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
					if (map[nr][nc] == 0) {
						map[nr][nc] = -2;
						q.add(new int[] { nr, nc });
					}
					if (map[nr][nc] == thiscolor) {
						map[nr][nc] = -2;
						q.add(new int[] { nr, nc });
					}
				}
			}
		}
	}

	public static void gravity() {
		for (int i = N - 2; i >= 0; i--) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] >= 0 && map[i + 1][j] == -2) {
					int temp = map[i][j];
					map[i][j] = -2;
					boolean flag = false;
					for (int k = i + 1; k < N; k++) {
						if (map[k][j] != -2) {
							map[k - 1][j] = temp;
							flag = true;
							break;
						}
					}
					if(!flag) map[N-1][j] = temp;
				}
			}
		}
	}
	public static void rotate() {
		int[][] temp = new int[N][N];
		for(int i = 0 ; i<N ; i++) {
			for(int j = 0 ; j<N ; j++) {
				temp[N-1-j][i] = map[i][j];
			}
		}
		map = temp;
	}

}
