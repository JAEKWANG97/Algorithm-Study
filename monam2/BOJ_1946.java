import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1946 { //백준 1946 신입 사원 - 30분
	private static class Sawon{
		int a,b;

		public Sawon(int a, int b) {
			super();
			this.a = a;
			this.b = b;
		}
	}//Sawon

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T+1; t++) {
			int n = Integer.parseInt(br.readLine());
			
			Sawon[] sawons = new Sawon[n];
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				sawons[i] = new Sawon(a,b);
			}
			
			Arrays.sort(sawons, (s1, s2)-> s1.a - s2.a);
			int cnt = 1;
			int std = sawons[0].b;
			for (int i = 1; i < sawons.length; i++) {
				if (sawons[i].b < std) {
					cnt++;
					std = sawons[i].b;
				}
			}
			
			
			System.out.println(cnt);
		}//for T
	}//main
}//class
