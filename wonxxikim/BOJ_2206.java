import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,M,map[][];
	
	static class loc{
		int x; 
		int y;
		int len;
		int attack;
		public loc(int x, int y, int len, int attack) {
			super();
			this.x = x;
			this.y = y;
			this.len = len;
			this.attack = attack;
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i = 0 ; i<N ; i++) {
			String str = br.readLine();
			for(int j = 0 ; j <M ; j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}
		int result = bfs();
		System.out.println(result);
		

	}
	public static int bfs() {
		Queue<loc> q= new LinkedList<>();
		boolean[][][] visit = new boolean[2][N][M];
		visit[1][0][0] = true;
		q.add(new loc(0,0,1,1));
		int[][] delta = {{1,0},{0,1},{-1,0},{0,-1}};
	
		while(!q.isEmpty()) {
			loc cur = q.poll();
			if(cur.x==N-1 && cur.y==M-1) return cur.len;
			for(int d = 0 ; d<4 ;d++) {
				int nr = cur.x + delta[d][0];
				int nc = cur.y + delta[d][1];
				if(nr>=0 && nr<N && nc>=0 && nc<M && !visit[cur.attack][nr][nc]) {
					if(map[nr][nc] == 0) {
					visit[cur.attack][nr][nc] = true;
					q.add(new loc(nr,nc,cur.len+1,cur.attack));
					}
					else if(map[nr][nc] == 1 && cur.attack==1) {
                        visit[0][nr][nc] = true;
						q.add(new loc(nr,nc,cur.len+1,0));
						
					}
				}
			}
			
		}
		return -1;
	}

}
