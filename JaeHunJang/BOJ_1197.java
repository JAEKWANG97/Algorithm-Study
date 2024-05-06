import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// 1197. 최소 스패닝 트리 / 30분
public class BOJ_1197 {
	static StringBuilder sb = new StringBuilder();
	static int V, E, parents[];
	static Edge[] list;
	static class Edge {
		int from, to, w;

		public Edge(int from, int to, int w) {
			this.from = from;
			this.to = to;
			this.w = w;
		}
	}
	
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		list = new Edge[E];

		parents = new int[V+1];
		for (int i = 1; i <= V; i++) parents[i] = i;

		int from, to, w;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			list[i] = new Edge(from, to, w);
		}

		Arrays.sort(list, new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return Integer.compare(o1.w, o2.w);
			}
		});

		solve();
	}
	
	static void solve() throws Exception {
		long answer = 0;
		int cnt = 0;
		for (int i = 0; i < E; i++) {
			if (union(list[i].from, list[i].to)) {
				answer += list[i].w;
				cnt++;
			}

			if (cnt == V-1) break;
		}
		sb.append(answer);
	}

	static int find(int a) {
		if (parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}

	static boolean union(int a, int b) {
		int pa = find(a), pb = find(b);
		if (pa == pb) return false;
		parents[pa] = pb;

		return true;
	}
}