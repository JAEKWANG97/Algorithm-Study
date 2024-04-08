package algorithm_study.day_03_13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ_2251 {

	private static int[] bucket;
	private static boolean[][] check;
	private static Set<Integer> answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		bucket = new int[3];
		check = new boolean[201][201];
		for (int i = 0; i < 3; i++) {
			bucket[i] = Integer.parseInt(st.nextToken());
		}

		answer = new TreeSet<>();
		dfs(0, 0, bucket[2]);

		for (int num : answer) {
			System.out.print(num + " ");
		}
	}

	static void dfs(int a, int b, int c) {
		if (check[a][b])
			return;

		if (a == 0) {
			answer.add(c);
		}
		check[a][b] = true;

		if (a + b > bucket[1]) {
			dfs((a + b) - bucket[1], bucket[1], c);
		} else {
			dfs(0, a + b, c);
		}

		if (a + b > bucket[0]) {
			dfs(bucket[0], a + b - bucket[0], c);
		} else {
			dfs(a + b, 0, c);
		}

		if (a + c > bucket[0]) {
			dfs(bucket[0], b, a + c - bucket[0]);
		} else {
			dfs(a + c, b, 0);
		}

		if (b + c > bucket[1]) {
			dfs(a, bucket[1], b + c - bucket[1]);
		} else {
			dfs(a, b + c, 0);
		}

		dfs(a, 0, b + c);
		dfs(0, b, a + c);
	}
}