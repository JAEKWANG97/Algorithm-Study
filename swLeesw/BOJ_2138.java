import java.io.*;
import java.util.*;

public class BOJ_2138 {
	
	static int n, sol[] = {0, 1};//2는 한번 앞부분 2개 바꾼 것
	static char init[], init2[], want[];
	
	static void solve(char[] arr, int idx, int sIdx) {
		if (want[idx] == arr[idx]) return;
		for (int i = idx; i < idx + 3; i++) {
			if(i == n) break;
			arr[i] = arr[i] == '0' ? '1' : '0';
		}
		sol[sIdx]++;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
				
		init = br.readLine().toCharArray();//원래 배열
		init2 = init.clone();//0, 1번을 바꾼 배열
		init2[0] = init2[0] == '0' ? '1' : '0';
		init2[1] = init2[1] == '0' ? '1' : '0';
		want = br.readLine().toCharArray();
		
		for (int i = 0; i < n - 1; i++) {			
			solve(init, i, 0);
			solve(init2, i, 1);
		}
		
		if (Arrays.equals(init, want) || Arrays.equals(init2, want)) {
			System.out.println(Arrays.equals(init, want) ? sol[0] : sol[1]);
		} else {
			System.out.println(-1);
		}
	}
}