package algorithm_study.april.day_04_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_9935 {

	private static Stack<Character> stack = new Stack<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String H = br.readLine();
		String N = br.readLine();
		int HLen = H.length();
		int NLen = N.length();
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < HLen; i++) {
			int cnt = 0;
			stack.push(H.charAt(i));
			if(stack.size() >= NLen) {
				for (int j = 0; j < NLen; j++) {
					if(stack.get(stack.size() - NLen + j) == N.charAt(j)) {
						cnt++;
					}
					
					if(cnt == NLen) {
						for (int k = 0; k < NLen; k++) {
							stack.pop();
						}
					}
				}
			}
		}
		
		if(stack.isEmpty()) {
			System.out.println("FRULA");
		} else {
			for(char ch : stack) {
				sb.append(ch);
			}
			System.out.println(sb.toString());
		}
		
	}


}
