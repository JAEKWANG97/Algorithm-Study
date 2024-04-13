package week10.day5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206 {
	static class Location {
		int x;
		int y;
		int k;

		public Location(int x, int y, int k) {
			super();
			this.x = x;
			this.y = y;
			this.k = k;
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] map = new int[n][m];

		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}

		int answer = bfs(map, n, m);

		System.out.println(answer);

	}

	private static int bfs(int[][] map, int n, int m) {
		Queue<Location> que = new ArrayDeque<>();
		que.offer(new Location(0, 0, 0));

		boolean[][][] visited = new boolean[2][n][m];
		visited[0][0][0] = true;
		int[] deltaX = new int[] { 0, 1, 0, -1 };
		int[] deltaY = new int[] { -1, 0, 1, 0 };
		int time = 0;
		while (!que.isEmpty()) {
			int size = que.size();
			while (size-- > 0) {
				Location cur = que.poll();
				if (cur.x == n - 1 && cur.y == m - 1) {
					return time + 1;
				}
				for (int i = 0; i < 4; i++) {
					int nx = cur.x + deltaX[i];
					int ny = cur.y + deltaY[i];

					if (nx < 0 || nx >= n || ny < 0 || ny >= m)
						continue;

					if (visited[cur.k][nx][ny]) {
						continue;
					}

					if (map[nx][ny] == 0) {
						que.offer(new Location(nx, ny, cur.k));
						visited[cur.k][nx][ny] = true;
					}

					if (cur.k < 1 && map[nx][ny] == 1) {
						que.offer(new Location(nx, ny, cur.k + 1));
						visited[cur.k + 1][nx][ny] = true;
					}

				}
			}
			time += 1;
		}
		return -1;
	}

}
