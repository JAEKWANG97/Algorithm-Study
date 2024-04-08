package algorithm_study.day_03_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1107 {

	private static boolean[] buttons;
	private static String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
	private static int absMin = Integer.MAX_VALUE;
	private static int myNum;
	private static String strN;
	private static int intN;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		strN = br.readLine();
		intN = Integer.parseInt(strN);
		
		int M = Integer.parseInt(br.readLine());
		
		if(M != 0) {			
			st = new StringTokenizer(br.readLine());
		}
		
		buttons = new boolean[10];
		Arrays.fill(buttons, true);
		
		for (int i = 0; i < M; i++) {
			buttons[Integer.parseInt(st.nextToken())] = false;
		}
		
		StringBuilder s1 = new StringBuilder();
		for (int j = 1; j <= 6; j++) {			
			perm(s1, 0, j);
		}
		if(absMin == Integer.MAX_VALUE) {
			System.out.println(Math.abs(intN - 100));
		}else {			
			System.out.println(Math.min(absMin + Integer.toString(myNum).length(), Math.abs(intN - 100)));
		}

	}
	
	private static void perm(StringBuilder s1, int cnt, int E) {
		if(cnt == E) {
			int currN = Integer.parseInt(s1.toString());
			int absN = Math.abs(intN - currN);
			if(absN < absMin) {
				absMin = Math.min(absN, absMin);
				myNum = currN;
			}
			return;
		}
		else {
			for (int i = 0; i <= 9; i++) {
				if(!buttons[i]) continue;
				perm(s1.append(numbers[i]), cnt + 1, E);
				s1.deleteCharAt(s1.length()-1);
			}
		}
	}

}
