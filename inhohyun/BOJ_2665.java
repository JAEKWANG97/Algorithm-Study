import java.io.*;
import java.util.*;

public class BOJ_2665 {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[][] map = new int[n][n];
		for(int i=0; i<n; i++) {
			String line = br.readLine();
			for(int j=0; j<line.length(); j++) {
				map[i][j] = line.charAt(j)-'0';
			}
		}
		bfs(map,n);
	}
	
	static void bfs(int[][] map, int n) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][][] check = new boolean[n][n][n*n];
		q.add(new int[] {0,0,0,0});
		check[0][0][0] = true;
		
		int min = Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			int[] p = q.poll();
			int px = p[0], py=p[1];
			int mv = p[2], blackToWhite = p[3];
			
			if(min < blackToWhite) continue;
			if(px==n-1 && py==n-1) {
				if(min > blackToWhite) {
					min = blackToWhite;
				}
				continue;
			}
			
			for(int i=0; i<4; i++) {
				int nx = px+dx[i];
				int ny = py+dy[i];
				
				if(nx<0 || ny<0 || nx>n-1 || ny>n-1 || check[ny][nx][blackToWhite]) continue;
				if(map[ny][nx] ==1) {
					check[ny][nx][blackToWhite] = true;
					q.add(new int[] {nx, ny, mv+1, blackToWhite});
				}
				
				if(map[ny][nx] ==0) {
					check[ny][nx][blackToWhite] = true;
					q.add(new int[] {nx, ny, mv+1, blackToWhite+1});
				}
			}
			
		}
		System.out.println(min==Integer.MAX_VALUE? 0 : min);
	}
}
