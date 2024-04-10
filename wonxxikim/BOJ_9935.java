import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	static String str, bomb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		bomb = br.readLine();
		Stack<Character> stack = new Stack<>();
		for(int i = 0 ; i<str.length();i++) {
			stack.push(str.charAt(i));
			
			if(stack.size()>=bomb.length()) {
				if(check(stack)) {
					for(int j =0  ;j<bomb.length();j++) {
						stack.pop();
					}
				}
			}
		}
	  StringBuilder sb = new StringBuilder();
		for(Character c : stack) {
			sb.append(c);
		}
		System.out.println(sb.length()==0? "FRULA" : sb.toString());

	}

	static boolean check(Stack<Character> stack) {
		for (int i = 0; i<bomb.length(); i++) {
			if (stack.get(stack.size()-bomb.length()+i) != bomb.charAt(i))
				return false;
		}
		return true;
	}

}
