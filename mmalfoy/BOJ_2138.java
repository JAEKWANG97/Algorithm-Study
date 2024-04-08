
import java.io.*;
import java.util.*;

public class BOJ_2138 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] fromNonZero = new int[N], fromZero = new int[N], to = new int[N];

		
		String st = br.readLine();
		for (int i = 0; i < N; i++) {
			fromZero[i] = Character.getNumericValue(st.charAt(i));
			fromNonZero[i] = fromZero[i];
		}
		
		fromZero[0] = fromZero[0] == 0 ? 1 : 0;
		fromZero[1] = fromZero[1] == 0 ? 1 : 0;
		
		st = br.readLine();
		int cntNonZero = 0, cntZero = 1;
		for (int i = 1; i < N; i++) {
			if (fromNonZero[i - 1] != Character.getNumericValue(st.charAt(i - 1))) {
				fromNonZero[i - 1] = fromNonZero[i - 1] == 0 ? 1 : 0;
				fromNonZero[i] = fromNonZero[i] == 0 ? 1 : 0;
				if (i != N - 1) {
					fromNonZero[i + 1] = fromNonZero[i + 1] == 0 ? 1 : 0;					
				}
				cntNonZero += 1;
			}
			
			if (fromZero[i-1] != Character.getNumericValue(st.charAt(i-1))) {
				fromZero[i - 1] = fromZero[i - 1] == 0 ? 1 : 0;
				fromZero[i] = fromZero[i] == 0 ? 1 : 0;
				if (i != N - 1) {
					fromZero[i + 1] = fromZero[i + 1] == 0 ? 1 : 0;					
				}
				cntZero += 1;
			}
			
		}
		
		if (fromNonZero[0] == Character.getNumericValue(st.charAt(0)) &&
				fromNonZero[N - 1] == Character.getNumericValue(st.charAt(N - 1))) {
			System.out.println(cntNonZero);
			return;
		}
		
		if (fromZero[0] == Character.getNumericValue(st.charAt(0)) &&
				fromZero[N - 1] == Character.getNumericValue(st.charAt(N - 1))) {
			System.out.println(cntZero);
			return;
		}
		
		System.out.println(-1);
		return;
		
	}
}
