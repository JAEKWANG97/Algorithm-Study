package ec0227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576 {
	static int n,m,max;
	static int[][] arr;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static Queue<int[]> que = new LinkedList<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		max = Integer.MIN_VALUE;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(arr[i][j] == 1) {
					que.offer(new int[] {i,j});
				}
			}
		}
		
		dfs();
	}
	
	static void dfs() {
		while(!que.isEmpty()) {
			int now[] = que.poll();
			int nowX = now[0];
			int nowY = now[1];
			
			for(int d = 0; d < 4; d++) {
				int nx = nowX + dx[d];
				int ny = nowY + dy[d];
				
				if(nx >= 0 && ny >= 0 && nx < n && ny < m) {
					if(arr[nx][ny] == 0) {
						que.offer(new int[] {nx,ny});
						arr[nx][ny] = arr[nowX][nowY] + 1;
					}
				}
			}
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(arr[i][j] == 0) {
					System.out.println(-1);
					return;
				}else {
					max = Math.max(max, arr[i][j]);
				}
			}
		}
		System.out.println(max - 1);
	}
}
