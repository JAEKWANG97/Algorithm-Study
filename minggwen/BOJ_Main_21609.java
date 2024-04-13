import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Main_21609 {

	static class Block implements Comparable<Block>{
		int r,c;

		public Block(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Block o) {
			if(this.r==o.r) {
				return this.c-o.c;
			}
			return this.r-o.r;
		}

		@Override
		public String toString() {
			return "Block [r=" + r + ", c=" + c + "]";
		}
		
	}
	static class BlockGroup implements Comparable<BlockGroup>{
		int r,c,num,rainbow;
		List<Block> blocks;

		public BlockGroup(int r, int c, int num, int rainbow, List<Block> blocks) {
			super();
			this.r = r;
			this.c = c;
			this.num = num;
			this.rainbow = rainbow;
			this.blocks = blocks;
		}

		@Override
		public String toString() {
			return "BlockGroup [r=" + r + ", c=" + c + ", num=" + num + ", rainbow=" + rainbow + "]";
		}

		@Override
		public int compareTo(BlockGroup o) {
			if(this.num==o.num) {
				if(this.rainbow==o.rainbow) {
					if(this.r==o.r) {
						return o.c-this.c;
					}
					return o.r-this.r;
				}
				return o.rainbow-this.rainbow;
			}
			return o.num-this.num;
		}
		
	}
	static int N,M,map[][];
	static int delta[][] = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0;c<N;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int result=0;
		while(true) {
			int num = remove();
			if(num<2)break;
			result+=num*num;
			gravity();
			rotate();
			gravity();
		}

		System.out.println(result);
	}
	private static int remove() {
		Queue<int[]> que = new ArrayDeque<>();
		Queue<BlockGroup> arr = new PriorityQueue<>();
		for(int r=0;r<N;r++){
			for(int c=0;c<N;c++){
				if(map[r][c]<=0)continue;
				boolean visited[][]= new boolean[N][N];
				int rainbow = 0;
				int cnt = 0;
				List<Block> blocksArr = new ArrayList<>();
				que.add(new int[] {r,c});
				visited[r][c] = true;
				int color = map[r][c];
				while(!que.isEmpty()) {
					int row = que.peek()[0];
					int col = que.peek()[1];
					blocksArr.add(new Block(row,col));
					que.poll();
					cnt++;
					for(int d=0;d<4;d++) {
						int nr = row+delta[d][0];
						int nc = col+delta[d][1];
						if(!isIn(nr,nc)||visited[nr][nc]) continue;
						if(map[nr][nc]==0||map[nr][nc]==color) {
							if(map[nr][nc]==0)rainbow++;
							visited[nr][nc] = true;
							que.add(new int[] {nr,nc});
						}
					}
				}
				Collections.sort(blocksArr);
				for(int idx = 0; idx<blocksArr.size();idx++) {
					if(map[blocksArr.get(idx).r][blocksArr.get(idx).c]==0)continue;
					arr.add(new BlockGroup(blocksArr.get(idx).r, blocksArr.get(idx).c, cnt, rainbow,blocksArr));
					break;
				}
				
			}
		}
		if(arr.isEmpty()) return 0;
		for(Block block : arr.peek().blocks) {
			map[block.r][block.c]=-2;
		}
		
		return arr.poll().num;
	}
	
	private static void rotate() {
		int[][] copy=copy();
		for(int r=0;r<N;r++) {
			for(int c=0;c<N;c++) {
				map[r][c] = copy[c][N-1-r];
			}
		}
	
	}
	private static void gravity() {
		for(int r=N-1;r>0;r--) {
			for(int c=0;c<N;c++) {
				if(map[r][c]==-2&&map[r-1][c]>=0) {
					boolean flag = false;
					for(int d=r;d<N;d++) {
						if(map[d][c]>=-1) {
							map[d-1][c] = map[r-1][c];
							map[r-1][c] = -2;
							flag = true;
							break;
						}
					}
					if(!flag) {
						map[N-1][c] = map[r-1][c];
						map[r-1][c] = -2;
					}
				}
			}
		}
		
	}
	
	private static boolean isIn(int row,int col) {
		return 0<=row&&row<N&&0<=col&&col<N?true:false;
	}
	private static int[][] copy(){
		int copy[][] = new int[N][N];
		for(int r=0;r<N;r++) {
			for(int c=0;c<N;c++) {
				copy[r][c] = map[r][c];
			}
		}
		return copy;
	}
	
}
