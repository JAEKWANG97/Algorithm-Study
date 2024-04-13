import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

public class BOJ_2660 { //백준 2660 회장 뽑기 - 80분
	public static void main(String[] args) throws IOException { //백준 2660 회장뽑기
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[][] graph = new int[n+1][n+1];
		for (int i = 1; i < n+1; i++) {
			for (int j = 1; j < n+1; j++) {
				if (i==j) graph[i][j] = 0;
				else {
					graph[i][j] = 987654321;
				}
			}
		}
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a==-1 && b==-1) {
				break;
			}
			graph[a][b] = 1;
			graph[b][a] = 1;
		}
		
		for (int i = 1; i < n+1; i++) {
			for (int j = 1; j < n+1; j++) {
				if (i==j) continue;
				for (int k = 1; k < n+1; k++) {
					graph[j][k] = Math.min(graph[j][k], graph[j][i] + graph[i][k]);
				}
			}
		}
		
		int min = 987654321;
		int[] score = new int[n+1];
		for (int i = 1; i < n+1; i++) {
			int max = 0;
			for (int j = 1; j < n+1; j++) {
				if (graph[i][j] != 987654321) {
					max = Math.max(max, graph[i][j]);
				}
			}
			score[i] = max;
			min = Math.min(min, max);
		}
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 1; i < n+1; i++) {
			if (score[i] == min) {
				result.add(i);
			}
		}
		System.out.println(min + " " + result.size());
		for (Integer integer : result) {
			System.out.print(integer + " ");
		}
	}//main
}//class
