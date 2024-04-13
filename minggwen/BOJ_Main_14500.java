import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class BOJ_Main_14500 {

	static int max = 0;
	static int N,M;
	static int map[][];
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			for(int m=0;m<M;m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		for(int n=0;n<N;n++) {
			for(int m=0;m<M;m++) {
				boolean[][] visited = new boolean[n+4][m+4];
				visited[n][m] = true;
				dfs(1,n,m,map[n][m],visited);
			}
		}
		System.out.println(max);
	}
	private static void dfs(int cnt, int row, int col,int sum,boolean visited[][]) {
		if(cnt==4) {
			max = Math.max(max,sum);
			return;
		}
		for(int d=0;d<4;d++) {
			int nr = row+delta[d][0];
			int nc = col+delta[d][1];
			if(isIn(nr,nc)&&!visited[nr][nc]) {
				visited[nr][nc] = true;
				dfs(cnt+1,nr,nc,sum+map[nr][nc],visited);
				dfs(cnt+1,row,col,sum+map[nr][nc],visited);
				visited[nr][nc] = false;
			}
		}
	}
	private static boolean isIn(int row,int col) {
		return 0<=row&&row<N&&0<=col&&col<M?true:false;
	}
}
