package ex0307;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1107 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		boolean[] erro = new boolean[10];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			erro[num] = true;
		}
		
		int result = Math.abs(n-100);
		
		for(int i = 0; i <= 999999; i++) {
			String num = String.valueOf(i);
			
			boolean check = false;
			for(int j = 0; j < num.length(); j++) {
				if(erro[num.charAt(j) - '0']) {
					
					check = true;
					break;
				}
			}
			if(!check) {
				int min = Math.abs(n-i) + num.length();
				result = Math.min(result, min);
			}
		}
		System.out.println(result);
	}//main end
}//class end
