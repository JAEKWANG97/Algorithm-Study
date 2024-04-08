import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] delta = { { 0, 1, 0 }, { 0, -1, 0 }, { 0, 0, 1 }, { 0, 0, -1 }, { 1, 0, 0 }, { -1, 0, 0 } };
	static class loc{
		int i;
		int r;
		int c;
		int time;
		public loc(int i, int r, int c, int time) {
			super();
			this.i = i;
			this.r = r;
			this.c = c;
			this.time = time;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			if (L == 0 && R == 0 && C == 0)
				break;
			char[][][] building = new char[L][R][C];
			boolean[][][] visit = new boolean[L][R][C];
			int end_i = 0;
			int end_r = 0;
			int end_c = 0;
			Queue<loc> q = new LinkedList<>();
			for (int i = 0; i < L; i++) {
				for (int r = 0; r < R; r++) {
					char[] str = br.readLine().toCharArray();
					for (int c = 0; c < C; c++) {
						building[i][r][c] = str[c];
						if(building[i][r][c] =='S') {
							q.add(new loc(i,r,c,0));
							visit[i][r][c] = true;
						}
						if(building[i][r][c] =='E') {
							end_i = i;
							end_r = r;
							end_c = c;
						}
					}
				}
				br.readLine();
			}
			int mintime = -1;
			while(!q.isEmpty()) {
				loc cur = q.poll();
				if(cur.i==end_i && cur.r==end_r && cur.c==end_c) {
					mintime = cur.time;
					break;
				}
				for(int d = 0 ; d<6 ; d++) {
					int ni = cur.i+delta[d][0];
					int nr = cur.r+delta[d][1];
					int nc = cur.c+delta[d][2];
					if(ni>=0 && ni<L && nr>=0 && nr<R && nc>=0 && nc<C && building[ni][nr][nc]!='#'&&!visit[ni][nr][nc]) {
						q.add(new loc(ni,nr,nc,cur.time+1));
						visit[ni][nr][nc]= true;
					}
				}
			}
			
			if(mintime == -1) System.out.println("Trapped!");
			else System.out.println("Escaped in "+mintime+" minute(s).");
		}

	}
}
