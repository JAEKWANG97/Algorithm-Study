import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	private static class Node implements Comparable<Node> {
		int a, b, weight;

		public Node(int a, int b, int weight) {
			this.a = a;
			this.b = b;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o1) {
			return Integer.compare(weight, o1.weight);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int V, E;
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] parent = new int[V + 1];
		for (int i = 0; i <= V; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a, b, c;

			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			pq.add(new Node(a, b, c));
		}

		int answer = 0;
		while (!pq.isEmpty()) {
			Node poll = pq.poll();

			int from = find(poll.a, parent);
			int to = find(poll.b, parent);
			if (union(from, to, parent)) {
				answer += poll.weight;
			}
		}
		System.out.println(answer);
	}

	private static int find(int x, int[] parent) {
		if (x == parent[x]) {
			return x;
		}
		return parent[x] = find(parent[x], parent);
	}

	private static boolean union(int x, int y, int[] parent) {
		int x_p = find(x, parent);
		int y_p = find(y, parent);

		if (x_p == y_p) {
			return false;
		}

		parent[x_p] = y_p;
		return true;
	}
}
