import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1946 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		while(tc-->0) {
			int n = Integer.parseInt(br.readLine());
			
			int[] arr = new int[n];
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				arr[a-1] = b;
			}
			int cnt =1;
			int rating = arr[0];
			for(int i=1; i<n; i++) {
				if(rating>arr[i]) {
					cnt++;
					rating = arr[i];
				}
			}
			sb.append(cnt+"\n");
		}
		System.out.println(sb.toString());
	}
}
