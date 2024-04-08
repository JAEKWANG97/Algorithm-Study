
import java.io.*;
import java.util.*;

public class BOJ_1697 {
	static int B;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		boolean[] visited = new boolean[100001];
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] {A, 0});
		bfs(q, visited);
	}

	static void bfs(Queue<int[]> q, boolean[] visited) {
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			if (cur[0] == B) {
				System.out.println(cur[1]);
				return;
			}
			if (!visited[cur[0]]) {
				visited[cur[0]] = true;
				if ( 2*cur[0]  < 100001 ) {
					q.offer(new int[] {2*cur[0], cur[1] + 1});
				}
				
				if (cur[0] - 1 >= 0) {
					q.offer(new int[] {cur[0] - 1, cur[1] + 1});
				}
				
				if (cur[0] + 1 < 100001) {
					q.offer(new int[] {cur[0] + 1, cur[1] + 1});
				}
			}
		}
	}
}
