import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class BOJ_Main_2665 {

	static int N;
	static boolean map[][];
	static int dp[][];
	static List<int[]> blacks;
	static boolean[] visited;
	static int[][] delta = {{-1,0},{1,0},{0,1},{0,-1}};
	static boolean flag = false;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		map = new boolean[N][N];
		dp = new int[N][N];
		blacks = new ArrayList<>();
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<N;j++) {
				if(str.charAt(j)-'0'==1) {
					map[i][j]=true;
				}else {
					map[i][j]=false;
					blacks.add(new int[] {i,j});
				}
				
			}
		}
		for(int tmp[]:dp)Arrays.fill(tmp, Integer.MAX_VALUE);
		dp[0][0] = 0;
		bfs();
		System.out.println(dp[N-1][N-1]);
	}

	private static void bfs() {
		Queue<int[]> que = new ArrayDeque<>();
		que.add(new int[] {0,0});
		while(!que.isEmpty()) {
			int r = que.peek()[0];
			int c = que.peek()[1];
			que.poll();
			for(int d=0;d<4;d++) {
				int nr = r+delta[d][0];
				int nc = c+delta[d][1];
				if(isIn(nr,nc)&&dp[nr][nc]>dp[r][c]) {
					if(map[nr][nc]) dp[nr][nc] = dp[r][c];
					else dp[nr][nc] = dp[r][c]+1;
					que.add(new int[] {nr,nc});
				}
			}
		}
	}
	private static boolean isIn(int row,int col) {
		return 0<=row&&row<N&&0<=col&&col<N?true:false;
	}

}
