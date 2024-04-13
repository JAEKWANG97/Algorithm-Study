import java.io.*;
import java.util.*;

public class BOJ_2023 {
	
	
	static boolean sosu(int n) {
		if (n < 2) return false;
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	static void dfs(int s, int d) {
		if (d == N) {
			System.out.println(s);
			return;
		}
		
		for (int i = 0; i < 10; i++) {
			int next = s * 10 + i;
			if (sosu(next)) {
				dfs(next, d + 1);
			}
		}
		
		
	}
	
	static int N;

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		N = Integer.parseInt(br.readLine());
		dfs(0, 0);

		
	}
	
}
