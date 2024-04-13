import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static class node implements Comparable<node>{
		int loc;
		int cow;
		int cowsum;
		public node(int loc, int cow, int cowsum) {
			super();
			this.loc = loc;
			this.cow = cow;
			this.cowsum = cowsum;
		}
		@Override
		public int compareTo(node o) {
			// TODO Auto-generated method stub
			return this.cowsum-o.cowsum;
		}
		
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<node>[] list = new ArrayList[N+1];
		for(int i = 1 ; i<=N; i++) {
			list[i] = new ArrayList<>();
		}
		for(int i = 0 ;i<M ; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			list[A].add(new node(B,C,0));
			list[B].add(new node(A,C,0));
		}
		PriorityQueue<node> pq = new PriorityQueue<>();
		pq.add(new node(1,0,0));
		boolean[] visit = new boolean[N+1];
		int answer = 0;
		while(!pq.isEmpty()) {
			node cur = pq.poll();
			if(cur.loc==N) {
				answer = cur.cowsum;
				break;
			}
			if(visit[cur.loc]) continue;
			visit[cur.loc] = true;
			for(node n : list[cur.loc]) {
				pq.add(new node(n.loc,n.cow,cur.cowsum+n.cow));
				}
			}
		
		System.out.println(answer);
	}

}
