package ex0229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2470 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int low = 0;
		int height = n-1;
		
		int minSum = Integer.MAX_VALUE;
		int max = 0;
		int min = 0;	
		
		int sum = 0;
		int temp = 0;
		
		while(low < height){
			sum = arr[height] + arr[low];
			temp = Math.abs(sum);
			
			if(temp < minSum) {
				minSum = temp;
				max = arr[height];
				min = arr[low];
			}
			
			if(sum > 0) {
				height--;
			}else {
				low++;
			}
		}
		System.out.println(min + " " + max);
	}
}
