package algorithm_study.day_02_29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110 {

	private static int N;
	private static int C;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		int[] homes = new int[N];
		
		for (int i = 0; i < N; i++) {
			homes[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(homes);
		
		int left = 0;
		int right = homes[N - 1] - homes[0];
		int answer = 0;
		
		while(left <= right) {
			int cnt = 1;
			int prev = homes[0];
			
			int mid = (left + right) / 2;
			
			for (int i = 1; i < N; i++) {
				if((homes[i] - prev) >= mid) {
					cnt += 1;
					prev = homes[i];
				}
			}
			
			if(cnt < C) {
				 right = mid - 1;
			}
			else {
				 answer = mid;
				 left = mid + 1;
			}
			
		}
		System.out.println(answer);
	}
}
