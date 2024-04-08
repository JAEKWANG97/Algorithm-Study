package ex0314;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_5972 {
	private static class Eat implements Comparable<Eat>{
		int index;
		int value;
		public Eat(int index,int value) {
			this.index = index;
			this.value = value;
		}
		@Override
		public int compareTo(Eat o) {
			return Integer.compare(this.value, o.value);
		}
	}
	private static int n,m;
	private static int[] arr;
	private static boolean[] check;
	private static ArrayList<Eat>[] list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n+1];
		check = new boolean[n+1];
		list = new ArrayList[n+1];
		int INF = Integer.MAX_VALUE;
		
		for(int i = 1; i < n+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		Arrays.fill(arr, INF);
		arr[1] = 0;
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			list[a].add(new Eat(b, c));
			list[b].add(new Eat(a, c));
		}
		
		
		bfs();
		
		System.out.println(arr[n]);
	}//main end
	
	private static void bfs() {
		PriorityQueue<Eat> que = new PriorityQueue<>();
		que.offer(new Eat(1, 0));
		
		while(!que.isEmpty()) {
			Eat eat = que.poll();
			int vertex = eat.index;
			
			if(check[vertex]) {
				continue;
			}
			
			check[vertex] = true;
			
			for(Eat next : list[vertex]) {
				if(arr[next.index] > arr[vertex] + next.value) {
					arr[next.index] = arr[vertex] + next.value;
					
					que.offer(new Eat(next.index, arr[next.index]));
				}
			}
		}
	}//bfs end
}//class end
