
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, parents[];
	static class road implements Comparable<road>{
		int from;
		int to;
		int cost;
		public road(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(road o) {
			return this.cost-o.cost;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		road[] roads = new road[M];
		for(int i = 0 ; i<M ; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			roads[i] = new road(A,B,C);
		}
		
		make();
		Arrays.sort(roads);
		int result = 0 ;
		int cnt = 0 ;
		for(road cur : roads) {
			if(cnt == N-2) break;
			if(!union(cur.from,cur.to)) continue;
			result+=cur.cost;
			cnt++;
		}
		System.out.println(result);

	}
	public static void make() {
		parents = new int[N+1];
		for(int i = 1 ; i<=N; i++) {
			parents[i] = i ;
		}
	}
	
	public static int find(int A) {
		if(parents[A] == A) return A;
		else return parents[A] = find(parents[A]);
	}
	
	public static boolean union(int A, int B) {
		int roota = find(A);
		int rootb = find(B);
		
		if(roota==rootb) return false;
		parents[rootb] = roota;
		return true;
	}

}
