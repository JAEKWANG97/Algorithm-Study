import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 네트워크 복구 / 90분
public class BOJ_2211 {
	static StringBuilder sb = new StringBuilder();

	static class Node implements Comparable<Node> {
		int to, w;

		public Node(int to, int w) {
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		List<Node>[] adjList = new ArrayList[V+1];
		for (int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			adjList[from].add(new Node(to, w));
			adjList[to].add(new Node(from, w));
		}

		boolean[] visited = new boolean[V+1];
		int[] dist = new int[V+1];
		int[] prev = new int[V+1];
		Arrays.fill(dist, Integer.MAX_VALUE);

		PriorityQueue<Node> pq = new PriorityQueue<>();

		dist[1] = 0;
		pq.offer(new Node(1, 0));

		while (!pq.isEmpty()) {
			Node curNode = pq.poll();
			int cur = curNode.to;

			if (visited[cur]) continue;
			visited[cur] = true;

			for (Node next : adjList[cur]) {
				if (!visited[next.to] && dist[next.to] > dist[cur] + next.w) {
					dist[next.to] = dist[cur] + next.w;
					prev[next.to] = cur;
					pq.offer(new Node(next.to, dist[next.to]));
				}
			}
		}

		int count = 0;
		for (int i = 1; i <= V; i++) {
			if (prev[i] != 0) count++;
		}

		System.out.println(count);
		for (int i = 1; i <= V; i++) {
			if (prev[i] != 0) {
				System.out.println(i + " " + prev[i]);
			}
		}
	}
}