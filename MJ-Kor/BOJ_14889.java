package algorithm_study.day_02_26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14889 {
	private static int N;
	private static int[][] map;
	private static int min = Integer.MAX_VALUE;
	private static int[] A, B;
	private static boolean[] check;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		check = new boolean[N];
		A = new int[N/2];
		B = new int[N/2];
		Arrays.fill(A, -1);
		
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		comb(0, 0);
		
		System.out.println(min);
	}
	private static void comb(int cnt, int start) {
		if(cnt == N/2) {
			int bIdx = 0;
			for (int i = 0; i < N; i++) {
				if(!check[i]) {
					B[bIdx] = i;
					++bIdx;
				}
			}
			calculation();
			return;
		}
		
		for (int i = start; i < N; i++) {
			check[i] = true;
			A[cnt] = i;
			comb(cnt + 1, i + 1);
			A[cnt] = -1;
			check[i] = false;
		}
	}
	private static void calculation() {
		int sumA = 0;
		int sumB = 0;
		
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length; j++) {
				if(i == j) continue;
				sumA += map[A[i]][A[j]];
			}
		}
		for (int i = 0; i < B.length; i++) {
			for (int j = 0; j < B.length; j++) {
				if(i == j) continue;
				sumB += map[B[i]][B[j]];
			}
		}
		
		int result = Math.abs(sumA - sumB);
		if(min > result) min = result;
	}
}
