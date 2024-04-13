import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_5052 { //백준 5052 전화번호 목록 - 30분
	
	//문자열 오름차순 정렬
	//이전과 다음 일치 여부 체크
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			int n = Integer.parseInt(br.readLine());
			String[] arr = new String[n];
			for (int j = 0; j < n; j++) {
				arr[j] = br.readLine();
			}
			
			Arrays.sort(arr);
			
			//startwith 함수 : 특정 문자로 시작하는지 체크
			
			boolean flag = true;
			for (int j = 1; j < n; j++) {
				if (arr[j].startsWith(arr[j-1])) {
					flag = false;
					break;
				}
			}
			if (!flag) System.out.println("NO");
			else System.out.println("YES");
			
		}//for T
	}//main
}//class
