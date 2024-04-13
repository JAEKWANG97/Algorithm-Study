import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main { //백준 2206 벽 부수고 이동하기 - 80분
	private static class Node{
		int x,y,k,dist;

		public Node(int x, int y, int k, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.k = k;
			this.dist = dist;
		}
	}//Node
	
	static int n, m, ans;
	static char[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new char[n][m];
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		System.out.println(bfs(0,0));
		
	}//main
	
	private static int bfs(int x, int y) {
		ArrayDeque<Node> q = new ArrayDeque<Node>();
		q.offer(new Node(x,y,1,1));
		boolean[][][] visited = new boolean[n][m][2];
		visited[x][y][0] = true; //안부순건 0 부순건 1
		
		
		int[] dx = {-1,0,1,0};
		int[] dy = {0,1,0,-1};
		while (!q.isEmpty()) {
			x = q.peek().x;
			y = q.peek().y;
			int k = q.peek().k;
			int dist = q.poll().dist;
//			System.out.println("현재 위치 : " + x + ", " + y + ", k = " + k + ", dist = " + dist);
			
			if (x==n-1 && y==m-1) {
				 return dist;
			}
			
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if (0<=nx&&nx<n && 0<=ny&&ny<m) {
					//벽을 부순 적이 있으면
					if (k==0) {
						//아직 방문x & 0인 지점으로 탐색
						if (map[nx][ny]=='0' && !visited[nx][ny][1]) {
							visited[nx][ny][1] = true;
							q.offer(new Node(nx, ny, 0, dist+1));
						}
					}
					//벽을 부순 적이 없을때
					else {
						//벽이라면 부수고 큐에 넣음
						if (map[nx][ny] == '1') {
							visited[nx][ny][1] = true;
							q.offer(new Node(nx,ny,0,dist+1));
						}
						//벽이 아니면 그냥 그대로 탐색
						else if (map[nx][ny] == '0' && !visited[nx][ny][0]) {
							visited[nx][ny][0] = true;
							q.offer(new Node(nx,ny,1,dist+1));
						}
					}
					
					
				}
			}
		}
		return -1;
	}//bfs
}//class
