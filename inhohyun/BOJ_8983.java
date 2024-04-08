import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_8983 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		int []mm = new int[m];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<m;i++) mm[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(mm);
		int answer = 0;
		for(int i=0; i<n;i++) {
			st = new StringTokenizer(br.readLine());
			int x=  Integer.parseInt(st.nextToken());
			int y=  Integer.parseInt(st.nextToken());
			if(x <=mm[0]) {
				if(l-((mm[0]-x)+y) >=0) {
					answer++;
				}
			} else if(x>=mm[m-1]) {
				if(l-((x-mm[m-1])+y) >=0) {
					answer++;
				}
			} else {
				int s = 0;
				int e = m-1;
				while(s<e) {
					int mid = (s+e)>>1;
					if(mm[mid]==x) {
						if(l-y >=0) {
							answer++;
						}
						break;
					} else if(mm[mid] < x) {
						s = mid;
					} else {
						e = mid;
					}
					
					if(s+1==e && mm[s]<x && mm[e]>x) {
						if(l-(x-mm[s]+y) >= 0) {
							answer++;
							break;
						}
						if(l-(mm[e]-x+y) >= 0) {
							answer++;
							break;
						}
						break;
					}
				}
			}
		}
		System.out.println(answer);
	}
}
