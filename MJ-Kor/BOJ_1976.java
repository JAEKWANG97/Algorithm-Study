package algorithm_study.day_03_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1976 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int V = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		
		int[][] adjMatrix = new int[V][V];
		
		for (int i = 0; i < V; i++) {
			adjMatrix[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		st = new StringTokenizer(br.readLine());
		
		int[] tripVertex = new int[N];
		for (int i = 0; i < N; i++) {
			tripVertex[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		
		boolean[] visited = new boolean[V];
		visited[tripVertex[0]] = true;
		dfs(V, tripVertex[0], tripVertex[N - 1], adjMatrix, visited);
		
		for (int i = 0; i < N; i++) {
			if(!visited[tripVertex[i]]) {
				System.out.println("NO");
				System.exit(0);
			}
		}
		
		System.out.println("YES");
		
	}

	private static void dfs(int V, int start, int end, int[][] adjMatrix, boolean[] visited) {
		
		for (int i = 0; i < V; i++) {
			if(adjMatrix[start][i] == 1 && !visited[i]) {
				visited[i] = true;
				dfs(V, i, end, adjMatrix, visited);
			}
		}

	}
	
}
