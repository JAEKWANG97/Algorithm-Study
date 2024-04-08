package ex0405;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_8983 {
	private static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		int[] position = new int[m];
		Node[] animal = new Node[n];
		int count = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			position[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			animal[i] = new Node(x, y);
		}

		Arrays.sort(position);

		for (int i = 0; i < n; i++) {
			int left = 0;
			int right = m - 1;

			while (left <= right) {
				int mid = (left + right) / 2;
				int diff = (Math.abs(position[mid] - animal[i].x) + animal[i].y);

				if (diff > l) {
					if (position[mid] < animal[i].x) {
						left = mid + 1;
					} else if(position[mid] > animal[i].x){
						right = mid - 1;
					}else {
						break;
					}
				}else {
					count++;
					break;
				}
			}
		}

		System.out.println(count);
	}// main end
}// class end
