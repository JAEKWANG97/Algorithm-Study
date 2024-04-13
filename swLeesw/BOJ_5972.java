import java.io.*;
import java.util.*;

public class Main {
	
	static class Edge {
		int next, weight;

		public Edge(int next, int weight) {
			super();
			this.next = next;
			this.weight = weight;
		}
		
		
	}
	
	static class Node implements Comparable<Node>{
		int num, weightSum;

		public Node(int num, int weightSum) {
			super();
			this.num = num;
			this.weightSum = weightSum;
		}

		public int compareTo(Node o) {
			return this.weightSum - o.weightSum;
		}
		
	}
	
	
	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(1, 0));
		dist[1] = 0;
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			for (int i = 0; i < graph[cur.num].size(); i++) {
				Edge nextEdge = graph[cur.num].get(i);
				if (cur.weightSum + nextEdge.weight < dist[nextEdge.next]) {
					int nWeight = cur.weightSum + nextEdge.weight;
					dist[nextEdge.next] = nWeight;
					pq.offer(new Node(nextEdge.next, nWeight));
				}
			}
			
			
		}
	}
	
	static int n, m, c, dist[];
	static ArrayList<Edge> graph[];
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		dist = new int[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		graph = new ArrayList[n + 1];
		
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[u].add(new Edge(v, w));
			graph[v].add(new Edge(u, w));
		}
		
		dijkstra();
		
		System.out.println(dist[n]);
		
	}
}
