package algorithm_study.day_04_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2660 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int INF = N;
		int[][] adjMatrix = new int[N + 1][N + 1];
		
		int start = 0;
		int end = 0;
		while(true) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			if(start == -1 && end == -1) break;
			adjMatrix[start][end] = 1;
			adjMatrix[end][start] = 1;
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(i == j) continue;
				if(adjMatrix[i][j] == 0) {
					adjMatrix[i][j] = INF;
				}
			}
		}
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					adjMatrix[i][j] = Math.min(adjMatrix[i][k] + adjMatrix[k][j], adjMatrix[i][j]);
				}
			}
		}
		
		int minOfAll = Integer.MAX_VALUE;
		int[] member = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			int maxOfRow = Integer.MIN_VALUE;
			for (int j = 1; j <= N; j++) {
				maxOfRow = Math.max(maxOfRow, adjMatrix[i][j]);
			}
			member[i] = maxOfRow;
			minOfAll = Math.min(maxOfRow, minOfAll);
		}
		
		int candidateNum = 0;
		for (int j = 1; j <= N; j++) {
			if(member[j] == minOfAll) candidateNum++;
		}
		
		System.out.println(minOfAll + " " + candidateNum);
		for (int j = 1; j <= N; j++) {
			if(member[j] == minOfAll) System.out.print(j + " ");
		}
	}
}
