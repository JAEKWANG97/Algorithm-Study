import java.io.*;
import java.util.*;

public class BOJ_2589 { //백준 2589 보물섬 - 30분
	private static class Node{
		int x,y,d;

		public Node(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}//Node
	
	static int n,m,max;
	static char map[][];
	static boolean visited[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		max = 0;
		bruteForce();
		System.out.println(max);
	}//main
	
	private static void bruteForce() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j]=='L') {
					bfs(i,j);
				}
			}
		}
	}//bruteForce
	
	private static void bfs(int x, int y) {
		ArrayDeque<Node> q = new ArrayDeque<Node>();
		q.offer(new Node(x,y,0));
		visited = new boolean[n][m];
		visited[x][y] = true;
		
		int[] dx = {-1,0,1,0};
		int[] dy = {0,1,0,-1};
		
		while (!q.isEmpty()) {
			x = q.peek().x;
			y = q.peek().y;
			int d = q.poll().d;
			
			max = Math.max(max, d);
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (0<=nx&&nx<n && 0<=ny&&ny<m && !visited[nx][ny] && map[nx][ny]=='L') {
					q.offer(new Node(nx,ny,d+1));
					visited[nx][ny] = true;
				}
			}
		}
	}//bfs
}//class
