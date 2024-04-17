package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1253 {
	
	public static void main(String[] args) throws  IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] A = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0 ; i < n; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(A);
		int ans = 0;
		
		
		for(int i = 0; i < n; i++) {
			int l = 0;
			int r = n-1;
			while(true) {
				if(i == l) l ++;
				else if(r == i) r--;
				
				if(l >= r) break;
				
				if(A[r] +A[l] > A[i]) r--;
				else if(A[r] + A[l] < A[i]) l++;
				
				else if(A[r]+A[l] == A[i]) {
					ans++;
					break;
				}
			}
			
		}
		
		System.out.println(ans);
		
	}
}
