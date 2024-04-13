import java.io.*;
import java.util.*;

public class BOJ_18352 {
	private static final int INF = Integer.MAX_VALUE;

	static class Edge implements Comparable<Edge> {
		int targetId, cost;

		public Edge(int targetId, int cost) {
			this.targetId = targetId;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}

	}

	static void dijkstra(int X, int[] distance, List<Edge>[] edges) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		distance[X] = 0;
		pq.add(new Edge(X, 0));

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (distance[cur.targetId] < cur.cost) {
				continue;
			}

			for (Edge next : edges[cur.targetId]) {
				int weightSum = distance[cur.targetId] + next.cost;
				if (weightSum < distance[next.targetId]) {
					distance[next.targetId] = weightSum;
					pq.add(new Edge(next.targetId, distance[next.targetId]));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		int[] distance = new int[N + 1];
		Arrays.fill(distance, INF);

		List<Edge>[] edges = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			edges[A].add(new Edge(B, 1));
		}

		dijkstra(X, distance, edges);

		for (int i = 1; i <= N; i++) {
			if (distance[i] == K && i != X) {
				sb.append(i).append("\n");
			}
		}
		if (sb.length() == 0) {
			sb.append(-1);
		}
		bw.write(sb.toString());

		br.close();
		bw.close();
	}
}