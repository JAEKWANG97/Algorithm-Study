package algorithm_study.april.day_04_16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1253 {
	
	private static int result = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		for (int i = 0; i < N; i++) {
			int targetNum = arr[i];
			int leftPointer = 0;
			int rightPointer = N - 1;
			if(leftPointer == i) leftPointer += 1;
			if(rightPointer == i) rightPointer -= 1;
			while(leftPointer < rightPointer) {
				if((arr[leftPointer] + arr[rightPointer]) > targetNum) {
					rightPointer -= 1;
					if(rightPointer == i) rightPointer -= 1;
				} else if((arr[leftPointer] + arr[rightPointer]) < targetNum) {
					leftPointer += 1;
					if(leftPointer == i) leftPointer += 1;
				} else {
					result++;
					break;
				}
			}
		}
		System.out.println(result);
	}

}
