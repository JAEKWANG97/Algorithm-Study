import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Main_4179 {

	static int R,C;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int jihoon_r = 0;
		int jihoon_c = 0;
		Queue<int[]> fire = new ArrayDeque<>();
		boolean[][] map=new boolean[R+2][C+2];
		for(int r=0;r<R;r++) {
			String str = br.readLine();
			for(int c=0;c<C;c++) {
				if(str.charAt(c)=='.') {
					map[r+1][c+1] = true;
				}else if(str.charAt(c)=='J') {
					jihoon_r = r+1;
					jihoon_c = c+1;
				}else if(str.charAt(c)=='F') {
					fire.add(new int[] {r+1,c+1});
				}
			}
		}
		
		Queue<int[]> que = new ArrayDeque<>();
		
		int tmp[] = {jihoon_r,jihoon_c};
		boolean visitedJ[][] = new boolean[R+2][C+2];
		for(int c =0; c<=C+1;c++) {
			map[R+1][c] = true;
			map[0][c] = true;
		}
		for(int r =0; r<=R+1;r++) {
			map[r][C+1] = true;
			map[r][0] = true;
		}
		que.add(tmp);
		int[][] delta = {{-1,0},{1,0},{0,1},{0,-1}};
		int time = 0;
		boolean flag = false;
		while(true) {
			int size = fire.size();
			for(int s=0;s<size;s++) {
				int row = fire.peek()[0];
				int col = fire.peek()[1];
				fire.poll();
				for(int d =0;d<4;d++) {
					int nr = row+delta[d][0];
					int nc = col+delta[d][1];
					if(isInF(nr,nc)&&map[nr][nc]) {
						map[nr][nc] = false;
						fire.add(new int[] {nr,nc});
					}
				}
			}
			int qsize = que.size();
			for(int s=0;s<qsize;s++) {
				int row = que.peek()[0];
				int col = que.peek()[1];
				que.poll();
				if(row==R+1||col==C+1||row==0||col==0) {
					flag = true;
					break;
				}
				for(int d =0;d<4;d++) {
					int nr = row+delta[d][0];
					int nc = col+delta[d][1];
					if(isIn(nr,nc)&&!visitedJ[nr][nc]&&map[nr][nc]) {
						visitedJ[nr][nc] = true;
						que.add(new int[] {nr,nc});
					}
				}
			}
			if(flag) break;
			
			if(que.isEmpty()) {
				System.out.println("IMPOSSIBLE");
				System.exit(0);
			}
			time++;
			
		}
		System.out.println(time);
	}
	private static boolean isIn(int row,int col) {
		return 0<=row&&row<=R+1&&0<=col&&col<=C+1?true:false;
	}
	private static boolean isInF(int row,int col) {
		return 0<row&&row<R+1&&0<col&&col<C+1?true:false;
	}
}
