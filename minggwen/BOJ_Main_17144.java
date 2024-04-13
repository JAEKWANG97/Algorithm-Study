import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Main_17144 {

	static class Dust{
		int r,c,num;

		public Dust(int r, int c, int num) {
			this.r = r;
			this.c = c;
			this.num = num;
		}
		
	}
	static int R,C,map[][];
	static int delta[][] = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		map = new int [R][C];
		List<Integer> elecArr = new ArrayList<>();
		boolean flag = false;
		for(int r=0;r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0;c<C;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c]==-1&&!flag) {
					flag = true;
				}else if(map[r][c]==-1&&flag) {
					elecArr.add(r-1);
					flag = false;
				}
			}
		}
		for(int t=0;t<T;t++) {
			bfs();
			move(elecArr);
		}
		int sum = 0 ;
		for(int r=0;r<R;r++) {
			for(int c=0;c<C;c++) {
				if(map[r][c]==-1)continue;
				sum+=map[r][c];
			}
		}
		System.out.println(sum);
	}
	private static void bfs() {
		Queue<int[]> que = new ArrayDeque<>();
		Queue<Dust> activate = new ArrayDeque<>();
		for(int r=0;r<R;r++) {
			for(int c=0;c<C;c++) {
				if(map[r][c]>0) {
					activate.add(new Dust(r,c,map[r][c]));
				}
			}
		}
		int num = 0;
		for(Dust dust:activate) {
				for(int d=0;d<4;d++) {
					int nr = dust.r+delta[d][0];
					int nc = dust.c+delta[d][1];
					if(isIn(nr,nc)&&map[nr][nc]>=0) {
						que.add(new int[] {nr,nc});
					}
				}
				num = dust.num/5;
				map[dust.r][dust.c] -= num*que.size();
				while(!que.isEmpty()) {
					int tr = que.peek()[0];
					int tc = que.peek()[1];
					que.poll();
					map[tr][tc] += num;
				}
			
		}
	}
	private static void move(List<Integer> elecArr) {
		int[][] copy = new int[R][C];
		for(int r=0;r<R;r++) {
			for(int c=0;c<C;c++) {
				copy[r][c] = map[r][c];
			}
		}
	
		for(int elec : elecArr) {
			
			for(int c=2;c<C;c++) {
				map[elec][c] = copy[elec][c-1];
			}
			map[elec][1] = 0;
			for(int r=elec;r>0;r--) {
				map[r-1][C-1] = copy[r][C-1];
			}
			for(int c=C-1;c>=1;c--) {
				map[0][c-1] = copy[0][c];
			}
			for(int r=1;r<elec;r++) {
				map[r][0] = copy[r-1][0];
			}
			for(int c=2;c<=C-1;c++) {
				map[elec+1][c] = copy[elec+1][c-1];
			}
			map[elec+1][1] = 0;
			
			for(int r=elec+2;r<=R-1;r++) {
				map[r][C-1] = copy[r-1][C-1];
			}
			for(int c=0;c<C-1;c++) {
				map[R-1][c] = copy[R-1][c+1];
			}
			for(int r=R-1;r>elec+2;r--) {
				map[r-1][0] = copy[r][0];
			}
		}
	}
	private static boolean isIn(int row,int col) {
		return 0<=row&&row<R&&0<=col&&col<C?true:false;
	}

}
