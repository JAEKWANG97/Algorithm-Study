import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class BOJ_Main_2573 {

	static class Ice{
		int row,col,h;
		public Ice(int row, int col,int h) {
			this.row = row;
			this.col = col;
			this.h = h;
		}
		
		@Override
		public String toString() {
			return "row : "+row + " , col : "+col+"\n";
		}
	}
	static int N,M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		List<Ice> iceberg = new ArrayList<>();
		for(int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			for(int m=0; m<M;m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
				if(map[n][m]!=0) iceberg.add(new Ice(n,m,map[n][m]));
			}
		}
		
		int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
		int time = 0;
		Queue<Ice> que = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		que.add(iceberg.get(0));
		visited[iceberg.get(0).row][iceberg.get(0).col]= true;
		int cnt = 0;
		while(!que.isEmpty()) {
			cnt++;
			Ice i= que.poll();
			for(int d=0;d<4;d++) {
				int nr = i.row+delta[d][0];
				int nc = i.col+delta[d][1];
				if(isIn(nr,nc)&&map[nr][nc]>0&&!visited[nr][nc]) {
					que.add(new Ice(nr,nc,map[nr][nc]));
					visited[nr][nc] = true;
				}
			}
		}
		if(cnt<iceberg.size()) {
			System.out.println(0);
			System.exit(0);
		}
		while(true) {
			time++;
			for(int idx = 0; idx<iceberg.size();idx++) {
				for(int d=0;d<4;d++) {
					int nr = iceberg.get(idx).row+delta[d][0];
					int nc = iceberg.get(idx).col+delta[d][1];
					if(isIn(nr,nc)&&map[nr][nc]==0) {
						iceberg.get(idx).h--;
					}
				}
			}
			for(int idx = 0; idx<iceberg.size();idx++) {
				if(iceberg.get(idx).h<=0) {
					map[iceberg.get(idx).row][iceberg.get(idx).col] = 0;
					iceberg.remove(idx);
					idx--;
				}
			}
			if(iceberg.size()==0) {
				time = 0;
				break;
			}
			//분리된 빙하 찾기 
			
			visited = new boolean[N][M];
			que.add(iceberg.get(0));
			visited[iceberg.get(0).row][iceberg.get(0).col]= true;
			cnt = 0;
			while(!que.isEmpty()) {
				cnt++;
				Ice i= que.poll();
				for(int d=0;d<4;d++) {
					int nr = i.row+delta[d][0];
					int nc = i.col+delta[d][1];
					if(isIn(nr,nc)&&map[nr][nc]>0&&!visited[nr][nc]) {
						que.add(new Ice(nr,nc,map[nr][nc]));
						visited[nr][nc] = true;
					}
				}
			}
			if(cnt<iceberg.size()) {
				break;
			}
		}
		System.out.println(time);
	}
	private static boolean isIn(int row, int col) {
		return 0<=row&&row<N&&0<=col&&col<M?true:false;
	}

}
