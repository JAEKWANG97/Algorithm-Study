import java.io.*;
import java.util.*;

public class Main {
	
	
	static class Edge {
		int u, v, w;

		public Edge(int u, int v, int w) {
			super();
			this.u = u;
			this.v = v;
			this.w = w;
		}
	}
	
	static int n, m, connected, sol;
	static int arr[];
	public static void main(String[] args) throws IOException {
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		arr = new int[n + 1];
		
		for (int i = 0; i <= n; i++) {
			arr[i] = i;
		}
		
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			pq.offer(new Edge(u, v, w));
		}
		
		while (!pq.isEmpty()) {
			//간선 빼서 연결
			Edge cur = pq.poll();
			
			if (union(cur.u, cur.v)) {
				sol += cur.w;
				if (++connected == n - 1) break;				
			}
		}
		
		System.out.println(sol);
	}
	
	static int find(int x) {
		if (arr[x] == x) return x; //자기 자신 기리킴
		return arr[x] = find(arr[x]);
	}
	
	static boolean union(int x, int y) {
		int tx = find(x);
		int ty = find(y);
		
		if (tx == ty) return false;
		if (tx > ty) {
			arr[tx] = ty;
		} else {
			arr[ty] = tx;
		}
		
		return true;
	}
	
	
}
