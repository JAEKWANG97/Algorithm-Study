
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int V,E, parents[];
	static Node[] list;
	static class Node implements Comparable<Node>{
		int from;
		int to;
		int weight;
		public Node(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight,o.weight);
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		list = new Node[E];

		for(int i = 0 ; i<E ; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			list[i] = new Node(A,B,C);
		}
		
		Arrays.sort(list);
		
		make();
		
		long weight = 0;
		int cnt = 0;
		for(Node n : list) {
			if(! union(n.from,n.to)) continue;
			weight +=n.weight;
			if(cnt++ == V-1) break;
			
		}
		System.out.println(weight);

		
	}
	
	public static void make() {
		parents = new int[V+1];
		for(int i = 1 ; i<=V ; i++) {
			parents[i] = i;
		}
	}
	
	public static int find(int a) {
		if(a== parents[a]) return a;
		return parents[a] = find(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int aroot = find(a);
		int broot = find(b);
		if(broot == aroot) return false;
		parents[broot] = aroot;
		return true;
	}

}
