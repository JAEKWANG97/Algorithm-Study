import java.io.*;
import java.util.*;

public class BOJ_11657 {
	private static final int INF = Integer.MAX_VALUE;
	static boolean cycleFlag;

	static class Edge {
		int startId, targetId, cost;

		public Edge(int startId, int targetId, int cost) {
			this.startId = startId;
			this.targetId = targetId;
			this.cost = cost;
		}
	}

	private static void bellmanFord(int N, List<Edge> edges, long[] distance) {
		for (int i = 1; i < N; i++) {
			for (Edge edge : edges) {
				if (distance[edge.startId] == INF) {
					continue;
				}

				distance[edge.targetId] = Math.min(distance[edge.targetId], distance[edge.startId] + edge.cost);
			}
		}

		for (Edge edge : edges) {
			if (distance[edge.startId] == INF) {
				continue;
			}
			if (distance[edge.targetId] > distance[edge.startId] + edge.cost) {
				cycleFlag = true;
				return;
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
		List<Edge> edges = new ArrayList<>();
		long[] distance = new long[N + 1];
		cycleFlag = false;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges.add(new Edge(a, b, c));
		}

		for (int i = 1; i <= N; i++) {
			distance[i] = INF;
		}
		distance[1] = 0;

		bellmanFord(N, edges, distance);

		if (cycleFlag) {
			sb.append("-1");
		} else {
			for (int i = 2; i <= N; i++) {
				sb.append(distance[i] == INF ? "-1" : distance[i]).append("\n");
			}
		}
		bw.write(sb.toString());

		br.close();
		bw.close();
	}
