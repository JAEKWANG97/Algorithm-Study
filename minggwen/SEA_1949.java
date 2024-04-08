import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SEA_1949 {

	static int N,K,map[][],max;
	static boolean visited[][];
	static class StartPoint{
		int row,col;

		public StartPoint(int row, int col) {
			this.row = row;
			this.col = col;
		}

		@Override
		public String toString() {
			return "StartPoint [row=" + row + ", col=" + col + "]"+"\n";
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			sb.append("#"+t+" ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			int high = 0;
			for(int r=0;r<N;r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0;c<N;c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					high = Math.max(high, map[r][c]);
				}
			}
			List<StartPoint> starts = new ArrayList<>();
			max = 0;
			for(int r = 0; r<N;r++) {
				for(int c=0;c<N;c++) {
					if(high==map[r][c]) {
						starts.add(new StartPoint(r, c));
					}
				}
			}
			for(int idx=0;idx<starts.size();idx++) {
				visited = new boolean[N][N];
				visited[starts.get(idx).row][starts.get(idx).col]=true;
				visited[starts.get(idx).row][starts.get(idx).col]=true;
				dfs(starts.get(idx).row,starts.get(idx).col,0,1);
			}
			sb.append(max).append("\n");
		}
		System.out.println(sb.toString());
	}
	private static void dfs(int row,int col, int cut, int cnt) {
		int delta[][] = {{-1,0},{1,0},{0,-1},{0,1}};
		if(cut==1) {
			for(int d=0;d<4;d++) {
				int nr = row+delta[d][0];
				int nc = col+delta[d][1];
				if(!isIn(nr,nc)||visited[nr][nc]||map[row][col]<=map[nr][nc])continue;
				visited[nr][nc] = true;
				dfs(nr,nc,cut,cnt+1);
				visited[nr][nc] = false;
			}
		}else {
			for(int d=0;d<4;d++) {
				int nr = row+delta[d][0];
				int nc = col+delta[d][1];
				if(!isIn(nr,nc)||visited[nr][nc])continue;
				if(map[row][col]<=map[nr][nc]) {
					if(map[nr][nc]-K<map[row][col]) {
						int tmp = map[nr][nc];
						map[nr][nc] = map[row][col]-1;
						visited[nr][nc] = true;
						dfs(nr,nc,cut+1,cnt+1);
						map[nr][nc] = tmp;
						visited[nr][nc] = false;
					}
				}
				else {
					visited[nr][nc] = true;
					dfs(nr,nc,cut,cnt+1);
					visited[nr][nc] = false;
				}
			}
		}
		max = Math.max(cnt, max);
	}
	private static boolean isIn(int row,int col) {
		return 0<=row&&row<N&&0<=col&&col<N?true:false;
	}

}
