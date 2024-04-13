import java.io.*;
import java.util.*;

public class Main {
	
	static class Node {
		int num, dis;

		public Node(int num, int dis) {
			super();
			this.num = num;
			this.dis = dis;
		}
		
	}
	
	static int bfs() {
		Queue<Node> que = new ArrayDeque<>();
		dist[0] = 0;
		que.offer(new Node(0, 0));
		
		while (!que.isEmpty()) {
			Node cur = que.poll();
			
			if (cur.num == n - 1) { //최단거리 보장되어 있음
				return cur.dis;
			}
			
			for (int i = 1; i <= arr[cur.num]; i++) { //갈 수 있는 칸 만큼
				int nextNum = i + cur.num;
				int nextDis = cur.dis + 1;
				if (nextNum < n && nextDis < dist[nextNum]) {//범위 안 && 다음 최단 거리 < 현재 최단거리
					dist[nextNum] = nextDis;
					que.offer(new Node(nextNum, dist[nextNum]));
				}
				
				
			}
					
		}
		
		return -1;
	}
	
	
	static int n, arr[], dist[];
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n];
		dist = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		
		System.out.println(bfs());
		
	}
}
