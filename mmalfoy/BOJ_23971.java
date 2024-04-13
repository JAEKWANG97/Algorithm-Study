
import java.io.*;
import java.util.*;

public class BOJ_23971 {
	private static int[] P;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		// O(N)
		makeSet(N);

		// O(N^3)
		for (int from = 1; from < N + 1; from++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int to = 1; to < N + 1; to++) {
				int weight = Integer.parseInt(st.nextToken());
				if (weight == 1) {
					union(from, to);
				}
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		int before = Integer.parseInt(st.nextToken());
		int next;
		for (int m = 1; m < M; m++) {
			next = Integer.parseInt(st.nextToken());
			if (find(before) != find(next)) {
				System.out.println("NO");
				return;
			}
			before = next;
		}

		System.out.println("YES");

	}

	private static void makeSet(int N) {
		P = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			P[i] = i;
		}
	}

	private static int find(int node) {
		if (P[node] == node) {
			return node;
		}
		return P[node] = find(P[node]);
	}

	private static void union(int a, int b) {
		int A = find(a);
		int B = find(b);
		if (A != B) {
			P[B] = A;
		}
	}

}
