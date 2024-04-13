import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4179 {
	static class Location {
		int x;
		int y;

		public Location(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	static int N, M;
	static char[][] map;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static Queue<Location> fireList;
	static Location person;

	static int[] deltaX = new int[] { 0, 1, 0, -1 };
	static int[] deltaY = new int[] { -1, 0, 1, 0 };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		init();
		escape();
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		fireList = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				char item = input.charAt(j);
				map[i][j] = item;

				if (item == 'J') {
					person = new Location(i, j);
				} else if (item == 'F') {
					fireList.add(new Location(i, j));
				}
			}
		}
	}

	private static void escape() {
		Queue<Location> que = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];

		que.add(person);
		int time = 0;

		while (!que.isEmpty()) {
			int size = que.size();
			fireMove();
			while (size-- > 0) {
				Location cur = que.poll();
				if ((cur.x == N - 1 || cur.y == 0 || cur.y == M - 1 || cur.x == 0)) {
					System.out.println(time + 1);
					return;
				}
				for (int i = 0; i < 4; i++) {
					int nx = cur.x + deltaX[i];
					int ny = cur.y + deltaY[i];

					if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) {
						continue;
					}

					if (map[nx][ny] == '.') {
						que.add(new Location(nx, ny));
						visited[nx][ny] = true;
					}
				}
			}

			time += 1;
		}
		System.out.println("IMPOSSIBLE");
		return;
	}

	private static void fireMove() {
		int size = fireList.size();
		while (size-- > 0) {
			Location cur = fireList.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + deltaX[i];
				int ny = cur.y + deltaY[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
					continue;
				}
				if (map[nx][ny] == '.' || map[nx][ny] == 'J') {
					map[nx][ny] = 'F';
					fireList.add(new Location(nx, ny));
				}
			}
		}
	}
}
