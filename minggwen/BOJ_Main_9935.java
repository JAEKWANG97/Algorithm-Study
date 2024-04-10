import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


public class BOJ_Main_9935 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String bomb = br.readLine();
		Stack<Character> s = new Stack<>();
		for(int idx=0;idx<str.length();idx++) {
			s.push(str.charAt(idx));
			if(s.size()>=bomb.length()) {
				boolean flag = true;
				for(int j=0;j<bomb.length();j++) {
					if(s.get(s.size()-bomb.length()+j)!=bomb.charAt(j)) {
						flag = false;
						break;
					}
				}
				if(flag) {
					for(int j=0;j<bomb.length();j++)s.pop();
				}
			}
		}
		StringBuilder sb= new StringBuilder();
		for(char c:s)sb.append(c);
		System.out.println(sb.length()==0?"FRULA":sb.toString());
	}

}
