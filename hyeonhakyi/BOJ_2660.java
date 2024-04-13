package ex0401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2660 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int INF = 987654321;
		int[][] arr = new int[n+1][n+1];
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(i != j) {
					arr[i][j] = INF;
				}
			}
		}
		
		StringTokenizer st;
		while(true) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if(x == -1 && y == -1) {
				break;
			}
			
			arr[x][y] = arr[y][x] = 1;
		}
		
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
			}
		}
		
		int min = INF;
		int[] answer = new int[n+1];
		for(int i = 1; i <= n; i++) {
			int maxCount = 0;
			for(int j = 1; j <= n; j++) {
				if(arr[i][j] != INF) {
					maxCount = Math.max(maxCount, arr[i][j]);
				}
			}
			answer[i] = maxCount;
			min = Math.min(maxCount, min);
		}
		
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		for(int i = 1; i <= n; i++) {
			if(min == answer[i]) {
				cnt++;
				sb.append(i + " ");
			}	
		}
		
		System.out.println(min + " " + cnt);
		System.out.println(sb.toString());
	}//main end
}//class end
