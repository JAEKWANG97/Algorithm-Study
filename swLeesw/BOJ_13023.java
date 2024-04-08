import java.util.*;
import java.io.*;
public class BOJ_13023 {
	
	static int n, m, cnt;
	static boolean visited[];
	static ArrayList<Integer> graph[];

	static boolean dfs(int s, int cnt) {
		visited[s] = true;
		if (cnt == 4) {
			return true;
		}
		for (int i = 0; i < graph[s].size(); i++) {
			if (!visited[graph[s].get(i)]) {
				if (dfs(graph[s].get(i), cnt + 1)) {
					return true;
				}
			}
		}
		visited[s] = false;
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[n];
		visited = new boolean[n];
		
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
			graph[v].add(u);
		}
		
		for (int i = 0; i < n; i++) {
			if (dfs(i, 0)) {
				System.out.println(1);
				return;
			}
		}
		System.out.println(0);
	}
	
}
