import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 물통 - 150분
public class BOJ_2251 {
	static StringBuilder sb = new StringBuilder();
	static List<Integer> list;
	static int A, B, C;
	static class Basket {
		int a, b, c;

		public Basket(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}

	private static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		list = new ArrayList<>();

		solution();
	}

	private static void solution() throws Exception {
		bfs();
		Collections.sort(list);
		for (int i : list) {
			sb.append(i).append(" ");
		}
	}

	private static void bfs() {
		boolean visitied[][][] = new boolean[201][201][201];
		Queue<Basket> q = new ArrayDeque<>();
		q.offer(new Basket(0, 0, C));

		while(!q.isEmpty()) {
			Basket basket = q.poll();
			int a = basket.a;
			int b = basket.b;
			int c = basket.c;
			if (visitied[a][b][c]) continue;
			visitied[a][b][c] = true;
			if (a == 0) list.add(c);

			// A 에서 B로 물 이동
			if (a + b > B) {
				q.offer(new Basket(a + b - B, B, c));
			} else {
				q.offer(new Basket(0, a + b, c));
			}

			// A 에서 C로 물 이동
			if (a + c > C) {
				q.offer(new Basket(a + b - C, b, C));
			} else {
				q.offer(new Basket(0, b, a + c));
			}

			// B 에서 A로 물 이동
			if (b + a > A) {
				q.offer(new Basket(A, b + a - A, c));
			} else {
				q.offer(new Basket(b + a, 0, c));
			}

			// B 에서 C로 물 이동
			if (b + c > C) {
				q.offer(new Basket(a, b + c - C, C));
			} else {
				q.offer(new Basket(a, 0, b + c));
			}

			// C 에서 A로 물 이동
			if (c + a > A) {
				q.offer(new Basket(A, b, c + a - A));
			} else {
				q.offer(new Basket(c + a, b, 0));
			}

			// C 에서 B로 물 이동
			if (c + b > B) {
				q.offer(new Basket(a, B, c + b - B));
			} else {
				q.offer(new Basket(a, c + b, 0));
			}
		}
	}
}
