package mmalfoy;

import java.io.*;
import java.util.*;

public class BOJ_14889 {
	static int N, minDiff = Integer.MAX_VALUE;
	static int[][] table;
	static boolean[] combination;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		table = new int[N][N];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				table[n][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		combination = new boolean[N];
		combination[0] = true;
		nCn_2(1, 1);
		System.out.println(minDiff);
		br.close();
	}
	
	private static void nCn_2 (int start, int depth) {
		if (depth == N/2) {
			Sum();
			return;
		}
		for (int i = start; i < N; i++) {
			// 절반만 탐색			
			combination[i] = true;
			nCn_2(i + 1, depth + 1);
			combination[i] = false;
		}
	}
	
	private static void Sum() {
		int sumStart = 0;
		int sumLink = 0;
		
		int[] start = new int[N/2];
		int[] link = new int[N/2];
		
		for (int i = 0, j = 0, k = 0; i < N; i++) {
			if (combination[i]) {
				start[j++] = i;
			} else {
				link[k++] = i;				
			}
		}
		for (int i = 0; i < N/2; i++) {
			for (int j = i+1; j < N/2; j++) {
				sumStart += table[start[i]][start[j]] + table[start[j]][start[i]];
				sumLink += table[link[i]][link[j]] + table[link[j]][link[i]];
			}
		}
		int curDiff = Math.abs(sumStart - sumLink);
		if ( curDiff < minDiff) {
			minDiff = curDiff;
		}
	}
}
