import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 백준 - 도시 분할 계획
public class BOJ_1647 {
	static StringBuilder sb = new StringBuilder();
	static Edge list[];
	static int parents[];

	static class Edge {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Edge{" +
					"from=" + from +
					", to=" + to +
					", weights=" + weight +
					'}';
		}
	}
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	private static void init() throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 집의 개수
		int M = Integer.parseInt(st.nextToken()); // 길의 개수

		list = new Edge[M]; // 간선리스트
		int f, t, w;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			f = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			list[i] = new Edge(f,t,w);
		}

		parents = new int[N+1]; // make-set
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}

		solve(N);
	}
	
	private static void solve(int N) throws Exception {

		Arrays.sort(list, new Comparator<Edge>() { // 가중치 오름차순 정렬
			@Override
			public int compare(Edge o1, Edge o2) {
				return Integer.compare(o1.weight, o2.weight);
			}
		});

		long cnt = 0, weight = 0;
		for (Edge edge : list) { // 크루스칼
			if (!union(edge.from, edge.to)) continue;
			weight += edge.weight;
			if (++cnt == N-1) { // 두 마을로 나눌 것이기 때문에 마지막 최대 간선 비용은 빼줌
				weight -= edge.weight;
				break;
			}
		}

		sb.append(weight).append("\n");
	}

	static int find(int n) {
		if (parents[n] == n) return parents[n];
		return parents[n] = find(parents[n]);
	}

	static boolean union(int a, int b) {
		int pA = find(a);
		int pB = find(b);
		if (pA == pB) return false;
		parents[pA] = pB;
		return true;
	}
}

