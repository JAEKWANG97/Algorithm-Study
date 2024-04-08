import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17070 { //백준 17070 파이프 옮기기1 - 80분
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] dx = {0,1,1};
	static int[] dy = {1,1,0};
	
	static int n, map[][], cnt;
	
	public static void main(String[] args) throws IOException {
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int start = 0;
		cnt = 0;
		dfs(start, 0, 1);
		System.out.println(cnt);
	}//main
	
	private static void dfs(int nowDir, int x, int y) {
		if (x==n-1 && y==n-1) {
			//목표지점 도달
			cnt++;
			return;
		}
		
		switch (nowDir) { 
		case 0: //현재 가로 -> 0,1만 가능
			for (int d = 0; d <= 1; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if (0<=nx && 1<=ny && nx<n && ny<n && map[nx][ny] != 1) {
					if (d==1 && (map[nx-1][ny]==1 || map[nx][ny-1]==1)) continue;
					dfs(d, nx, ny);
				}
			}
			break;
		case 1: //현재 대각 -> 0,1,2 전부 가능
			for (int d = 0; d <= 2; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if (0<=nx && 1<=ny && nx<n && ny<n && map[nx][ny] != 1) {
					if (d==1 && (map[nx-1][ny]==1 || map[nx][ny-1]==1)) continue;
					dfs(d, nx, ny);
				}
			}
			break;
		case 2: //현재 세로 -> 1,2만 가능
			for (int d = 1; d <= 2; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if (0<=nx && 1<=ny && nx<n && ny<n && map[nx][ny] != 1) {
					if (d==1 && (map[nx-1][ny]==1 || map[nx][ny-1]==1)) continue;
					dfs(d, nx, ny);
				}
			}
			break;
		}
	}//dfs
}//class
