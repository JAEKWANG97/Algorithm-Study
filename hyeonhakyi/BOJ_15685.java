package ex0328;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15685 {
	private static int[] dx = { 0, -1, 0, 1 };
	private static int[] dy = { 1, 0, -1, 0 };
	private static boolean[][] arr = new boolean[101][101];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int ans = 0;
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int ger = Integer.parseInt(st.nextToken());

			curve(x, y, dir, ger);
		}

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (arr[i][j] && arr[i][j + 1] && arr[i + 1][j] && arr[i + 1][j + 1]) {
					ans++;
				}
			}
		}
		System.out.println(ans);
	}// main end

	private static void curve(int x, int y, int d, int g) {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(d);

		for (int i = 1; i <= g; i++) {
			for (int j = list.size() - 1; j >= 0; j--) {
				list.add((list.get(j) + 1) % 4);
			}
		}

		arr[x][y] = true;
		for (Integer direction : list) {
			y += dx[direction];
			x += dy[direction];
			arr[x][y] = true;
		}
	}
}// class end
