package algorithm_study.day_04_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_13023 {

	private static int N, M, a, b;
	private static boolean[] visited;
	private static List<ArrayList<Integer>> graph = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		
		// 양방향 그래프 생성
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		// 생성한 그래프 확인
//		for (int i = 0; i < M; i++) {
//			System.out.print(i + ": ");
//			for (int j = 0; j < graph.get(i).size(); j++) {
//				System.out.print(graph.get(i).get(j) + " ");
//			}
//			System.out.println();
//		}
		
		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			visited[i] = true;
			dfs(graph.get(i), 0);
		}
		
		System.out.println(0);
		
	}
	
	public static void dfs(ArrayList<Integer> e, int cnt) {
		if(cnt == 4) {
			System.out.println(1);
			System.exit(0);
			return;
		}
		
		for (int i = 0; i < e.size(); i++) { 
			if(!visited[e.get(i)]) {
				//System.out.println();
				visited[e.get(i)] = true;
				dfs(graph.get(e.get(i)), cnt + 1);
				visited[e.get(i)] = false;
			}
		}
	}
}
