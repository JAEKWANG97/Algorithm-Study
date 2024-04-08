import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 해킹 - 60분
public class BOJ_10282 {
	static StringBuilder sb = new StringBuilder();
	static int N, D, C;

	static class Node {
		int from, to, time;

		public Node(int from, int to, int time) {
			this.from = from;
			this.to = to;
			this.time = time;
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}

	private static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			List<Node>[] list = new List[N+1];

			int from, to, time;
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				to = Integer.parseInt(st.nextToken());
				from = Integer.parseInt(st.nextToken());
				time = Integer.parseInt(st.nextToken());

				if (list[from] == null) list[from] = new ArrayList<>();
				list[from].add(new Node(from, to, time));
			}

			solution(list);

		}
	}

	private static void solution(List<Node>[] list) {
		int maxTime = 0;
		int totalCnt = 0;
		boolean[] visited = new boolean[N+1];
		int[] minWeight = new int[N+1];

		Arrays.fill(minWeight, Integer.MAX_VALUE);
		minWeight[C] = 0;

		// 다익스트라
		int min = 0, minIdx = 0;
		for (int i = 1; i <= N; i++) {
			min = Integer.MAX_VALUE;
			minIdx = -1;

			for (int j = 1; j <= N; j++) {
				if (!visited[j] && min > minWeight[j]) {
					min = minWeight[j];
					minIdx = j;
				}
			}

			if (minIdx == -1) break;
			totalCnt++;
			maxTime = Math.max(min, maxTime);
			visited[minIdx] = true;

			if (list[minIdx] == null) continue;
			for (Node node : list[minIdx]) {
				if (minWeight[node.to] > min + node.time) {
					minWeight[node.to] = min + node.time;
				}
			}
		}


		sb.append(totalCnt).append(" ").append(maxTime).append("\n");
	}
}
