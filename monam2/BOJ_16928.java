import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_16928 { //백준 16928 뱀과 사다리 게임 - 80분

	static int n, m, map[], visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		map = new int[101];
		visited = new int[101];
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			map[from] = end;
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			map[from] = end;
		}

		bfs();
		System.out.println(visited[100]);
	}//main

	private static void bfs() {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.offer(1);
		visited[1] = 0;

		while(!q.isEmpty()) {
			int x = q.poll();
			if (x==100) {
				break;
			}

			for (int i = 1; i < 7; i++) {
				int nx = x + i;

				if (nx>100) continue;

				if (map[nx]==0 && visited[nx]==0) {
					q.offer(nx);
					visited[nx] = visited[x] + 1;
				}
				// 사다리 / 뱀
				else if (map[nx] != 0) {
					if (visited[map[nx]] == 0) {
						visited[map[nx]] = visited[x]+1;
						q.offer(map[nx]);
					}
				}
			}
		}
	}//bfs
}//class
