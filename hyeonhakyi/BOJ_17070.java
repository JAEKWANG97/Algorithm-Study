package ex0402;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17070 {
	private static int n,result;
	private static int[][] arr;
	private static boolean[][] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		arr = new int[n+1][n+1]; 
		visited = new boolean[n+1][n+1];
		result = 0;
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited[1][1] = true;
		dfs(1,2,0);
	
		System.out.println(result);
	}//main end
	
	private static void dfs(int x,int y, int dir) {
		if(x == n && y == n) {
			result++;
			return;
		}
		
		if(dir == 0 || dir == 1) {
			if(check(x, y+1) && arr[x][y+1] == 0) {
				visited[x][y+1] = true;
				dfs(x,y+1,0);
				visited[x][y+1] = false;
			}	
		}
		
		if(dir == 1 || dir == 2) {
			if(check(x+1, y) && arr[x+1][y] == 0) {
				visited[x+1][y] = true;
				dfs(x+1,y,2);
				visited[x+1][y] = false;
			}
		}
		if(check(x+1,y+1) && arr[x+1][y+1] == 0 && arr[x+1][y] == 0 && arr[x][y+1] == 0) {
			visited[x+1][y+1] = true;
			dfs(x+1,y+1,1);
			visited[x+1][y+1] = false;
		}
	}//bfs end
	
	private static boolean check(int x,int y) {
		if(x >= 1 && y >= 1 && x <= n && y <= n && !visited[x][y]) {
			return true;
		}else {
			return false;
		}
	}//check end
}//class end
