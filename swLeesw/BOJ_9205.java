import java.io.*;
import java.util.*;

public class BOJ_9205 {
	static class Node {
		int y, x;

		public Node(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	
	
	
	static boolean canConnect(Node a, Node b) {
		int dis = Math.abs(a.y - b.y) + Math.abs(a.x - b.x);
		if (dis <= 1000) {
			return true;
		} else {
			return false;
		}
	}
	
	static void bfs() {
		Queue<Integer> que = new ArrayDeque<>();
		que.offer(0);
		visited[0] = false;
		while (!que.isEmpty()) {
			int cur = que.poll();
			if (cur == n + 1) {
				System.out.println("happy");
				return;
			}
			for (int i = 0; i < graph[cur].size(); i++) {
				int next = graph[cur].get(i);
				if (!visited[next]) {
					que.offer(next);
					visited[next] = true;
				}
				
			}
			
			
		}
		
		System.out.println("sad");
	}
	
	static int t, n, x, y;
	static boolean visited[];
	static List<Integer> graph[];
	static List<Node> posList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		t = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			visited = new boolean[n + 2];
			graph = new ArrayList[n + 2];
			posList = new ArrayList<>();
			for (int i = 0; i < n + 2; i++) {
				graph[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < n + 2; i++) {
				st = new StringTokenizer(br.readLine());
				y = Integer.parseInt(st.nextToken());
				x = Integer.parseInt(st.nextToken());
				posList.add(new Node(y, x));
			}
			
			for (int i = 0; i < n + 1; i++) {
				for (int j = i + 1; j < n + 2; j++) {
					if (canConnect(posList.get(i), posList.get(j))) {
						graph[i].add(j);
						graph[j].add(i);
					}
				}
			}
			bfs();
		}
		
		
	}	
}