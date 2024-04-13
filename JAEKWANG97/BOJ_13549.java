import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13549 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static int N, M;
	private static int MAX = 100001;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		init();
		bfs();
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	}

	private static void bfs() {
		Queue<Integer> que = new ArrayDeque<>();
		int[] visited = new int[MAX];

		que.add(N);
		Arrays.fill(visited,Integer.MAX_VALUE);
		visited[N] = 0;
		


		while (!que.isEmpty()) {
			int size = que.size();
			while (size-- > 0) {
				int cur = que.poll();

				if (cur == M) {
					System.out.println(visited[M]);
					return;
				}

				if (cur * 2 < MAX && visited[cur] < visited[cur * 2]) {
					que.add(cur * 2);
					visited[cur * 2] = visited[cur];

				}
				if (cur + 1 < MAX && visited[cur] + 1 < visited[cur + 1]) {
					que.add(cur + 1);
					visited[cur + 1] = visited[cur] + 1;

				}
				if (cur - 1 >= 0 && visited[cur] + 1 < visited[cur - 1]) {
					que.add(cur - 1);
					visited[cur - 1] = visited[cur] + 1;
				}
			}
		}

	}

}
