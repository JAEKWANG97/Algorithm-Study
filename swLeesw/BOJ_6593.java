import java.io.*;
import java.util.*;

public class Main {
	
	static class Pos {
		int y, x, z, depth;

		public Pos(int y, int x, int z, int depth) {
			this.y = y;
			this.x = x;
			this.z = z;
			this.depth = depth;
		}
	}
	
	static int l, r, c, arr[][][];
	static boolean visited[][][];
	static Pos s, e;
	static int dy[] = {0, 0, 1, -1};
	static int dx[] = {1, -1, 0, 0};
	static int dz[] = {1, -1};
	
	static boolean isRange(int y, int x, int z) {
		if (x >= 0 && y >= 0 && z >= 0 && x < c && y < r && z < l) return true;
		else return false;
	}
	
	static int bfs() {
		Queue<Pos> que = new ArrayDeque();
		visited[s.y][s.x][s.z] = true;
		que.offer(new Pos(s.y, s.x, s.z, 0));
		
		while (!que.isEmpty()) {
			Pos cur = que.poll();
			
			if (cur.y == e.y && cur.x == e.x && cur.z == e.z) {
				return cur.depth;
			}
			
			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if (isRange(ny, nx, cur.z) && !visited[ny][nx][cur.z] && arr[ny][nx][cur.z] == 0) {
					visited[ny][nx][cur.z] = true;
					que.offer(new Pos(ny, nx, cur.z, cur.depth + 1));
				}
			}
			for (int i = 0; i < 2; i++) {
				int nz= cur.z + dz[i];
				if (isRange(cur.y, cur.x, nz) && !visited[cur.y][cur.x][nz] && arr[cur.y][cur.x][nz] == 0) {
					visited[cur.y][cur.x][nz] = true;
					que.offer(new Pos(cur.y, cur.x, nz, cur.depth + 1));
				}				
			}
			
		}
		
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			l = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			if (l == 0 && r == 0 && c == 0) break;
			arr = new int[r][c][l];
			visited = new boolean[r][c][l];
			
			for (int k = 0; k < l; k++) {
				for (int i = 0; i < r; i++) {
					String str = br.readLine();
					for (int j = 0; j < c; j++) {
						char c = str.charAt(j);
						if (c == '#') {
							arr[i][j][k] = 1;
						} else if (c == 'S') {
							s = new Pos(i, j, k, 0);
						} else if (c == 'E') {
							e = new Pos(i, j, k, 0);						
						}
					}
				}
				br.readLine();
			}
			
			int sol = bfs();
			if (sol == -1) {
				System.out.println("Trapped!");
			} else {
				System.out.println("Escaped in " + sol + " minute(s).");
			}
		}
		
	}	
}
