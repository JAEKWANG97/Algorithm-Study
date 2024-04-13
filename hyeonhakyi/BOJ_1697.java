package ec0227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1697 {
	static int n,m;
	static int[] check;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		check = new int[100001];
		
		bfs(n);
	}
	
	static void bfs(int n) {
		Queue<Integer> que = new LinkedList<>();
		que.offer(n);
		check[n] = 0;
		
		while(!que.isEmpty()) {
			int temp = que.poll();
			for(int i = 0; i < 3; i++) {
				int next;
			
				if(i == 0) {
					next = temp -1;
				}else if(i == 1) {
					next = temp +1;
				}else {
					next = temp*2;
				}
				
				
				if(temp == m) {
					System.out.println(check[temp]);
					return;
				}
				
				
				if(next >= 0 && next < check.length && check[next] == 0) {
					que.offer(next);
					check[next] = check[temp] +1;
				}
			}
		}
	}
}
