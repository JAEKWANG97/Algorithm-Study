import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15685 { //백준 15685 드래곤 커브 - 120분
	private static class Node{
		int x,y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static int[] di = {0,-1,0,1};
	static int[] dj = {1,0,-1,0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(br.readLine());
		
	
		int[][] map = new int[101][101];
		
		for (int k = 0; k < n; k++) {
			st = new StringTokenizer(br.readLine());
			int sj = Integer.parseInt(st.nextToken());
			int si = Integer.parseInt(st.nextToken());
			int dr = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			ArrayList<Node> list = new ArrayList<Node>();
			list.add(new Node(si,sj));
			list.add(new Node(si+di[dr], sj+dj[dr]));
		
			for (int l = 0; l < g; l++) {
				int ei = list.get(list.size()-1).x;
				int ej = list.get(list.size()-1).y;
				
				for (int i = list.size()-2; i > -1 ; i--) {
					int ci = list.get(i).x;
					int cj = list.get(i).y;
					list.add(new Node(ei - (ej - cj), ej + (ei - ci)));
				}
			}
		
			for (Node node : list) {
				int x = node.x;
				int y = node.y;
				map[x][y] = 1;
			}
		}
		int answer = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j]==1 && map[i][j] == map[i+1][j] && map[i+1][j] == map[i][j+1] && map[i][j+1] == map[i+1][j+1]) {
					answer++;
				}
			}
		}
		
		System.out.println(answer);
	}//main
}//class
