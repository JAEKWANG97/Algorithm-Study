package ex0327;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_3649 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = null;
		while((s = br.readLine()) != null) {
			int n = Integer.parseInt(s) * 10000000;
			int m = Integer.parseInt(br.readLine());
			int[] arr = new int[m];
			
			for (int i = 0; i < m; i++) {
				arr[i] = Integer.parseInt(br.readLine());
			}

			Arrays.sort(arr);

			int left = 0;
			int right = m-1;
			boolean check = false;
			
			while(left < right) {
				int sum = arr[left] + arr[right];
				if(sum == n) {
					check = true;
					break;
				}else if(sum > n) {
					right--;
				}else {
					left++;
				}
			}
			if (!check) {
				System.out.println("danger");
			} else {
				System.out.println("yes " + arr[left] + " " + arr[right]);
			}
		}
	}// main end
}// class end
