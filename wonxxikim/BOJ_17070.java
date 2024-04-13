import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] house;
	static int[][] delta = { { 0, 1 }, { 1, 1 }, { 1, 0 } }; // ga : 0,1 se : 1,2 dae : 0, 1, 2
	static int cnt, N;

	static class pipe {
		int x;
		int y;
		int status;

		public pipe(int x, int y, int status) {
			this.x = x;
			this.y = y;
			this.status = status; // ga : 0, se : 1, dae : 2
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		house = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				house[i][j] = Integer.parseInt(st.nextToken());
				if (house[i][j] == 1)
					house[i][j] = -1;
			}
		}
		house[1][2] = 1;
		cnt = 0;
		dfs(new pipe(1, 2, 0));
		System.out.println(cnt);

	}

	static void dfs(pipe present) {
		if (present.x == N && present.y == N) {
			cnt++;
			return;
		}
		if (present.status == 0) {
			for (int d = 0; d <= 1; d++) {
				int nr = present.x + delta[d][0];
				int nc = present.y + delta[d][1];
				if (nr >= 1 && nr <= N && nc >= 1 && nc <= N && house[nr][nc] != -1) {
					if (d == 0) {
						dfs(new pipe(nr, nc, 0));
					}
					if (d == 1 && house[nr - 1][nc] != -1 && house[nr][nc - 1] != -1) {
						dfs(new pipe(nr, nc, 2));
					}
				}
			}
		}
		if (present.status == 1) {
			for (int d = 1; d <= 2; d++) {
				int nr = present.x + delta[d][0];
				int nc = present.y + delta[d][1];
				if (nr >= 1 && nr <= N && nc >= 1 && nc <= N && house[nr][nc] != -1) {
					if (d == 1 && house[nr - 1][nc] != -1 && house[nr][nc - 1] != -1) {
						dfs(new pipe(nr, nc, 2));
					} else if(d ==2) {
						dfs(new pipe(nr, nc, 1));
					}
				}
			}
		}
		if (present.status == 2) {
			for (int d = 0; d <= 2; d++) {
				int nr = present.x + delta[d][0];
				int nc = present.y + delta[d][1];
				if (nr >= 1 && nr <= N && nc >= 1 && nc <= N && house[nr][nc] != -1) {
					if (d == 1 && house[nr - 1][nc] != -1 && house[nr][nc - 1] != -1) {
						dfs(new pipe(nr, nc, 2));
					} else if (d == 0) {
						dfs(new pipe(nr, nc, 0));
					} else if (d== 2){
						dfs(new pipe(nr, nc, 1));
					}
				}
			}
		}
	}

}
