package algorithm_study.day_03_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_11657 {
	
	private static class Edge{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
		
	}
	
	private static final long INF = Long.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		List<Edge> edges = new ArrayList<>();
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edges.add(new Edge(from, to, weight));
		}
		
		int start = 1;
		
		bellman_ford(start, V, E, edges);
	}

	private static void bellman_ford(int start, int V, int E, List<Edge> edges) {
		long[] dist = new long[V + 1];
		Arrays.fill(dist, INF);
		dist[start] = 0L;
		
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < E; j++) {
				Edge edge = edges.get(j);
				
				if(dist[edge.from] != INF && dist[edge.to] > dist[edge.from] + edge.weight) {
					dist[edge.to] = dist[edge.from] + edge.weight;  
				}
			}
		}
		
		for (int i = 0; i < E; i++) {
			Edge edge = edges.get(i);
			
			if(dist[edge.from] != INF && dist[edge.to] > dist[edge.from] + edge.weight) {
				System.out.println(-1);
				System.exit(0);
			}
				
		}
		
		for (int i = 1; i < dist.length; i++) {
			if(i == start) continue;
			if(dist[i] == INF) {
				System.out.println(-1);
			}
			else {
				System.out.println(dist[i]);
			}
		}
	}

}
