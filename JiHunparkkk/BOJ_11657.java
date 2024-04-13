import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Node{
		int from, to, cost;

		public Node(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}		
	}
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n,m;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		List<Node> list = new ArrayList<>();
	
		for(int i=0; i<m; i++){
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			list.add(new Node(from, to, cost));
		}
		
		long[] costs = new long[n+1];
		Arrays.fill(costs, Long.MAX_VALUE);
		costs[1] = 0;
		
		for(int i=1; i<=n; i++) {
			for(Node node : list) {
				if(costs[node.from] == Long.MAX_VALUE) {
					continue;
				}
				
				if(costs[node.to] > costs[node.from] + node.cost) {
					if(i==n) {
						System.out.println(-1);
						return;
					}
					costs[node.to] = costs[node.from] + node.cost;
				}
			}
		}
		
		for(int i=2; i<=n; i++) {
			if(costs[i] == Long.MAX_VALUE) {
				System.out.println(-1);
				continue;
			}
			System.out.println(costs[i]);
		}
	}
}

