package ex0329;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2212 {
	
	private static int n,k,min=Integer.MAX_VALUE;
	private static int[] arr;
	private static int[] answer;
	private static boolean[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		arr = new int[n];
		answer = new int[n-1];
		visited = new boolean[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		for(int i = 0; i < n-1; i++) {
			answer[i] = arr[i+1] - arr[i];
		}
		
		Arrays.sort(answer);
		
		int sum = 0;
		for(int i = 0; i < n-k; i++) {
			sum += answer[i];
		}
		System.out.println(sum);
	}//main end
}//class end
