package ex0305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_18352 {
	private static class Node implements Comparable<Node>{
		int vertex;
		int weight;
		public Node(int vertex,int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight,o.weight);
		}
		
	}
	private static int n,m,k,x,count;
	private static ArrayList<Node>[] list;
	private static boolean[] visited;
	private static int[] ans;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		list = new ArrayList[n+1];
		visited = new boolean[n+1];
		ans = new int[n+1];
		Arrays.fill(ans,Integer.MAX_VALUE);
		
		
		for(int i = 1; i <= n; i++) {
			list[i] = new ArrayList<>();
		}
		
		ans[x] = 0;
		for(int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			list[x].add(new Node(y, 1));
		}
		dijk(x);
		
		for(int i = 1; i <= n; i++) {
			if(ans[i] == k) {
				sb.append(i).append("\n");
			}
		}
		if(sb.length() == 0) {
			System.out.println(-1);
		}else {
			System.out.println(sb);
		}
	}//main end
	
	private static void dijk(int k) {
		PriorityQueue<Node> que = new PriorityQueue<>();
		que.offer(new Node(k, 0));
		
		while(!que.isEmpty()) {
			Node node = que.poll();
			
			
			if(!visited[node.vertex]) {
				visited[node.vertex] = true;
			}
			
			for(Node next : list[node.vertex]) {
				if(!visited[next.vertex] && ans[next.vertex] > node.weight + next.weight) {
					ans[next.vertex] = node.weight + next.weight;
					que.offer(new Node(next.vertex, ans[next.vertex]));
				}
			}
		}
	}
}//class end
