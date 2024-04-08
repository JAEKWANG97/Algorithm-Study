import java.io.*;
import java.util.*;

public class BOJ_1647 {
	static int[] root;

	static class Edge implements Comparable<Edge> {
		int start, target, cost;

		public Edge(int start, int target, int cost) {
			this.start = start;
			this.target = target;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

	static void init() {
		for (int i = 1; i < root.length; i++) {
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

	static int kruskal(PriorityQueue<Edge> pq, int N) {

		int maxEdgeCost = 0, edgeCount = 0, costSum = 0;

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (edgeCount == N - 1) {
				break;
			}

			if (find(cur.start) != find(cur.target)) {
				union(cur.start, cur.target);
				edgeCount++;
				costSum += cur.cost;
				maxEdgeCost = Integer.max(maxEdgeCost, cur.cost);
			}
		}

		return costSum - maxEdgeCost;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		root = new int[N + 1];
		init();

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			pq.add(new Edge(A, B, C));
		}
		bw.write(String.valueOf(kruskal(pq, N)));
		br.close();
		bw.close();
	}
}
