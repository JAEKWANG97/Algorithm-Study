import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 13023. ABCDE / 60분
public class BOJ_13023 {
	static StringBuilder sb = new StringBuilder();
	static int N, M, result;
	static List<Integer> list[];
	static boolean visited[];
	public static void main(String[] args) throws Exception {
		init();
		solve();
		System.out.println(sb.toString());
	}

	private static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 사람 수
		M = Integer.parseInt(st.nextToken()); // 친구 관계 수
		result = 0; // 관계 여부
		
		list = new List[N]; // 그래프 만들기
		visited = new boolean[N];
		int from, to;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			
			if (list[from] == null) list[from] = new ArrayList<>();
			list[from].add(to);
			
			if (list[to] == null) list[to] = new ArrayList<>();
			list[to].add(from);
		}
	}
	
	private static void solve() throws Exception {
		for (int i = 0; i < N; i++) {
			if (result == 1) break; // 결과가 이미 나왔으면 탐색 중지
			visited = new boolean[N];
			visited[i] = true;
			dfs(i, 1); // 각 노드별로 탐색 시작
		}
		sb.append(result);
	}
	
	private static void dfs(int start, int count) {
		if (result == 1) return;

		if (count == 5) { // 관계가 연결된 친구 5명이 있으면 result 갱신
			result = 1;
			return;
		}
		if (list[start] == null) return;
		
		for (int i : list[start]) {
			if (visited[i]) continue;
			visited[i] = true;
			dfs(i, count+1);
			visited[i] = false;
		}
	}
}
