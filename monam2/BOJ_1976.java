import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1976 { //백준 1976 여행가자 - 40분

	static int n,m,goal,plan[], map[][];
	static boolean isTripOK, visited[];
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < n+1; i++) {
			graph.add(new ArrayList<Integer>());
		}
		map = new int[n+1][n+1];
		for (int i = 1; i < n+1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j < n+1; j++ ) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j]==1) {
					graph.get(i).add(j);
					graph.get(j).add(i);
				}
			}
		}
		plan = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			plan[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < m-1; i++) {
			isTripOK = false;
			visited = new boolean[n+1];

			dfs(plan[i], plan[i+1]);

			if (!isTripOK) break;
		}

		if (isTripOK) System.out.println("YES");
		else System.out.println("NO");
	}//main

	private static void dfs(int start, int goal) {
//		System.out.println("현재 방문 노드 : " + start);
		if (start == goal) {
			isTripOK = true;
			return;
		}
		visited[start] = true;
		for (int i = 0; i < graph.get(start).size(); i++) {
			if (!visited[graph.get(start).get(i)]) {
				dfs(graph.get(start).get(i), goal);
			}
		}
	}//dfs
}//class
