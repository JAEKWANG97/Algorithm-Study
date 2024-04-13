import java.io.*;
import java.util.*;


public class BOJ_3649 {
	
	static int x, n, lego[];
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			String tx = br.readLine();
			if (tx == null) {
				return;
			}
			x = Integer.parseInt(tx) * 10000000;
			n = Integer.parseInt(br.readLine());
			
			lego = new int[n];
			
			for (int i = 0; i < n; i++) {
				lego[i] = Integer.parseInt(br.readLine());
			}
			
			Arrays.sort(lego);
			int start = 0;
			int end = n - 1;
			int aS = -1;
			int aE = -1;
			int aSum = -210000000;
			while (start < end) {
				int cur = lego[start] + lego[end];
				if (cur == x) {
					int tSum = Math.abs(lego[start] - lego[end]);
					if (aSum < tSum) {
						aS = start;
						aE = end;
						aSum = tSum;
					}
					
					start++;
				} else if (cur > x) { //값이 더 크면 줄이자
					end--;
				} else { //늘리자
					start++;
				}
				
				
				
			}
			
			if (aS == -1) {
				System.out.println("danger");
			} else {
				System.out.println("yes " + lego[aS] + " " + lego[aE]);
			}
		}
	}
	
	
}