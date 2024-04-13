import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1759 { //백준 1759 암호만들기 - 56분 28초

	/*
	 * 최소 한 개의 모음, 두개의 자음으로 암호
	 * 암호는 사전순 규칙 -> 백트래킹
	 */
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static List<Character> arr;
	static char[] code;
	static int l, c;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		l = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		arr = new ArrayList<Character>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < c; i++) {
			arr.add(st.nextToken().charAt(0));
		}
		Collections.sort(arr);

		code = new char[l];
		makeCode(0,0,' ');
	}//main

	private static void makeCode(int cnt,int start, char before) {
		if (cnt==l) {
			//모음 수 자음 수 검증
			if (checkMo(code)) {
				for (char ch : code) {
					System.out.print(ch);
				}
				System.out.println();
			}
			return;
		}
		for (int i = start; i < c; i++) {
			if (i!=0 && before>arr.get(i)) continue;

			code[cnt] = arr.get(i);
			makeCode(cnt+1, i+1, arr.get(i));
		}
	}//makeCode

	private static boolean checkMo(char[] code) {
		//모음 자음 수 검증
		int moCnt = 0;
		int jaCnt = 0;
		for (char ch : code) {
			if (ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u') moCnt++;
			else jaCnt++;
		}
		if (moCnt>=1 && jaCnt>=2) return true;
		return false;
	}//checkMo
	
}//class
