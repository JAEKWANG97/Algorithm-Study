import java.io.*;
import java.util.*;

public class BOJ_1041 {

	static long dice[], n;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		long minimum3way = Integer.MAX_VALUE;
		long minimum2way = Integer.MAX_VALUE;
		long minimumValue = Integer.MAX_VALUE;
		long maxValue = 0;		
		long sol = 0;
		n = Integer.parseInt(br.readLine());
		dice = new long[7];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= 6; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
			minimumValue = Math.min(minimumValue, dice[i]);
			maxValue = Math.max(maxValue, dice[i]);			
		}
		
		if (n == 1) {
			int sum = 0;
			for (int i = 1; i <= 6; i++) {
				sum += dice[i];
			}
			System.out.println(sum - maxValue);
			return;
		}
		
		for (int i = 1; i <= 6; i++) {
			for (int j = i + 1; j <= 6; j++) {
				if ((i == 1 && j == 6) || (i == 2 && j == 5) || (i == 3 && j == 4)) continue;
				minimum2way = Math.min(minimum2way, dice[i] + dice[j]);
			}
		}
		
		for (int i = 1; i <= 6; i += 5) {
			for (int j = 2; j <= 4; j++) {
				for (int k = j + 1; k <= 5; k++) {
					if ((j == 3 && k == 4) || (j == 2 && k == 5)) continue;
					minimum3way = Math.min(minimum3way, dice[i] + dice[j] + dice[k]);
				}
			}
		}
		sol = 4 * minimum3way + minimum2way * 4 * (n - 1) + minimum2way * 4 * (n - 2) + minimumValue * (n - 2) * (n - 2) * 5 + minimumValue * (n - 2) * 4;
		System.out.println(sol);
	}
}
