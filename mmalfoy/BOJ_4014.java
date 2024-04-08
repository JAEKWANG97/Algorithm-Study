import java.io.*;
import java.util.*;

public class BOJ_4014 {
	private static int[][] G;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			G = new int[N][N];
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int m = 0; m < N; m++) {
					G[n][m] = Integer.parseInt(st.nextToken());
				}
			}

			int cntCol = 0;
			for (int m = 0; m < N; m++) {
				int n = 0, cntUp = 0, before = G[0][m];
				boolean isPassed = true;
				for (n = 0; n < N; n++) {
					// 같은 높이
					if (G[n][m] == before) {
						before = G[n][m];
						cntUp += 1;

						if (!isPassed && cntUp == X) {
							cntUp = 0;
							isPassed = true;
						}
						continue;

					}

					// 낮 -> 높
					if (G[n][m] > before) {
						
						if (G[n][m] != before + 1) {
							break;
						}
						
						if (cntUp < X) {
							break;
						}
						if (!isPassed && cntUp < X) {
							break;
						}
						cntUp = 1;
						isPassed = true;
						before = G[n][m];
						continue;
					} else {
						// 높 -> 낮
						if (G[n][m] != before - 1) {
							break;
						}
						
						if ((!isPassed && cntUp < X)) {
							break;
						}
						cntUp = 1;
						before = G[n][m];
						isPassed = false;

					}
				}
				if (n == N) {
					if (!isPassed && cntUp < X)
						continue;
					cntCol += 1;
				}
			}
			int cntRow = 0;
			for (int n = 0; n < N; n++) {
				int m = 0, cntUp = 0, before = G[n][0];
				boolean isPassed = true;
				for (m = 0; m < N; m++) {
					// 같은 높이
					if (G[n][m] == before) {
						before = G[n][m];
						cntUp += 1;

						if (!isPassed && cntUp == X) {
							cntUp = 0;
							isPassed = true;
						}
						continue;

					}

					// 낮 -> 높
					if (G[n][m] > before) {
						if (G[n][m] != before + 1) {
							break;
						}
						
						if (cntUp < X) {
							break;
						}
						if (!isPassed && cntUp < X) {
							break;
						}
						cntUp = 1;
						isPassed = true;
						before = G[n][m];
						continue;
					} else {
						// 높 -> 낮
						if (G[n][m] != before - 1) {
							break;
						}
						
						if ((!isPassed && cntUp < X)) {
							break;
						}
						cntUp = 1;
						before = G[n][m];
						isPassed = false;
					}
				}
				if (m == N) {
					if (!isPassed && cntUp < X)
						continue;
					cntRow += 1;
				}
			}
			sb.append('#').append(t).append(' ').append(cntCol + cntRow).append('\n');
		}
		System.out.println(sb.toString());
	}
}
