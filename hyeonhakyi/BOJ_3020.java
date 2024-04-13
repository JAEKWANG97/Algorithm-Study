package ex0329;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3020 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		int[] down = new int[h + 1];
		int[] up = new int[h + 1];

		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			if (i % 2 == 0) {
				down[num]++;
			} else {
				up[num]++;
			}
		}

		for (int i = h - 1; i > 0; i--) {
			down[i] += down[i + 1];
			up[i] += up[i + 1];
		}

		int min = Integer.MAX_VALUE;
		int count = 0;
		for (int i = 1; i <= h; i++) {
			int cnt = down[i] + up[h - i + 1];
			
			if(min > cnt) {
				min = cnt;
				count = 1;
			}else if(min == cnt){
				count++;
			}
		}
		
		System.out.println(min + " " + count);
		
	}// main end
}// class end
