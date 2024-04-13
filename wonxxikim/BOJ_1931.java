import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_1931 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); //스위치 개수 받아옴
		int[][] start = new int[N][2];
		for(int i = 0 ; i<N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			start[i][0] = Integer.parseInt(st.nextToken());
			start[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(start, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				if(o1[1]==o2[1]) {
					return o1[0]-o2[0];
				}
				
				return o1[1]-o2[1];
			}
		});
		
		int prev_end = 0;
		int cnt = 0 ;
		for(int i = 0 ; i<N ; i++) {
			if(start[i][0]>=prev_end) {
				cnt++;
				prev_end= start[i][1];
			}
		}
		System.out.println(cnt);
		

	}

}
