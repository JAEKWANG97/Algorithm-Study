import java.io.*;
import java.util.*;

public class BOJ_1976 {
	static int[] root;

	static void init() {
		for (int i = 0; i < root.length; i++) {
			root[i] = i;
		}
	}

	static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if (pa < pb) {
			root[pb] = pa;
		} else {
			root[pa] = pb;
		}
	}

	static int find(int x) {
		if (root[x] == x) {
			return x;
		}
		return root[x] = find(root[x]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		boolean isPossible = true;
		root = new int[N];
		init();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int link = Integer.parseInt(st.nextToken());
				if (link == 1) {
					union(i, j);
				}
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		int start = Integer.parseInt(st.nextToken()) - 1;
		for (int i = 1; i < M; i++) {
			int next = Integer.parseInt(st.nextToken()) - 1;
			if (find(start) != find(next)) {
				isPossible = false;
				break;
			}
		}
		bw.write(!isPossible ? "NO" : "YES");

		br.close();
		bw.close();
	}
}