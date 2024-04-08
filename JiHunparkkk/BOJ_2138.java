import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	private static int n;
	private static char[] init;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		init = br.readLine().toCharArray();
		String expect = br.readLine();
		
		//복사본 초기화
		char[] copy = new char[n];
		for(int i=0;i<n;i++) {
			copy[i] = init[i];
		}
		
		int answer = 0;
		for(int i=0;i<n-1;i++) {
			if(init[i]!=expect.charAt(i)) {
				pushSwitch(i+1);												
				answer++;
			}
		}
		
		if(init[n-1]!=expect.charAt(n-1)) {
			init = Arrays.copyOf(copy, n);
			answer=1;
			pushSwitch(0);
			
			for(int i=0;i<n-1;i++) {
				if(init[i]!=expect.charAt(i)) {
					pushSwitch(i+1);												
					answer++;
				}
			}
		}

		if(!String.valueOf(init).equals(expect)) {
			answer = -1;
		}
		
		System.out.println(answer);
	}

	
	private static void pushSwitch(int i) {
		init[i] = changeStatus(i);
		if(i>0) init[i-1] = changeStatus(i-1);
		if(i<n-1) init[i+1] = changeStatus(i+1);
	}
	
	private static char changeStatus(int i) {
		return init[i]=='1' ? '0' : '1';
	}
}
