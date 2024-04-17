import java.util.*;
import java.io.*;

public class BOJ_1253 {
	
	static int n, arr[], sol;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		for (int i = 0; i < n; i++) {
			int start = 0;
			int end = n - 1;
			if (start == i) start++;
			if (end == i) end--;
			
			while (start < end) {
				int sum = arr[start] + arr[end];
				if (sum == arr[i]) {
					sol++;
					break;
				} else if (sum > arr[i]) { //sum이 더 크면 줄이기
					end--;
					if (end == i) end--;
				} else if (sum < arr[i]) { //sum이 더 작으면 늘리기
					start++;
					if (start == i) start++;
				}
			}

		}
		System.out.println(sol);
		
	}
	
}
