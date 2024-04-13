package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1949 {
	static int[][] delta = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
	static int map[][], K, result, N;
	static ArrayList<Node> tops;

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			int top = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					top = Math.max(top, map[i][j]);
				}
			}

			// 봉우리 리스트
			tops = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == top)
						tops.add(new Node(i, j));
				}
			}

			result = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 1; k <= K; k++) {
						if (map[i][j] - k == -1)
							break;
						map[i][j] -= k;
						bfs();
						map[i][j] += k;
					}
				}
			}
			System.out.println("#" + tc + " " + result);

		}
	}

	public static void bfs() {
		for (Node x : tops) {
			Queue<Node> q = new LinkedList<>();
			q.add(x);
			int[][] visit = new int[N][N];
			visit[x.x][x.y] = 1;
			int answer = 1;
			while (!q.isEmpty()) {
				Node cur = q.poll();
				for (int d = 0; d < 4; d++) {
					int nr = cur.x + delta[d][0];
					int nc = cur.y + delta[d][1];
					if (!(nr >= 0 && nr < N && nc >= 0 && nc < N))
						continue;
					if (map[cur.x][cur.y] <= map[nr][nc])
						continue;

					if (visit[nr][nc] < visit[cur.x][cur.y] + 1) {
						visit[nr][nc] = visit[cur.x][cur.y] + 1;
						q.add(new Node(nr, nc));
						answer = Math.max(answer, visit[nr][nc]);
					}

				}
			}
			result = Math.max(result, answer);
		}
	}

}
