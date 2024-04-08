import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] delta = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][C];
		Queue<int[]> fire = new LinkedList<>();
		Queue<int[]> jihun = new LinkedList<>();
		boolean[][] visit = new boolean[R][C];
		boolean start = false;
		for (int i = 0; i < R; i++) {
			char[] str = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				map[i][j] = str[j];
				if (map[i][j] == 'J') {
					jihun.add(new int[] { i, j });
					map[i][j] = '.';
					visit[i][j] = true;
					if (i == 0 || i == R - 1 || j == 0 || j == C - 1)
						start = true;
				}
				if (map[i][j] == 'F') {
					fire.add(new int[] { i, j });
				}
			}
		}
		if (start == true)
			System.out.println(1);
		else {
			int time = 1;
			boolean flag = false;
			while (true) {
				int size = fire.size();
				for (int i = 0; i < size; i++) {
					int[] cur = fire.poll();
					for (int d = 0; d < 4; d++) {
						int nr = cur[0] + delta[d][0];
						int nc = cur[1] + delta[d][1];
						if (nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] == '.') {
							map[nr][nc] = 'F';
							fire.add(new int[] { nr, nc });
						}
					}
				}
				int size_j = jihun.size();
				for (int i = 0; i < size_j; i++) {
					int[] cur = jihun.poll();
					for (int d = 0; d < 4; d++) {
						int nr = cur[0] + delta[d][0];
						int nc = cur[1] + delta[d][1];
						if (nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] == '.' && !visit[nr][nc]) {
							if (nr == 0 || nr == R - 1 || nc == 0 || nc == C - 1) {
								flag = true;
							}
							jihun.add(new int[] { nr, nc });
							visit[nr][nc] = true;
						}

					}
				}
				time++;
				if (jihun.isEmpty() || flag == true)
					break;

			}

			if (flag == false)
				System.out.println("IMPOSSIBLE");
			else
				System.out.println(time);
		}

	}
}
