package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4014 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int result = 0;
			for (int i = 0; i < N; i++) {
				boolean flag = true;
				boolean[] visit = new boolean[N];
				for (int j = 1; j < N; j++) {
					if (flag == false)
						break;
					int diff = Math.abs(map[i][j] - map[i][j - 1]);
					if (diff > 1)
						flag = false;
					if (diff == 0)
						continue;
					if (diff == 1) {
						if (map[i][j] > map[i][j - 1]) {
							if (j - X < 0)
								flag = false;
							else {
								for (int k = j - 1; k > j - 1 - X; k--) {
									if (map[i][k] == map[i][j - 1] && !visit[k])
										visit[k] = true;
									else
										flag = false;

								}
							}
						} else {
							if (j + X > N)
								flag = false;
							else {
								for (int k = j; k < j + X; k++) {
									if (map[i][k] == map[i][j] && !visit[k])
										visit[k] = true;
									else
										flag = false;
								}
							}
						}

					}

				}
				if (flag == true)
					result++;

			}
			for (int j = 0; j < N; j++) {
				boolean flag = true;
				boolean[] visit = new boolean[N];
				for (int i = 1; i < N; i++) {
					if (flag == false)
						break;
					int diff = Math.abs(map[i][j] - map[i - 1][j]);
					if (diff > 1)
						flag = false;
					if (diff == 0)
						continue;
					if (diff == 1) {
						if (map[i][j] > map[i - 1][j]) {
							if (i - X < 0)
								flag = false;
							else {
								for (int k = i - 1; k > i - 1 - X; k--) {
									if (map[k][j] == map[i - 1][j] && !visit[k])
										visit[k] = true;
									else
										flag = false;

								}
							}

						} else {
							if (i + X > N)
								flag = false;
							else {
								for (int k = i; k < i + X; k++) {
									if (map[k][j] == map[i][j] && !visit[k])
										visit[k] = true;
									else
										flag = false;
								}
							}
						}

					}

				}
				if (flag == true)
					result++;

			}
			System.out.println("#" + tc + " " + result);

		}
	}

}
