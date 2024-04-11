

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Integer.parseInt(br.readLine());
		int[] dice = new int[6];
		int min1 = Integer.MAX_VALUE;
		int max = 0, sum = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 6; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
			min1 = Math.min(min1, dice[i]);
			max = Math.max(max, dice[i]);
			sum += dice[i];
		}
		if (N == 1)
			System.out.println(sum - max);
		else {
			long result = 0;
			// 1. 1면 최소
			result += min1 * ((N - 2) * (N - 2) * 5 + (N - 2) * 4);
			// 2. 2면 최소
			int min2 = Integer.MAX_VALUE;
			for (int i = 0; i < 6; i++) {
				for (int j = i + 1; j < 6; j++) {
					if (i == 0 && j == 5)
						continue;
					if (i == 1 && j == 4)
						continue;
					if (i == 2 && j == 3)
						continue;
					min2 = Math.min(min2, dice[i] + dice[j]);
				}
			}
			result += min2 * ((N - 2) * 4 + (N - 1) * 4);
			// 3. 3면 최소
			int min3 = Integer.MAX_VALUE;
			min3 = Math.min(dice[0] + dice[1] + dice[2], min3);
			min3 = Math.min(dice[0] + dice[2] + dice[4], min3);
			min3 = Math.min(dice[0] + dice[3] + dice[4], min3);
			min3 = Math.min(dice[0] + dice[1] + dice[3], min3);
			min3 = Math.min(dice[1] + dice[2] + dice[5], min3);
			min3 = Math.min(dice[1] + dice[3] + dice[5], min3);
			min3 = Math.min(dice[2] + dice[4] + dice[5], min3);
			min3 = Math.min(dice[3] + dice[4] + dice[5], min3);
			result += min3 * 4;
			System.out.println(result);
		}

	}

}
