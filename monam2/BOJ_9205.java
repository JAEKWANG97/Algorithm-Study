import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_9205 { //백준 9205 맥주 마시면서 걸어가기 - 50분
	private static class Node {
		int x,y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}//Node

	static int n;
	static boolean[] visited;
	static ArrayList<Node> list;
	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;


		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			n = Integer.parseInt(br.readLine());

			list = new ArrayList<Node>();

			for (int i = 0; i < n+2; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				list.add(new Node(a, b));
			}

			graph = new ArrayList<>();
			for (int i = 0; i < n+2; i++) {
				graph.add(new ArrayList<>());
			}

			for (int i = 0; i < n+2; i++) {
				for (int j = i+1; j < n+2; j++) {
					if (getManhattanDist(list.get(i), list.get(j)) <= 1000) {
						graph.get(i).add(j);
						graph.get(j).add(i);
					}
				}
			}

			visited = new boolean[n+2];
			if (bfs()) {
				System.out.println("happy");
			} else {
				System.out.println("sad");
			}

		}//for T
	}//main
	private static int getManhattanDist(Node node1, Node node2) {
		return Math.abs(node1.x - node2.x) + Math.abs(node1.y - node2.y);
	}//getManhattanDist

	private static boolean bfs() {
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		q.offer(0);
		visited[0] = true;

		while (!q.isEmpty()) {
			int x = q.poll();

			if (x==n+1) { //도착점이랑 같을때 종료
				return true;
			}

			for (int i = 0; i < graph.get(x).size(); i++) {
				int nx = graph.get(x).get(i);
				if (!visited[nx]) {
					visited[nx] = true;
					q.offer(nx);
				}
			}
		}

		return false;
	}//bfs
}//class
