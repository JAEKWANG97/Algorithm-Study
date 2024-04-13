import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Town implements Comparable<Town>{
		int from, to, cost;

		public Town(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Town o) {
			return cost - o.cost;
		}	
	}
	
	private static Town[] towns;
	private static int[] parents;
 	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n,m;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		towns = new Town[m];
		parents = new int[n+1];
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			towns[i] = new Town(from, to, cost);
		}
		
		Arrays.sort(towns);
		make(n);
		
		List<Integer> costs = new ArrayList<>();
		for(Town town : towns) {
			if(union(town.from,town.to)) {
				costs.add(town.cost);
			}
		}
		
		Collections.sort(costs);
		costs.remove(costs.size()-1);
		int result = costs.stream().mapToInt(Integer::intValue).sum();
		System.out.println(result);
	}
	
	private static void make(int n) {
		for(int i=1; i<=n; i++) {
			parents[i] = i;
		}
	}
	
	private static int find(int x) {
		if(x == parents[x]) {
			return x;
		}
		
		return parents[x] = find(parents[x]);
	}
	
	private static boolean union(int a,int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) {
			return false;
		}
		
		parents[aRoot] = bRoot;
		return true;
	}
}

