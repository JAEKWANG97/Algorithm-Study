package ex0311;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_5052 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < t; tc++) {
			int n = Integer.parseInt(br.readLine());
			String[] arr = new String[n];
			
			for(int i = 0; i < n; i++) {
				arr[i] = br.readLine();
			}
			
			Arrays.sort(arr);
			
			String s = "YES";
			for(int i = 0; i < n-1; i++) {
				if(arr[i+1].startsWith(arr[i])) {
					s = "NO";
					break;
				}
			}			
			System.out.println(s);
		}//test end
	}//main end
}//class end
