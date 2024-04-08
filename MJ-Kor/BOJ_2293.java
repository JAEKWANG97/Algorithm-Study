package algorithm_study.day_03_26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2293 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] dp = new int[K + 1];
		dp[0] = 1;
		int[] coin = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = coin[0]; j <= K; j++) {
				if(j >= coin[i]) {
					dp[j] += dp[j - coin[i]];
				}
			}
		}
		
		System.out.println(dp[K]);
	}

}
