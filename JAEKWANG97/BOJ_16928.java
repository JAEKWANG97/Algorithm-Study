import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] map;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		init();
		bfs();
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[101];

		for (int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			map[from] = to;
		}
	}

	private static void bfs() {

		Queue<Integer> que = new ArrayDeque<>();
		boolean[] visited = new boolean[101];

		que.add(1);
		visited[1] = true;

		int time = 0;
		while (!que.isEmpty()) {
			int size = que.size();
			while (size-- > 0) {
				int cur = que.poll();
				if (cur == 100) {
					System.out.println(time);
					return;
				}
				for (int i = 1; i <= 6; i++) {
					int next = cur + i;
					if (cur + i > 100 || visited[cur + i]) {
						continue;
					}

					if (map[next] != 0) {
						que.add(map[next]);
						visited[map[next]] = true;
					} else {
						que.add(next);
					}
					visited[next] = true;
				}
			}
			time += 1;
		}
	}

}
