import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Main_6593 {

	static int L,R,C;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		while(true) {
			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			if(L==0&&R==0&&C==0) break;
			int startF = 0;
			int startX = 0;
			int startY = 0;
			int endF = 0;
			int endX = 0;
			int endY = 0;
			boolean map[][][] = new boolean[L][R][C];
			boolean visited[][][] = new boolean[L][R][C];
			for(int l=0;l<L;l++) {
				for(int r=0;r<R;r++) {
					String str = br.readLine();
					for(int c=0;c<C;c++) {
						char ch = str.charAt(c);
						if(ch=='.') map[l][r][c] = true;
						else if(ch=='S') {
							startF = l;
							startX = r;
							startY = c;
						}else if(ch=='E') {
							endF = l;
							endX = r;
							endY = c;
							map[l][r][c] = true;
						}
					}
				}
				br.readLine();
			}
			int delta[][] = {{0,0,-1},{0,0,1},{0,-1,0},{0,1,0},{1,0,0},{-1,0,0}};
			Queue<int[]> que = new ArrayDeque<>();
			que.add(new int[] {startF,startX,startY});
			boolean flag = false;
			int time = 0;
			while(!que.isEmpty()) {
				int size = que.size();
				for(int s=0;s<size;s++) {
					int f = que.peek()[0];
					int x = que.peek()[1];
					int y = que.peek()[2];
					if(f==endF&&x==endX&&y==endY) {
						flag = true;
						break;
					}
					que.poll();
					for(int d =0;d<6;d++) {
						int nf = f+delta[d][0];
						int nx = x+delta[d][1];
						int ny = y+delta[d][2];
						if(isIn(nf,nx,ny)&&!visited[nf][nx][ny]&&map[nf][nx][ny]) {
							que.add(new int[] {nf,nx,ny});
							visited[nf][nx][ny] = true;
						}
					}
				}
				if(flag) break;
				time++;
			}
			if(!flag) System.out.println("Trapped!");
			else System.out.println("Escaped in "+time+" minute(s).");
		}
	}
	private static boolean isIn(int floor,int row,int col) {
		return 0<=floor&&floor<L&&0<=row&&row<R&&0<=col&&col<C?true:false;
	}

}
