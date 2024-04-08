import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Main_17070 {

	static int[][] dp;
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		dp = new int[N][N];
		for(int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0;c<N;c++) {
				int tmp = Integer.parseInt(st.nextToken());
				if(tmp==1) {
					dp[r][c] = -1;
				}
			}
		}

		dfs(0,1,0);
		System.out.println(dp[N-1][N-1]!=-1?dp[N-1][N-1]:0);
	}
	private static void dfs(int row,int col,int pipe) {//0 : 가로 1: 세로 2: 대각선
		if(!isIn(row,col)||dp[row][col]==-1)return;
		if(pipe==2) {
			if(isIn(row-1,col)&&dp[row-1][col]==-1) return;
			if(isIn(row,col-1)&&dp[row][col-1]==-1) return;
		}
		dp[row][col]++;
		if(pipe==0) {
			dfs(row,col+1,0);
			dfs(row+1,col+1,2);
		}else if(pipe==1) {
			dfs(row+1,col,1);
			dfs(row+1,col+1,2);
		}else {
			dfs(row,col+1,0);
			dfs(row+1,col,1);
			dfs(row+1,col+1,2);
		}
	}
	private static boolean isIn(int row,int col) {
		return 0<=row&&row<N&&0<=col&&col<N ? true:false;
	}
}