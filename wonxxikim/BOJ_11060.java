import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	static class node implements Comparable<node>{
		int idx;
		int cnt;
		public node(int idx, int cnt) {
			this.idx = idx;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(node o) {
			// TODO Auto-generated method stub
			return this.cnt-o.cnt;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		int[] visit = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i<N ; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.fill(visit, Integer.MAX_VALUE);
		visit[0] = 0 ;
		for(int i = 0 ; i<N-1; i++) {
			for(int j = 1 ; j<=A[i]; j++) {
				if(i+j<=N-1 && visit[i]!=Integer.MAX_VALUE && visit[i+j]>visit[i]+1) {
					visit[i+j] = visit[i] +1;
				}
			}
		}
		if(visit[N-1]==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(visit[N-1]);
	}

}
