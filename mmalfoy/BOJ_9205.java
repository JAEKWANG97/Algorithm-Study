
import java.util.*;
import java.io.*;

public class BOJ_9205 {
	static int[][] places;
	static int[] D;
	static int n;
	static class Node{
		int to;
		int weak;
		Node link;
		public Node(int to, int weak, Node link) {
			super();
			this.to = to;
			this.weak = weak;
			this.link = link;
		}
	}
	static Node[] nodes;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		for(int t=1; t<=T; t++) {
			n = Integer.parseInt(bf.readLine());
			places = new int[n+2][2];
			for(int i=0; i<n+2; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
				places[i][0] = Integer.parseInt(st.nextToken());
				places[i][1] = Integer.parseInt(st.nextToken());
			}
			
			nodes = new Node[n+2];
			for(int i=0; i<n+2; i++) {
				for(int j=i+1; j<n+2; j++) {
					if(i == j) continue;
					int weak = Math.abs(places[i][0]-places[j][0])+Math.abs(places[i][1]-places[j][1]);
					if(weak <= 1000) {
						nodes[i] = new Node(j, weak, nodes[i]);
						nodes[j] = new Node(i, weak, nodes[j]);
					}
				}
			}
			
			D = new int[n+2];
			Arrays.fill(D, 987654321);
			PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
			D[0] = 0;
			pq.offer(new int[] {0, 0});
			while(!pq.isEmpty()) {
				int[] now = pq.poll();
				if(now[1] > D[now[0]]) continue;
				for(Node n = nodes[now[0]]; n!= null; n = n.link)  {
					if(D[n.to] > now[1] + n.weak) {
						D[n.to] = now[1] + n.weak;
						pq.offer(new int[] {n.to, D[n.to]});
					}
				}
			}
			
			System.out.println(D[n+1] == 987654321 ? "sad" : "happy");
		}
	}
}
