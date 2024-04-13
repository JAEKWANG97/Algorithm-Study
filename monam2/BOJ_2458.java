import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2458 { //백준 2458 키 순서 - 50분
	static int inf = Integer.MAX_VALUE / 2;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] graph = new int[n + 1][n + 1];
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				graph[i][j] = inf;
				if (i == j) {
					graph[i][j] = 0;
				}
			}
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a][b] = 1;
		}

		for (int mid = 1; mid < n + 1; mid++) {
			for (int start = 1; start < n + 1; start++) {
				for (int end = 1; end < n + 1; end++) {
					if (graph[start][end] > graph[start][mid] + graph[mid][end] && start != end) {
						graph[start][end] = graph[start][mid] + graph[mid][end];
					}
				}
			}
		}

		int[] from = new int[n + 1];
		int[] to = new int[n + 1];
		for (int k = 1; k < n + 1; k++) {
			for (int i = 1; i < n + 1; i++) {
				if (graph[k][i] != 0 && graph[k][i] != inf) {
					from[k]++;
				}
				if (graph[i][k] != 0 && graph[i][k] != inf) {
					to[k]++;
				}
			}
		}

		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 1; i < n + 1; i++) {
			if (from[i] + to[i] == n - 1) {
				result.add(i);
			}
		}
		System.out.println(result.size());
	}// main
}// class
