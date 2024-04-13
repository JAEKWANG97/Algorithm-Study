package week10.day5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2589 {
	static class Location {
		int x;
		int y;
		int time;

		public Location(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	static Location answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		char[][] map = new char[n][m];

		List<Location> startPoint = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = input.charAt(j);
				if (map[i][j] == 'L') {
					startPoint.add(new Location(i, j));
				}
			}
		}

		for (Location start : startPoint) {
			bfs(map, n, m, start);
		}

		System.out.println(answer.time - 1);

	}

	static void bfs(char[][] map, int n, int m, Location start) {
		Queue<Location> que = new ArrayDeque<>();
		boolean[][] visited = new boolean[n][m];
		Location lastLocation = null;
		que.offer(start);
		visited[start.x][start.y] = true;

		int[] deltaX = new int[] { 0, 1, 0, -1 };
		int[] deltaY = new int[] { -1, 0, 1, 0 };

		int time = 0;

		while (!que.isEmpty()) {
			int size = que.size();
			while (size-- > 0) {
				Location cur = que.poll();
				lastLocation = cur;
				for (int i = 0; i < 4; i++) {
					int nx = cur.x + deltaX[i];
					int ny = cur.y + deltaY[i];

					if (nx < 0 || nx >= n || ny < 0 || ny >= m)
						continue;

					if (visited[nx][ny])
						continue;

					if (map[nx][ny] == 'L') {
						que.offer(new Location(nx, ny));
						visited[nx][ny] = true;
					}
				}
			}
			time += 1;
		}

		lastLocation.time = time;
		if (answer == null) {
			answer = lastLocation;
		} else {
			if (answer.time < lastLocation.time) {
				answer = lastLocation;
			}
		}
	}

}
