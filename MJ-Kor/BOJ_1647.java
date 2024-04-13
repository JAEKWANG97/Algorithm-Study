package algorithm_study.day_03_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1647 {
	
	private static class Edge implements Comparable<Edge>{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		Edge[] edges = new Edge[E];
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(from, to, weight);
		}
		
		Arrays.sort(edges);
//		System.out.println();
		
		int result = kruskal(V, edges);
		
		System.out.println(result);
	}

	private static int kruskal(int V, Edge[] edges) {
		int[] P = new int[V + 1];
		
		make(P, V);
		
		int weightSum = 0;
		int cnt = 0;
		int max = Integer.MIN_VALUE;
		for(Edge edge : edges) {
			if(!union(P, edge.from, edge.to)) continue;
			weightSum += edge.weight;
			max = Math.max(max, edge.weight);
			cnt++;
//			System.out.println(edge.from + " " + edge.to + " " + edge.weight);
			if(cnt == V - 1) break;
		}
//		System.out.println(cnt);
		return weightSum - max;
	}

	private static void make(int[] P, int V) {
		for (int i = 1; i <= V; i++) {
			P[i] = i;
		}
	}
	
	private static int find(int[] P, int a) {
		if(P[a] == a) return a;
		return P[a] = find(P, P[a]);
	}
	
	private static boolean union(int[] P, int a , int b) {
		if(find(P, a) == find(P, b)) {
			return false;
		}
		else {
			P[find(P, b)] = find(P, a);
			return true;
		}
	}

}
