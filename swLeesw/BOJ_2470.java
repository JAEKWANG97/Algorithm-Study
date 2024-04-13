import java.util.*;
import java.io.*;

public class BOJ_2470 {

	static class MinPos {
		int s, e, min;
		public MinPos(int s, int e, int min) {
			this.s = s;
			this.e = e;
			this.min = min;
		}
	}
	
	static int n, arr[], s, e;
	static MinPos minpos;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i]= Integer.parseInt(st.nextToken());
		}
		s = 0; e = n - 1;
		Arrays.sort(arr);
		minpos = new MinPos(-1, -1, 2100000000);
		while (s < e) { //s가 e와 같아지기 전까지
			int sum = arr[s] + arr[e];
			
			if (Math.abs(minpos.min) > Math.abs(sum)) {
				minpos.s = s;
				minpos.e = e;
				minpos.min = sum;
			}
			
			if (sum > 0) {//0보다 크면 -
				e--;
			} else if (sum < 0) { //0보다 작으면 +
				s++;
			} else {
				minpos.e = e;
				minpos.s = s;
				minpos.min = 0;
				break;
			}
		}
		
		System.out.println(arr[minpos.s] + " " + arr[minpos.e]);
	}
	
}
