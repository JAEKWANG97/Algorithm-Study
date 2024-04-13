import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Main_3055 {

	static int R,C;
	static char[][] map;
	static boolean[][] visited;
	static int delta[][] = {{1,0},{-1,0},{0,-1},{0,1}};
	static Queue<int[]> que = new ArrayDeque<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int startR=0;
		int startC=0;
		int endR=0;
		int endC=0;
		map = new char[R][C];
		visited = new boolean[R][C];
		for(int r=0;r<R;r++) {
			String str = br.readLine();
			for(int c=0;c<C;c++) {
				map[r][c] = str.charAt(c);
				if(map[r][c]=='D') {
					endR = r;
					endC = c;
					map[r][c] = 'D';
				}else if(map[r][c]=='S') {
					startR = r;
					startC = c;
				}else if(map[r][c]=='*') {
					que.add(new int[] {r,c});
				}
			}
		}
		Queue<int[]> move = new ArrayDeque<>();
		move.add(new int[] {startR,startC});
		visited[startR][startC] = true;
		boolean flag = false;
		int time = 0;
		while(!move.isEmpty()) {
			int size = move.size();
			water();
			for(int s=0;s<size;s++) {
				int r = move.peek()[0];
				int c = move.peek()[1];
				move.poll();
				if(r==endR&&c==endC) {
					flag = true;
					break;
				}
				for(int d=0;d<4;d++) {
					int nr = r+delta[d][0];
					int nc = c+delta[d][1];
					if(isIn(nr,nc)&&!visited[nr][nc]&&(map[nr][nc]=='.'||map[nr][nc]=='D')) {
						move.add(new int[] {nr,nc});
						visited[nr][nc] = true;
					}
				}
			}
			
			if(flag)break;
			time++;
		}
		
		System.out.println(flag?time:"KAKTUS");
	}
	
	private static void water() {
		int size = que.size();
		for(int s=0;s<size;s++) {
			int r = que.peek()[0];
			int c = que.peek()[1];
			que.poll();
			for(int d=0;d<4;d++) {
				int nr = r+delta[d][0];
				int nc = c+delta[d][1];
				if(isIn(nr,nc)&&map[nr][nc]=='.') {
					que.add(new int[] {nr,nc});
					map[nr][nc] = '*';
				}
			}
		}
	}
	private static boolean isIn(int row,int col) {
		return 0<=row&&row<R&&0<=col&&col<C?true:false;
	}

}
