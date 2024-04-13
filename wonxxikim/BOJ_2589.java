import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static char[][] map;
	static Queue<loc> q;
	static int[][] delta = {{0,1},{1,0},{-1,0},{0,-1}};
	static boolean[][] visit;
	static class loc{
		int x;
		int y;
		int time;
		public loc(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for(int i = 0 ; i<N ; i++) {
			map[i] = br.readLine().toCharArray();
		}
		int answer = 0;
		for(int i = 0 ; i< N ; i++) {
			for(int j = 0 ; j<M ; j++) {
				if(map[i][j] == 'L') {
					q = new LinkedList<>();
					q.add(new loc(i,j,0));
					visit = new boolean[N][M];
					visit[i][j] = true;
					int max = bfs();
					answer = Math.max(max, answer);
				}
			}
		}
		System.out.println(answer);

	}
	public static int bfs() {
		int result = 0;
		while(!q.isEmpty()) {
			loc cur = q.poll();
			result = cur.time;
			for(int d = 0 ; d<4 ; d++) {
				int nr = cur.x+delta[d][0];
				int nc = cur.y+delta[d][1];
				if(nr>=0 && nr<N && nc>=0 && nc<M && !visit[nr][nc] && map[nr][nc] == 'L') {
					q.add(new loc(nr,nc,cur.time+1));
					visit[nr][nc] = true;
				}
			}
		}
		return result;
	}

}
