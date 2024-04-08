import java.io.*;
import java.util.*;

public class BOJ_3055 {
	static char[][] G;
	static boolean[][] waterVisit, gosmVisit;

	static class Node {
		int y, x, depth;

		Node(int y, int x, int depth) {
			this.y = y;
			this.x = x;
			this.depth = depth;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		G = new char[R][C];
		boolean[][] visited = new boolean[R][C];

		// bfs 물 큐, 고슴도치 큐
		Queue<Node> waterQ = new ArrayDeque<>();
		Queue<Node> gosmQ = new ArrayDeque<>();

		for (int r = 0; r < R; r++) {
			String str = br.readLine();
			for (int c = 0; c < C; c++) {
				G[r][c] = str.charAt(c);
				if (G[r][c] == 'S') { // 물이 이동할 수 있게 . 으로 치환하여 저장
					G[r][c] = '.';
					visited[r][c] = true;
					gosmQ.add(new Node(r, c, 0));
				} else if (G[r][c] == '*') {
					waterQ.add(new Node(r, c, 0));
				}
			}
		}

		// 고슴도치가 더이상 이동할 수 없을때까지 bfs
		int[][] d = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		int time = 0;
		while (!gosmQ.isEmpty()) {
			// 물 먼저 time 같은 노드들만 bfs
			while (!waterQ.isEmpty() && waterQ.peek().depth == time) {
				Node water = waterQ.poll();
				for (int i = 0; i < 4; i++) {
					int ny = water.y + d[i][0];
					int nx = water.x + d[i][1];

					if (ny < 0 || ny > R - 1 || nx < 0 || nx > C - 1 || G[ny][nx] != '.') {
						continue;
					}

					G[ny][nx] = '*';
					waterQ.offer(new Node(ny, nx, water.depth + 1));
				}
			}

			// 그 후 고슴도치 이동
			while (!gosmQ.isEmpty() && gosmQ.peek().depth == time) {
				Node gosm = gosmQ.poll();
				
				for (int i = 0; i < 4; i++) {
					int ny = gosm.y + d[i][0];
					int nx = gosm.x + d[i][1];

					if (ny < 0 || ny > R - 1 || nx < 0 || nx > C - 1 || G[ny][nx] == '*' || G[ny][nx] == 'X'
							|| visited[ny][nx]) {
						continue;
					}
					
					if (G[ny][nx] == 'D') {
						System.out.println(gosm.depth + 1);
						return;
					}

					visited[ny][nx] = true;
					G[ny][nx] = 'S';
					gosmQ.offer(new Node(ny, nx, gosm.depth + 1));
				}

			}
			
			time += 1;
		}

		// 고슴도치가 더이상 이동할 수 없음
		System.out.println("KAKTUS");

	}
}