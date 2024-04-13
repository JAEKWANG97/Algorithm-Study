import java.io.*;
import java.util.*;

public class BOJ_1949 {
	static int[][] G;
	static int[] dx = new int[] {-1, 0, 1, 0};
	static int[] dy = new int[] {0 , -1, 0, 1};
	static int K, max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			G = new int[N][N];
			boolean[][][] visited = new boolean[N][N][2];
			PriorityQueue<Node> pq = new PriorityQueue<Node>();

			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int m = 0; m < N; m++) {
					G[n][m] = Integer.parseInt(st.nextToken());
					// n, m, value, depth, option
					pq.offer(new Node(n, m, G[n][m], 1, 0));
				}
			}
			
			max = 0;
			int value = 0;
			while (pq.peek().value >= value) {
				Node node = pq.poll();
				value = node.value;
				visited[node.n][node.m][0] = true;
				visited[node.n][node.m][1] = true;
				dfs(node, visited);
				visited[node.n][node.m][0] = false;
				visited[node.n][node.m][1] = false;
			}

			sb.append('#').append(t).append(' ').append(max).append('\n');
		}
		System.out.println(sb.toString());
	}
	
	static void dfs(Node node, boolean[][][] visited) {
		for (int i = 0; i < 4; i++) {
			int ny = node.n + dy[i];
			int nx = node.m + dx[i];
			// 좌 상 우 하
			if (ny < 0 || ny > G.length - 1 || nx < 0 || nx > G.length - 1
					|| visited[ny][nx][node.option] 
					|| (G[ny][nx] >= node.value) && node.option == 1 
					|| G[ny][nx] - K >= node.value) {
				continue;
			}
			
			if (G[ny][nx] >= node.value ) {
				visited[ny][nx][1] = true;
				dfs(new Node(ny, nx, node.value - 1, node.depth + 1, 1), visited);
				visited[ny][nx][1] = false;
			} else {
				visited[ny][nx][node.option] = true;
				dfs(new Node(ny, nx, G[ny][nx], node.depth + 1, node.option), visited);
				visited[ny][nx][node.option] = false;
			}
		}
		
		if (node.depth > max) {
			max = node.depth;
		}
	}
}

class Node implements Comparable<Node> {
	int n;
	int m;
	int value;
	int depth;
	int option;
	
	Node (int n, int m, int value, int depth, int option) {
		this.n = n;
		this.m = m;
		this.value = value;
		this.depth = depth;
		this.option = option;
	}

	@Override
	public int compareTo(Node o) {
		return -Integer.compare(this.value, o.value);
	}
	
}
