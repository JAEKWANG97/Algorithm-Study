package algorithm_study.day_03_25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13549 {
	private static int N, K;
	private static int min = Integer.MAX_VALUE;
	private static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new boolean[100001];
		bfs();
		System.out.println(min);
	}

	public static void bfs() {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {N, 0});
		visited[N] = true;
		while(!queue.isEmpty()) {
			int[] curr = queue.poll();
			
			if(curr[0] == K) min = Math.min(min, curr[1]);

			if(curr[0] * 2 <= 100000 && !visited[curr[0] * 2]) {
				queue.offer(new int[] {curr[0] * 2, curr[1]});
				visited[curr[0] * 2] = true;
			}

			if(curr[0] - 1 >= 0 && !visited[curr[0] - 1]) {
				queue.offer(new int[] {curr[0] - 1, curr[1] + 1});
				visited[curr[0] - 1] = true;
			}
			
			if(curr[0] + 1 <= 100000 && !visited[curr[0] + 1]) {
				queue.offer(new int[] {curr[0] + 1, curr[1] + 1});
				visited[curr[0] + 1] = true;
			}					
		}
	}
}
