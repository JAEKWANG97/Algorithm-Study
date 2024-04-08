import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int delta[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][C];
		int endx = 0, endy = 0, startx = 0, starty = 0;
		Queue<int[]> water = new LinkedList<>();
		Queue<int[]> loc = new LinkedList<>();
		for(int i = 0 ; i<R ; i++) {
			String str = br.readLine();
			for(int j = 0 ; j<C ; j++) {
				map[i][j] =str.charAt(j);
				if(map[i][j] == 'S') {
					loc.add(new int[] {i,j});
					map[i][j] = ',';
				}
				if(map[i][j] == '*') {
					water.add(new int[] {i,j}); 
				}
			}
		}
		int time = 1;
		while(!loc.isEmpty()) {
			int watersize = water.size();
			for(int i = 0 ; i<watersize ; i++) {
				 int[] cur = water.poll();
				 for(int d= 0 ; d<4 ; d++) {
					 int nr = cur[0] + delta[d][0];
					 int nc = cur[1] + delta[d][1];
					 if(nr>=0 && nr<R && nc>=0 && nc<C && map[nr][nc]!='X' && map[nr][nc] !='D'&&map[nr][nc]!='*') {
						 water.add(new int[] {nr, nc});
						 map[nr][nc] = '*';
					 }
				 }
			}
			int locsize = loc.size();
			for(int i = 0 ;i<locsize ; i++) {
				int[] cur = loc.poll();
				for(int d = 0 ; d<4 ;d++) {
					int nr = cur[0]+delta[d][0];
					int nc = cur[1]+delta[d][1];
					if(nr>=0 && nr<R && nc>=0 && nc<C ) {
						if(map[nr][nc] == 'D') {
							System.out.println(time);
							return;
						}
						if(map[nr][nc] =='.') {
							loc.add(new int[] {nr,nc});
							map[nr][nc] = ',';
						}
					}
				}
			}
			time++;
			
		}
		System.out.println("KAKTUS");


	}

}
