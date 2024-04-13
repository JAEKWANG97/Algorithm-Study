import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_18352 {
	static StringBuilder sb = new StringBuilder();
	static List<Integer> list[];
	
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	private static void init() throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		list = new List[N+1];
		int from, to;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			if (list[from] == null) list[from] = new ArrayList<>();
			list[from].add(to);
		}
		
		solve(K, X);
	}
	
	private static void solve(int K, int X) throws Exception {
		bfs(X, K);
	}

	private static void bfs(int start, int k) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		boolean visited[] = new boolean[list.length+1];
		visited[start] = true;
		
		int time = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			
			if (time++ == k) break;
			for (int i = 0; i < size; i++) {
				int cur = q.poll();
				
				if (list[cur] == null) continue;
				for (int to : list[cur]) {
					if (visited[to]) continue;
					q.offer(to);
					visited[to] = true;
				}
			}
		}
		
		if (q.isEmpty()) {
			sb.append(-1);
		} else {
			q.stream().sorted().forEach(n -> sb.append(n).append("\n"));
		}
	}
}
