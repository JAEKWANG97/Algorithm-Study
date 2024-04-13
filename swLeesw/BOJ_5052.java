import java.util.*;
import java.io.*;

public class BOJ_5052 {

	static int t, n;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			boolean flag = false; //포함 여부 확인
			String sList[] = new String[n];
			
			for (int i = 0; i < n; i++) {
				sList[i] = br.readLine();
			}
			
			Arrays.sort(sList);//문자열 정렬은 idx0 -> idx1 순으로 정렬
			
			for (int i = 1; i < n; i++) {
				if (sList[i].startsWith(sList[i - 1])) {//만약 포함되어있으면, 일관성이 없는 목록
					flag = true;
					break;
				}
			}
			if (flag) {
				System.out.println("NO");
			} else {
				System.out.println("YES");
			}
		}
		
		
	}
	
}
