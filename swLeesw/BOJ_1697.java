import java.util.*;
import java.io.*;


public class BOJ_1697 {
	
	static int n, k, visited[];
	
	static boolean isRange(int x) {//범위 탐색
		if (x >= 0 && x <= 100000) {
			return true;
		}
		return false;
	}
	
	static int bfs() {
		visited = new int[100001];
		Arrays.fill(visited, Integer.MAX_VALUE);
		Queue<Integer> que = new ArrayDeque<>();
		que.offer(n);
		visited[n] = 0;
		while (!que.isEmpty()) {
			int cur = que.poll();
			if (cur == k) return visited[k];
			
			//-1, +1, *2
			int a = cur - 1;
			int b = cur + 1;
			int c = cur * 2;
			//값이 더 작으면 insertQueue
			if (isRange(a) && visited[a] > visited[cur] + 1) {
				que.offer(a);
				visited[a] = visited[cur] + 1;
			}
			if (isRange(b) && visited[b] > visited[cur] + 1) {
				que.offer(b);
				visited[b] = visited[cur] + 1;
			}
			if (isRange(c) && visited[c] > visited[cur] + 1) {
				que.offer(c);				
				visited[c] = visited[cur] + 1;
			}
			
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		k = sc.nextInt();
		System.out.println(bfs());
		
	}
	
}
