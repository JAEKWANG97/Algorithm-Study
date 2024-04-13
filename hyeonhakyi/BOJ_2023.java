package ex0404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2023 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		dfs(0,n);
	}//main end
	
	private static void dfs(int count,int n) {
		if(n == 0) {
			System.out.println(count);
		}
		
		for(int i = 1; i < 10; i++) {
			int num = 10*count + i;
			if(n > 0 && check(num)) {
				dfs(num,n-1);
			}
		}
	}//dfs end
	
	private static boolean check(int num) {
		if(num < 2) return false;
		for(int i = 2; i*i <= num; i++) {
			if(num % i == 0) {
				return false;
			}
		}
		return true;
	}//num end
}//class end
