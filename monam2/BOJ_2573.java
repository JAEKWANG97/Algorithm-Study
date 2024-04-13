import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2573 { //백준 2573 빙산 - 60분
	//완전탐색으로 각 빙하에 대해 맞닿은 면이 바다인 갯수를 체크
	//각 빙하 좌표에 그 갯수를 저장했다가 한 번에 빙하 깎아주기
	//이후 BFS를 통해 빙하 덩어리 체크
	
	private static class Node{
		int x,y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	private static class Glacier{
		int x, y, height;

		public Glacier(int x, int y, int height) {
			super();
			this.x = x;
			this.y = y;
			this.height = height;
		}
	}
	
	static int n, m, map[][], ice;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static ArrayList<Glacier> glacierToCarve;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j]!=0) {
					ice++;
				}
			}
		}
		
		int time = 0;
		while (true) {
			//쪼개졌거나 더 이상 깎을 얼음이 없으면
			if (checkGalcier()){
				break;
			}
			if (ice == 0) {
				time = 0;
				break;
			}
			//완탐으로 깎아야 할 얼음 위치를 리스트에 저장
			bruteForce();
			//리스트에 저장된 위치의 얼음을 깎기
			carve();
			time++;
		}
		
		if (ice==0) {
			System.out.println(0);
		} else {
			System.out.println(time);
		}
		
	}//main
	
	private static boolean checkGalcier() {
		int cnt = 1;
		ArrayDeque<Node> q = new ArrayDeque<Node>();
		boolean[][] visited = new boolean[n][m];
		for (int i = 1; i < n-1; i++) {
			if (!q.isEmpty()) {
				break;
			}
			for (int j = 1; j < m-1; j++) {
				if (map[i][j]!=0) {
					q.add(new Node(i,j));
					visited[i][j] = true;
					break;
				}
			}
		}
		
		while (!q.isEmpty()) {
			int x = q.peek().x;
			int y = q.poll().y;
			
			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				
				if (0<=nx&&nx<n && 0<=ny&&ny<m && !visited[nx][ny] && map[nx][ny]!=0) {
					q.add(new Node(nx,ny));
					visited[nx][ny] = true;
					cnt++;
				}
			}
		}
		if (cnt==ice) { //아직 한덩어리임
			return false;
		} else {
			return true;
		}
	}//checkGalcier
	
	private static void carve() {
		for (Glacier glacier : glacierToCarve) {
			map[glacier.x][glacier.y] -= glacier.height;
			if (map[glacier.x][glacier.y] <= 0) {
				map[glacier.x][glacier.y] = 0;
				ice--;
			}
		}
	}//carve
	
	private static void bruteForce() {
		glacierToCarve = new ArrayList<>();
		for (int i = 1; i < n-1; i++) {
			for (int j = 1; j < m-1; j++) {
				if (map[i][j] != 0) {
					glacierToCarve.add(new Glacier(i, j, howCarve(i,j)));
				}
			}
		}
	}//bruteForce
	
	private static int howCarve(int x, int y) {
		int toCarve = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (0<=nx&&nx<n && 0<=ny&&ny<m && map[nx][ny]==0) {
				toCarve++;
			}
		}
		return toCarve;
	}//howCarve
	
}//class
