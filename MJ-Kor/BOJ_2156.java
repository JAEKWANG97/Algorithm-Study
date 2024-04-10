package algorithm_study.april.day_04_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2156 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] wines = new int[N];
        for (int i = 0; i < N; i++) {
            wines[i] = Integer.parseInt(br.readLine());
        }
        
        int[] dp = new int[N];
        dp[0] = wines[0];
        
        for (int i = 1; i < N; i++) {
			if(i == 1) {
				dp[1] = wines[0] + wines[i];
				continue;
			}
			
			if(i == 2) {
				dp[2] = Math.max(dp[1], Math.max(wines[0] + wines[2], wines[1] + wines[2]));
				continue;
			}
			
			dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + wines[i], dp[i - 3] + wines[i - 1] + wines[i]));

		}
        System.out.println(dp[N - 1]);
	}
}

