package ex0416;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1253 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		int count = 0;
		for(int i = 0; i < n; i++) {
			int left = 0;
			int right = n-1;
			int target = arr[i];
			while(left < right) {
				int sum = arr[left] + arr[right];
				if(sum == target) {
					if(left != i && right != i) {
						count++;
						break;
					}else if(right == i) {
						right--;
					}else if(left == i) {
						left++;
					}
				}else if(sum > target) {
					right--;
				}else {
					left++;
				}
			}
		}
		
		System.out.println(count);
		
	}//main end
}//class end
