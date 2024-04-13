import java.io.*;
import java.util.*;

public class BOJ_14500 {
	
	
	static int arr[][], n, m, sol;
	static boolean visited[][];
	static int dx[] = {0, 0, 1, -1};
	static int dy[] = {1, -1, 0, 0};
	static boolean isRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < n && x < m) return true;
		else return false;
	}
	
	static void yochul(int y, int x, int depth, int sum) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if (isRange(ny, nx) && !visited[ny][nx]) {
				list.add(arr[ny][nx]);
			}
			
		}
		
		//2개인 경유ㅜ
		if (list.size() == 2) {
			int realSum = sum + list.get(0) + list.get(1);
			sol = Math.max(sol, realSum);
		} else if (list.size() == 3) { //3개인 경우
			
			for (int i = 0; i < 2; i++) {
				for (int j = i + 1; j < 3; j++) {
					sol = Math.max(sol, sum + list.get(i) + list.get(j));
				}
			}
		}
		
		
	}
	
	static void dfs(int y, int x, int depth, int sum) {
		visited[y][x] = true;
		if (depth == 2) {
			yochul(y, x, depth, sum);
		}
		if (depth == 4) {
			sol = Math.max(sol, sum);
			visited[y][x] = false;
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if (isRange(ny, nx) && !visited[ny][nx]) {
				dfs(ny, nx, depth + 1, sum + arr[ny][nx]);
			}
			
		}
		
		visited[y][x] = false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				dfs(i, j, 1, arr[i][j]);
			}
		}

		System.out.println(sol);
	}	
}