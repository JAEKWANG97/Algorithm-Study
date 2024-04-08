import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_13023.java { //백준 13023 ABCDE - 40분
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int n, m;
	static boolean visited[];
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			dfs(i, 0);
		}
		System.out.println(0);
		
	}//end main
	
	private static void dfs(int v, int cnt) {
		if (cnt==5) {
			System.out.println(1);
			System.exit(0);
		}
		for (int i = 0; i < graph.get(v).size(); i++) {
			if (visited[graph.get(v).get(i)]) continue;
			visited[graph.get(v).get(i)] = true;
			dfs(graph.get(v).get(i), cnt+1);
			visited[graph.get(v).get(i)] = false;
		}
	}
}//end class
