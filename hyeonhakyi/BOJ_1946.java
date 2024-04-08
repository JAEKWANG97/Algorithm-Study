package ex0404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1946 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < t; tc++) {
			int n = Integer.parseInt(br.readLine());
			int[] arr = new int[n];
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				arr[a-1] = b;
			}
			
			int count = 1;
			int rating = arr[0];
			for(int i = 0; i < n; i++) {
				if(rating > arr[i]) {
					count++;
					rating = arr[i];
				}
			}
			System.out.println(count);
		}//testCase end
	}//main end
}//class end
