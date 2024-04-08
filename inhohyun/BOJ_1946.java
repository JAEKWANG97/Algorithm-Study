import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1946 {
	static class rank implements Comparable<rank>{
		int s, m ;
		
		rank(int s, int m){
			this.s = s;
			this.m = m;	
		}

		@Override
		public int compareTo(rank o) {
			//서류순 정렬
			return s - o.s;
		}
	}
	
	public static void main(String[] args) throws  IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int t = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=t; tc++) {
		
			int n = Integer.parseInt(br.readLine());
			rank[] arr = new rank[n];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				
				int s = Integer.parseInt(st.nextToken());
				int m = Integer.parseInt(st.nextToken());
				arr[i] = new rank(s, m);	
			}
			Arrays.sort(arr);
			
			int ans = 1;
			int pivot = arr[0].m; // 기준 값
			for(int i = 1; i < n; i++) {
				if(arr[i].m < pivot) {
					ans++;
					pivot = arr[i].m;
				}
			}
			
			System.out.println(ans);
			
		}
	}

}
