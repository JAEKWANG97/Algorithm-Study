import java.util.*;

public class BOJ_10844 {
	static final int mod = 1000000000;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		long[][] D = new long[10][N + 1];
		for (int i = 1; i < 10; i++) {
			D[i][1] = 1L;
		}
		
		find (N, D);
		long result = 0;
		for (int i = 0; i < 10; i++) {
			result += D[i][N];
		}
		
		System.out.println(result % mod);

	}
	
	static void find (int N, long[][] D) {
		int depth = 2;
		while (depth <= N) {
			
			for (int i = 0; i < 10; i++) {

				if (i == 0) {
					D[0][depth] = D[1][depth - 1] % mod;
					continue;
				}
				
				if (i == 9) {					
					D[9][depth] = D[8][depth - 1] % mod;
					continue;
				}
				
				D[i][depth] = (D[i - 1][depth - 1] + D[i + 1][depth - 1]) % mod;
				
			}
			depth += 1;
		}
	}

}
