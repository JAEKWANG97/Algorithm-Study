import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Main_16234 {

	static int N,L,R;
	static int[][] map;
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0;c<N;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		int day=0;
		for(;;day++) {
			if(!bfs())break;
		}
		System.out.println(day);
	}
	private static boolean bfs() {
		Queue<int[]> que = new ArrayDeque<>();
		List<int[]> union = new ArrayList<>();
		boolean visited[][] = new boolean[N][N];
		boolean flag = false;
		int sum = 0;
		for(int r=0;r<N;r++) {
			for(int c=0;c<N;c++) {
				sum=0;
				union.clear();
				if(visited[r][c])continue;
				que.add(new int[] {r,c});
				while(!que.isEmpty()) {
					int row = que.peek()[0];
					int col = que.peek()[1];
					que.poll();
					for(int d=0;d<4;d++) {
						int nr = row+delta[d][0];
						int nc = col+delta[d][1];
						if(isIn(nr,nc)&&!visited[nr][nc]&&L<=Math.abs(map[row][col]-map[nr][nc])&&Math.abs(map[row][col]-map[nr][nc])<=R) {
							que.add(new int[] {nr,nc});
							union.add(new int[] {nr,nc});
							visited[nr][nc]=true;
							sum+=map[nr][nc];
							if(!visited[r][c]) {
								visited[r][c] = true;
								sum+=map[r][c];
								union.add(new int[] {r,c});
							}
						}
					}
				}
				if(union.size()>0) {
					flag=true;
					int avg = sum/union.size();
					for(int[] tmp : union) {
						map[tmp[0]][tmp[1]] = avg;
					}
					
				}
			}
		}
		return flag?true:false;
	}
	private static boolean isIn(int row,int col) {
		return 0<=row&&row<N&&0<=col&&col<N?true:false;
	}
}
