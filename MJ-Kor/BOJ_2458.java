package algorithm_study.day_04_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2458 {
	
	private static int cnt = 0;
	private static int V;
	private static int[][] adjMatrix;
	private static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			cnt = 0;
			V = Integer.parseInt(br.readLine());
			int E = Integer.parseInt(br.readLine());
			adjMatrix = new int[V + 1][V + 1];
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				adjMatrix[start][end] = 1;
			}
			
			for (int i = 1; i <= V; i++) {				
				solution(i);
			}
			System.out.println("#" + tc + " " + cnt);
		}
	}

	private static void solution(int target) {
		visited = new boolean[V + 1];
		boolean bigger = true;
		
		// target보다 큰 학생들 확인
		bfs(target, bigger);
		
		// target보다 작은 학생들 확인
		bigger = false;
		bfs(target, bigger);
		visited[target] = true;
		cnt++;
		for (int i = 1; i <= V; i++) {
			if(!visited[i]) {
				cnt--;
				break;
			}
		}
	}

	private static void bfs(int start, boolean bigger) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			for (int j = 1; j <= V; j++) {			
				if(bigger) {
					if(adjMatrix[curr][j] == 1 && !visited[j]) {
						visited[j] = true;
						queue.offer(j);
					}
				} else {
					if(adjMatrix[j][curr] == 1 && !visited[j]) {
						visited[j] = true;
						queue.offer(j);
					}
				}

			}
		}
	}
}
