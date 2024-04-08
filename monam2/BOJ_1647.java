import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1647 { //백준 1647 도시 분할 계획 - 60분
	private static class Edge implements Comparable<Edge>{
		int from, end, cost;

		public Edge(int from, int end, int cost) {
			super();
			this.from = from;
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.cost, o.cost);
		}
		
	}//Edge
	
	static int n, m;
	static Edge[] edgeList;
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		parent = new int[n+1];
		edgeList = new Edge[m];
		for (int i = 1; i < n+1; i++) {
			parent[i] = i;
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(a,b,c);
		}
		
		Arrays.sort(edgeList);
		
		int result = 0;
		int maxCost = 0;
		
		for (int i = 0; i < edgeList.length; i++) {
			Edge edge = edgeList[i];
			
			if (!union(edge.from, edge.end)) {
				result += edge.cost;
				maxCost = edge.cost;
			}
		}
		
		System.out.println(result - maxCost);
		
		
		
		
		
	}//main
	
	private static int find(int x) {
		if (parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
	private static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if (rootA == rootB) { //조상이 같으면 합치면 안됨
			return true;
		} else {
			if (rootA > rootB) {
				parent[rootB] = rootA;
			} else {
				parent[rootA] = rootB;
			}
			return false;
		}
	}
	
	
	/*
	 * 크루스칼
	 * - 그래프의 간선들을 가중치의 오름차순으로 정렬
	 * - 사이클을 형성하는지 확인
	 * 		-> 부모가 동일한지 확인(find)
	 * 		-> 사이클을 형성하지 않으면 union(합치기)
	 * 
	 */
}
