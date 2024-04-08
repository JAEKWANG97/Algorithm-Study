import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_11660 {
	static int[][] arr;
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		arr = new int[n][n];
		
		for(int i = 0; i<n;i++) {
			st =  new StringTokenizer(br.readLine()); 
			for(int j = 0; j<n;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 누적합 배열 만들기
		//가로로 더하기
		for(int i =0; i<n;i++) {
			for(int j = 1;j<n;j++) {
				arr[i][j] += arr[i][j-1];
			}
		}
		//세로로 더하기

		for(int i =0; i<n;i++) {
			for(int j = 1;j<n;j++) {
				arr[j][i] += arr[j-1][i];
			}
		}
		
		// 명령어 입력
		for(int i = 0; i<m;i++) {
			
			st =  new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken()) -1;
			int y1 = Integer.parseInt(st.nextToken()) -1;
			int x2 = Integer.parseInt(st.nextToken()) -1;
			int y2 = Integer.parseInt(st.nextToken()) -1;
			
			System.out.println(sum(x1,y1,x2,y2));
		}
		
		
		
		

		
	}
	
	//구간합 구하기
	static int sum(int x1, int y1, int x2, int y2) {

		int result = 0;
		if(y1-1 <0 || x1-1 < 0) {
			if (y1-1 <0 && x1-1 >= 0) {
				result = arr[x2][y2] - arr[x1-1][y2]; 
			}
			else if(y1-1 >=0 && x1-1 < 0) {
				result = arr[x2][y2] - arr[x2][y1-1];
			}
			else {
				return arr[x2][y2];	
			}
		}
		else {
			result = arr[x2][y2] - arr[x2][y1-1] - arr[x1-1][y2] + arr[x1-1][y1-1];
		}
		
		return result;
	}

}
