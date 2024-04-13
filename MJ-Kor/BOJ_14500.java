package algorithm_study.day_03_19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500 {
	static int N, M, max = Integer.MIN_VALUE;
	static int[][] map;
	static boolean[][] visited;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				visited[r][c] = true;
				dfs(r, c, 1, map[r][c]);
				visited[r][c] = false;

				comb(0, 0, r, c, map[r][c]); // 인접한 4칸 중 3칸 고르기(ㅗ,ㅓ,ㅏ,ㅜ 모양)
			}
		}

		System.out.println(max);
	}

	private static void comb(int cnt, int start, int r, int c, int sum) {
		if (cnt == 3) {
			max = Math.max(max, sum);
			return;
		}

		for (int d = start; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (nr < 0 || nr >= N || nc < 0 || nc >= M)
				continue;

			comb(cnt + 1, d + 1, r, c, sum + map[nr][nc]);
		}
	}

	private static void dfs(int r, int c, int cnt, int sum) {

		if (cnt == 4) {
			max = Math.max(max, sum);
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (nr < 0 || nr >= N || nc < 0 || nc >= M)
				continue;
			if (visited[nr][nc])
				continue;

			visited[nr][nc] = true;
			dfs(nr, nc, cnt + 1, sum + map[nr][nc]);
			visited[nr][nc] = false;
		}

	}
}
