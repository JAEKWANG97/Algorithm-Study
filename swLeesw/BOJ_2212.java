import java.util.*;
import java.io.*;



public class BOJ_2212 {
	
	static int n, k, arr[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		Integer dis[] = new Integer[n - 1];
		
		for (int i = 0; i < n - 1; i++) {
			dis[i] = arr[i + 1] - arr[i];
		}
		Arrays.sort(dis, Collections.reverseOrder());
		int sol = 0;
		for (int i = k - 1; i < n - 1; i++) {
			sol += dis[i];
		}
		
		System.out.println(sol);
	}
	
}
