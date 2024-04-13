import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_2251 {
	static class Basket {
		int curVolume;
		int maxVolume;

		public Basket(int curVolume, int maxVolume) {
			super();
			this.curVolume = curVolume;
			this.maxVolume = maxVolume;
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static Set<Integer> set;
	static Basket A;
	static Basket B;
	static Basket C;
	static boolean[][][] visited;
	static List<Integer> answer;

	public static void main(String[] args) throws IOException {
		init();
		dfs(A.curVolume, B.curVolume, C.curVolume);
		Collections.sort(answer);
		for (int n : answer) {
			sb.append(n).append(" ");
		}
		System.out.println(sb);
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		int item = Integer.parseInt(st.nextToken());
		A = new Basket(0, item);
		item = Integer.parseInt(st.nextToken());
		B = new Basket(0, item);
		item = Integer.parseInt(st.nextToken());
		C = new Basket(item, item);
		set = new HashSet<>();
		answer = new ArrayList<>();

		visited = new boolean[A.maxVolume + 1][B.maxVolume + 1][C.maxVolume + 1];

	}

	private static void dfs(int a, int b, int c) {
		if (a == 0) {
			if (!set.contains(c)) {
				set.add(c);
				answer.add(c);
			}
		}
		
		if (visited[a][b][c]) {
			return;
		}

		if (!visited[a][b][c]) {
			visited[a][b][c] = true;

			dfs(a + c > A.maxVolume ? A.maxVolume : a + c, b, a + c > A.maxVolume ? c - (A.maxVolume - a) : 0); // c->a
			dfs(a, b + c > B.maxVolume ? B.maxVolume : b + c, b + c > B.maxVolume ? c - (B.maxVolume - b) : 0); // c->b
			dfs(a + b > A.maxVolume ? A.maxVolume : a + b, a + b > A.maxVolume ? b - (A.maxVolume - a) : 0, c); // b->a
			dfs(a, c + b > C.maxVolume ? b - (C.maxVolume - c) : 0, c + b > C.maxVolume ? B.maxVolume : c + b); // b->c
			dfs(a + b > B.maxVolume ? a - (B.maxVolume - b) : 0, a + b > B.maxVolume ? B.maxVolume : a + b, c); // a->b
			dfs(a + c > C.maxVolume ? a - (C.maxVolume - c) : 0, b, a + c > C.maxVolume ? C.maxVolume : a + c); // a->c

			visited[a][b][c] = false;
		}
	}
}
