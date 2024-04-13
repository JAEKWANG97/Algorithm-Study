package ex0229;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import java.util.Arrays;
import java.util.*;

public class BOJ_2110 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		
		int low = 1;
		int height = arr[n-1];
		
		while(low <= height) {
			int mid = (low + height)/2;
			
			int position = 0;
			int count = 1;
			for(int i = 1; i < n; i++) {
				if(arr[i] - arr[position] >= mid) {
					position = i;
					count++;
				}
			}
			
			if(count < c) {
				height = mid -1;
				continue;
			}
			
			low = mid + 1;
		
		}
		System.out.println(low - 1);
	}
}
