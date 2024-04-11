import java.util.*;
import java.io.*;

public class BOJ_9935 {

	
	public static void main(String[] args) throws IOException {
		Stack<Character> stack = new Stack<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str = br.readLine();
		String cmp = br.readLine();
		int cmpSize = cmp.length();
		
		for (int i = 0; i < str.length(); i++) {
			stack.push(str.charAt(i));
			
			//비교 문자열과 길이가 같아지면
			if (stack.size() >= cmpSize) {
				boolean check = true;
				
				for (int j = 0; j < cmpSize; j++) {
					if (stack.get(stack.size() - cmpSize + j) != cmp.charAt(j)) {
						check = false;
						break;
					}
				}
				
				if (check) {
					for (int j = 0; j < cmpSize; j++) {
						stack.pop();
					}
				}
				
			}
			
		}
		int stSize = stack.size();
		for (int i = 0; i < stSize; i++) {
			sb.append(stack.get(i));
		}
		
		if (sb.length() == 0) {
			System.out.println("FRULA");
		} else {
			System.out.println(sb.toString());
		}
	}
	
}
