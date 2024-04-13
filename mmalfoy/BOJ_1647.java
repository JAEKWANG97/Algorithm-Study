
import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
	int from;
	int to;
	int weight;
	Edge (int from, int to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.weight, o.weight);
	}
}

public class BOJ_1647 {
	private static int[] P;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			pq.offer(new Edge(from, to, weight));
		}
		
		makeSet(N);
		int cnt = 0, maxEdge = 0, sumEdge = 0;
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			if (union(edge.from, edge.to)) {
				sumEdge += edge.weight;
				maxEdge = Math.max(maxEdge, edge.weight);
			}
		}
		System.out.println(sumEdge - maxEdge);
	}
	
	private static void makeSet(int N) {
		P = new int[N + 1];
		for (int n = 1; n < N + 1; n++) {
			P[n] = n;
		}
	}
	
	private static int find(int node) {
		if (P[node] == node) {
			return node;
		}
		return P[node] = find(P[node]);
	}
	
	private static boolean union(int from, int to) {
		int fromP = find(from);
		int toP = find(to);
		
		if (fromP == toP) {
			return false;
		}
		
		P[toP] = fromP;
		return true;
		
	}
}
