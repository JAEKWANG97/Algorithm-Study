import java.util.*;
import java.io.*;

public class BOJ_13549 {
	
	static class Info {
		int num, weight;

		public Info(int num, int weight) {
			super();
			this.num = num;
			this.weight = weight;
		}
		
	}
	
	static int n, k, visited[];
	
	static boolean isRange(int x) {
		if (x >= 0 && x <= 100000) return true;
		else return false;
	}
	
	static int bfs(int s) {
		visited[s] = 0;
		PriorityQueue<Info> que = new PriorityQueue<>((o1, o2) -> {
			return o1.weight - o2.weight;
		});
		que.offer(new Info(s, 0));
		
		while (!que.isEmpty()) {
			Info cur = que.poll();
			if (cur.num == k) return cur.weight;
			if (isRange(cur.num + 1) && visited[cur.num + 1] > cur.weight + 1) {
				visited[cur.num + 1] = cur.weight + 1;
				que.offer(new Info(cur.num + 1, cur.weight + 1));
			}
			if (isRange(cur.num - 1) && visited[cur.num - 1] > cur.weight + 1) {
				visited[cur.num - 1] = cur.weight + 1;
				que.offer(new Info(cur.num - 1, cur.weight + 1));
			}
			if (isRange(cur.num * 2) && visited[cur.num * 2] > cur.weight) {
				visited[cur.num * 1] = cur.weight;
				que.offer(new Info(cur.num * 2, cur.weight));				
			}
			
		}
		
		return visited[k];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		visited = new int[100001];
		Arrays.fill(visited, 2100000000);
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		System.out.println(bfs(n));
		
	}

}