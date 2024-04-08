import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Main_2206 {
	
	static class Move{
		int row,col, crush;

		public Move(int row, int col,int crush) {
			this.row = row;
			this.col = col;
			this.crush = crush;
		}
		
	}
	static int N,M;
	static boolean[][] map;
	static int[][] delta = {{0,1},{0,-1},{1,0},{-1,0}};
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map = new boolean[N][M];
		visited = new boolean[N][M];

		for(int n = 0; n<N;n++) {
			String tmp = br.readLine();
			for(int m=0;m<M;m++) {
				if(tmp.charAt(m)=='0')map[n][m] = true;
			}
		}
		
		
		System.out.println(bfs());
	}

	private static int bfs() {
		int cnt = 0;
		Queue<Move> que = new ArrayDeque<>();
		boolean[][][] visited=new boolean[N][M][2];
		que.add(new Move(0,0,0));
		visited[0][0][0] = true;
		while(!que.isEmpty()) {
			int size = que.size();
			cnt++;
			for(int s=0;s<size;s++) {
				Move m = que.poll();
				int row = m.row;
				int col = m.col;
				int f = m.crush;
				if(row==N-1&&col==M-1) {
					return cnt;
				}
				for(int d=0;d<4;d++) {
					int nr = row+delta[d][0];
					int nc = col+delta[d][1];
					if(isIn(nr,nc)) {
						if(!visited[nr][nc][f]&&map[nr][nc]) {
							que.add(new Move(nr,nc,f));
							visited[nr][nc][f] = true;
						}else if(f==0&&!visited[nr][nc][1]&&!map[nr][nc]) {
							que.add(new Move(nr,nc,1));
							visited[nr][nc][1] = true;
						}
					}
				}
			}
		}
		return -1;
	}
	private static boolean isIn(int row,int col) {
		return 0<=row&&row<N&&0<=col&&col<M?true:false;
	}
}
