import java.util.*;
import java.io.*;

public class BOJ_18352 {
	
	
	static int n, k, m, x, sol, weight[];
	static ArrayList<Integer> list[];
	
	
	static void dijsktra() {
		Queue<Integer> que = new ArrayDeque<>();
		que.offer(x);
		weight[x] = 0;
		
		while (!que.isEmpty()) {
			int cur = que.poll();
			
			for (int i = 0; i < list[cur].size(); i++) {
				int next = list[cur].get(i);
				if (weight[cur] + 1 < weight[next]) {
					weight[next] = weight[cur] + 1;
					que.offer(next);
				}
			}
			
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<>();
		}
		
		weight = new int[n + 1];
		Arrays.fill(weight, Integer.MAX_VALUE);
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int tu = Integer.parseInt(st.nextToken());
			int tv = Integer.parseInt(st.nextToken());
			list[tu].add(tv);
		}
		
		
		dijsktra();
		boolean flag = false;
		for (int i = 1; i <= n; i++) {
			if (weight[i] == k) {
				flag = true;
				System.out.println(i);
			}
		}
		if (!flag) {
			System.out.println(-1);
		}
	}
	
}
