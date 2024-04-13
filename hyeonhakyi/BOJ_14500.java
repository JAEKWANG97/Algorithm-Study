package ex0319;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14500 {
	
	private static int n,m,result;
	private static int[][] arr;
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,-1,1};
	private static boolean[][] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		result = Integer.MIN_VALUE;
		visited = new boolean[n][m];
		
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j< m; j++) {
				visited[i][j] = true;
				bfs(i,j,0,arr[i][j]);
				visited[i][j] = false; 
			}
		}
		
		System.out.println(result);
	}//main end
	
	private static void bfs(int x,int y,int cnt,int sum) {
		if(cnt == 3) {
			result = Math.max(result, sum);
			return;
		}
		
		for(int d = 0; d < 4; d++) {
			int nx = x+ dx[d];
			int ny = y + dy[d];
			
			if(nx >= 0 && ny >= 0 && nx < n && ny < m) {
				if(!visited[nx][ny]) {
					if(cnt == 1) {
						visited[nx][ny] = true;
						bfs(x,y,cnt+1,sum + arr[nx][ny]);
						visited[nx][ny] = false;
					}
					visited[nx][ny] = true;
					bfs(nx,ny,cnt+1,sum + arr[nx][ny]);
					visited[nx][ny] = false;
				}
			}
		}
	}//bfs end
}//class end
