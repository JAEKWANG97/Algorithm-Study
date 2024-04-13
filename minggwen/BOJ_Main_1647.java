import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Main_1647 {

	static class Edge implements Comparable<Edge>{
		int start,to,weight;
		
		public Edge(int start,int to, int weight) {
			this.start = start;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.weight-o.weight;
		}

		@Override
		public String toString() {
			return "Edge [to=" + to + ", weight=" + weight + "]"+"\n";
		}
		
	}
	static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Queue<Edge> edges = new PriorityQueue<>();
		parents = new int[N+1];
		for(int m=0;m<M;m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges.add(new Edge(a,b,w));
		}
		for(int n=1;n<=N;n++) {
			parents[n]=n;
		}
		int max = 0;
		int result =0;
		while(!edges.isEmpty()) {
			Edge e = edges.poll();
			int a = e.start;
			int b = e.to;
			if(union(a,b)) {
				max = e.weight;
				result+=e.weight;
			}
		}
		System.out.println(result-max);
	}
	private static int find(int a) {
		if(parents[a]==a) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
	private static boolean union(int a,int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa==pb) return false;
		if(pa>pb) {
			parents[pa] = pb;
		}else {
			parents[pb] = pa;
		}
		return true;
	}
}
