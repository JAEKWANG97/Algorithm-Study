import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Main_2589 {

	static class Land{
		int row,col;

		public Land(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
	}
	static int R,C;
	static boolean[][] map;
	static List<Land> lands;
	static int MAX = 0;
	static int[][] delta = {{0,1},{0,-1},{1,0},{-1,0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new boolean[R][C];
		lands = new ArrayList<>();
		for(int r=0;r<R;r++) {
			String tmp = br.readLine();
			for(int c=0;c<C;c++) {
				if(tmp.charAt(c)=='L') {
					map[r][c] = true;
					lands.add(new Land(r,c));
				}
			}
		}
		for(Land land:lands) {
			MAX = Math.max(MAX, bfs(land));
		}
		System.out.println(MAX);
	}
	
	private static int bfs(Land start) {
		int cnt = 0;
		Queue<Land> que = new ArrayDeque<>();
		boolean[][] visited = new boolean[R][C];
		que.offer(start);
		visited[start.row][start.col] = true;
		while(!que.isEmpty()) {
			int size = que.size();
			for(int s=0;s<size;s++) {
				Land land = que.poll();
				for(int d=0; d<4; d++) {
					int nr = land.row + delta[d][0];
					int nc = land.col + delta[d][1];
					if(!isIn(nr,nc)||visited[nr][nc]||!map[nr][nc]) continue;
					que.add(new Land(nr,nc));
					visited[nr][nc] = true;
				}
			}
			if(que.size()==0) break;
			cnt++;
		}
		return cnt;
	}

	private static boolean isIn(int row,int col) {
		return 0<=row&&row<R&&0<=col&&col<C?true:false;
	}
}
