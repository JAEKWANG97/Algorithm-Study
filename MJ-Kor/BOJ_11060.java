package algorithm_study.day_03_19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11060 {
	public static int T, N;
	public static int[] arr = new int[1001];
	public static int[] dp = new int[1001];
	public static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.fill(dp, 1001);
		dp[0] = 0;
		for (int i = 0; i < N; i++) {
			int step = arr[i];
			for (int j = i + step; j > i; j--) {
				if (j < N) {
					dp[j] = Math.min(dp[j], dp[i] + 1);
				}
			}
		}

		if (dp[N - 1] == 1001) {
			System.out.println(-1);
		} else {
			System.out.println(dp[N - 1]);
		}
	}
}