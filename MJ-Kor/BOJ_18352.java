package algorithm_study.day_03_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_18352 {

	private static class Node{
		int vertex;
		int weight;
		Node next;
		
		public Node(int vertex, int weight, Node next) {
			super();
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
		
		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", weight=" + weight + ", next=" + next + "]";
		}
	}
	
	private static class queueData{
		Node node;
		int breadth;
		
		public queueData(Node node, int breadth) {
			super();
			this.node = node;
			this.breadth = breadth;
		}
		
		@Override
		public String toString() {
			return "queueData [node=" + node + ", breadth=" + breadth + "]";
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		Node[] adjList = new Node[N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, 1, adjList[from]);
		}
		
		bfs(N, X, K, adjList);
		
	}

	private static void bfs(int N, int X, int K, Node[] adjList) {
		int[] visited = new int[N + 1];
		Arrays.fill(visited, Integer.MAX_VALUE);
		Queue<queueData> queue = new ArrayDeque<>();
		visited[X] = 0;
		for (Node temp = adjList[X]; temp != null ; temp = temp.next) {
			queue.offer(new queueData(temp, 1));
		}
		while(!queue.isEmpty()) {
			queueData curr = queue.poll();
			if(visited[curr.node.vertex] == Integer.MAX_VALUE) {
				visited[curr.node.vertex] = curr.breadth; 
			}
			for (Node temp = adjList[curr.node.vertex]; temp != null ; temp = temp.next) {
				if(visited[temp.vertex] == Integer.MAX_VALUE) {					
					queue.offer(new queueData(temp, curr.breadth + 1));
				}
			}
		}
		
		int cnt = 0;
		
		for (int i = 1; i <= N; i++) {
			if(visited[i] == K) {
				System.out.println(i);
				cnt++;
			}
		}
		
		if(cnt == 0) {
			System.out.println(-1);
		}

	}

}
