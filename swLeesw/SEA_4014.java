import java.io.*;
import java.util.*;

public class SEA_4014 {

	static int n, t, arr[][], gLength, X, sol;
	static boolean visited[];
	static boolean solveHo(int y) {//y고정
		visited = new boolean[n];//처리 여부(중복 처리되면 return false;
		
		for (int i = 1; i < n; i++) {
			if (Math.abs(arr[y][i - 1] - arr[y][i]) > 1) return false; //왼오 차이가 1 초과면 false 
			if (arr[y][i - 1] < arr[y][i]) { //오른쪽이 더 크다면 왼쪽 처리
				if (i - X < 0) return false; //길이 모자라면 false
				for (int j = i - 1; j >= i - X; j--) {//활주로 만들기(visited배열로 체크)
					if (!visited[j] && arr[y][i] - arr[y][j] == 1) { //활주로가 방문하지 않은 곳이거나 arr[i] - arr[j] == 1이어야함
						visited[j] = true;
					} else {
						return false; //불가능
					}
				}
				
			} else if (arr[y][i - 1] > arr[y][i]) {//왼쪽이 더 크다면 오른쪽 처리
				if (i + X > n) return false; //범위 체크
				for (int j = i; j < i + X; j++) {
					if (!visited[j] && arr[y][i - 1] - arr[y][j] == 1) {
						visited[j] = true;
					} else {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	static boolean solveVe(int x) { //x 고정 (위 함수의 vertical버전)
		visited = new boolean[n];
		
		for (int i = 1; i < n; i++) {
			if (Math.abs(arr[i - 1][x] - arr[i][x]) > 1) return false; //왼오 차이가 1 초과면 false 
			if (arr[i - 1][x] < arr[i][x]) { //오른쪽이 더 크다면 왼쪽 처리
				if (i - X < 0) return false; //길이 모자라면 false
				for (int j = i - 1; j >= i - X; j--) {//활주로 만들기(visited배열로 체크)
					if (!visited[j] && arr[i][x] - arr[j][x] == 1) { //활주로가 방문하지 않은 곳이거나 arr[i] - arr[j] == 1이어야함
						visited[j] = true;
					} else {
						return false; //불가능
					}
				}
				
			} else if (arr[i - 1][x] > arr[i][x]) {//왼쪽이 더 크다면 오른쪽 처리
				if (i + X > n) return false; //범위 체크
				for (int j = i; j < i + X; j++) {
					if (!visited[j] && arr[i - 1][x] - arr[j][x] == 1) {
						visited[j] = true;
					} else {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			sol = 0;
			st = new StringTokenizer(br.readLine());
			n =Integer.parseInt(st.nextToken());
			X =Integer.parseInt(st.nextToken());
			arr = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < n; i++) {
				if (solveHo(i)) { //수평 체크
					sol++;
				}
				if (solveVe(i)) { //수직 체크
					sol++;
				}
			}
			
			System.out.println("#" + tc + " " + sol);
		}
		
	}
	
}
