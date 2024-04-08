import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SEA_1949 {
	static int n, k, map[][], top, result;
	static boolean visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T+1; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			top = 0;
			map = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					top = Math.max(top, map[i][j]);
				}
			}

			result = 0;
			visited = new boolean[n][n];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j]==top) {
						dfs(i,j,1); //좌표, 거리, 공사 가능 여부
					}
				}
			}
			
			System.out.println("#" + t + " " + result);

		}//for T
	}//main
	
	private static void dfs(int x, int y, int dist) {
		if (dist > result) {
			result = dist;
		}
		
		visited[x][y] = true;
		
		int[] dx = {-1,0,1,0};
		int[] dy = {0,1,0,-1};
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if (0<=nx&&nx<n && 0<=ny&&ny<n && !visited[nx][ny]) {
				if (map[nx][ny] < map[x][y]) { //더 낮은 칸이면 등산로 건설
					dfs(nx,ny,dist+1);
				} else if (k != 0 && k > map[nx][ny] - map[x][y]) {
					//k가 0 아님 -> 아직 공사x
					//다음 칸이 더 높을 때, 공사해서 현재 칸보다 더 낮게 만들 수 있으면 공사함(k->0)
					//이 때, 백트래킹 해야하므로 함수 리턴될 때 다시 공사하기 전 값으로 복원시켜놔야 함
					int tmp = map[nx][ny];
					map[nx][ny] = map[x][y] - 1;
					int tmpK = k;
					k = 0;
					dfs(nx, ny, dist+1);
					k = tmpK;
					map[nx][ny] = tmp;
				}
			}
		}
		
		visited[x][y] = false;
	}//dfs
	
}//class
