
import java.util.*;
import java.io.*;

public class BOJ_2470 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] num = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int n = 0; n < N; n++) {
			int cur = Integer.parseInt(st.nextToken());
			num[n] = cur;
		}
		Arrays.sort(num);
		int front = 0, rear = N-1, small = 0, big = 0, min = Integer.MAX_VALUE;

		while(front < rear) {
			int cur = Math.abs(num[front] + num[rear]);
			if (cur < min) {
				min = cur;
				small = num[front];
				big = num[rear];
			}
			
			if (Math.abs(num[front] + num[rear-1]) < Math.abs(num[front+1] + num[rear])) {
				rear -= 1;
			} else {
				front += 1;
			}	
		}
		System.out.println(small + " " + big);
	}
}
