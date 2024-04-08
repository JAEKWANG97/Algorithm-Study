package ex0403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3055 {
	private static class Node {
		int x;
		int y;
		int time;

		public Node(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

	private static int r, c, result;
	private static char[][] arr;
	private static Queue<Node> hozer = new LinkedList<>();
	private static Queue<Node> water = new LinkedList<>();
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		arr = new char[r][c];

		for (int i = 0; i < r; i++) {
			String s = br.readLine();
			for (int j = 0; j < c; j++) {
				arr[i][j] = s.charAt(j);

				if (arr[i][j] == 'S') {
					hozer.offer(new Node(i, j, 0));
				} else if (arr[i][j] == '*') {
					water.offer(new Node(i, j, 0));
				}
			}
		}
		bfs();
		
		if(result == 0) {
			System.out.println("KAKTUS");
		}else {
			System.out.println(result);
		}
	}// main end

	private static void bfs() {
		while (!hozer.isEmpty()) {
			int len = water.size();
			for (int i = 0; i < len; i++) {
				Node now = water.poll();

				for (int d = 0; d < 4; d++) {
					int nx = now.x + dx[d];
					int ny = now.y + dy[d];

					if (check(nx, ny) && (arr[nx][ny] == '.' || arr[nx][ny] == 'S')) {
						water.offer(new Node(nx, ny, 0));
						arr[nx][ny] = '*';
					}
				}
			}

			len = hozer.size();
			for (int i = 0; i < len; i++) {
				Node now = hozer.poll();

				for (int d = 0; d < 4; d++) {
					int nx = now.x + dx[d];
					int ny = now.y + dy[d];
					
					if (check(nx, ny) && (arr[nx][ny] == '.' || arr[nx][ny] == 'D')) {
						if (arr[nx][ny] == 'D') {
							result = now.time+1;
							return;
						}
						
						hozer.offer(new Node(nx, ny, now.time + 1));
						arr[nx][ny] = 'S';
					}
				}
			}
		}
	}// bfs end

	private static boolean check(int x, int y) {
		return x >= 0 && y >= 0 && x < r && y < c;
	}// check end
}// class end
