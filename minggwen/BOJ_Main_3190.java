import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Main_3190 {
	static class Change{
		int seconds;
		char LR;
		public Change(int seconds, char LR) {
			this.seconds = seconds;
			this.LR = LR;
		}
		
	}
	static List<int[]> apples;
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		apples = new ArrayList<>();
		for(int k=0;k<K;k++) {
			st = new StringTokenizer(br.readLine());
			apples.add(new int[] {Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1});
		}
		Queue<Change> arr = new ArrayDeque<>();
		int L = Integer.parseInt(br.readLine());
		for(int l=0;l<L;l++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			char lr = st.nextToken().charAt(0);
			arr.add(new Change(s,lr));
		}
		int time = 0;
		int snakeR = 0;
		int snakeC = 0;
		int[][] delta = {{0,1},{1,0},{-1,0},{0,-1}};
		int d = 0;
		boolean snake[][] = new boolean[N][N];
		boolean flag = false;
		Queue<int[]> body = new ArrayDeque<>();
		snake[0][0] = true;
		body.offer(new int[] {0,0});
	while(true) {
			time++;
			int nr = snakeR+delta[d][0];
			int nc = snakeC+delta[d][1];
			if(!isIn(nr,nc)) {
				flag = true;
				break;
			}
			if(snake[nr][nc]) {
				flag = true;
				break;
			}
			if(!eatApple(nr,nc)) {
				int r = body.peek()[0];
				int c = body.peek()[1];
				snake[r][c] = false;
				body.poll();
			}
			
			body.offer(new int[] {nr,nc});
			snake[nr][nc] = true;
			snakeR = nr;
			snakeC = nc;
		
		if(flag)break;
		if(arr.isEmpty())continue;
		if(arr.peek().seconds==time) {
			char ch = arr.poll().LR;
			if(d==0&&ch=='D') d=1;
			else if(d==0&&ch=='L') d=2;
			else if(d==3&&ch=='D')d=2;
			else if(d==3&&ch=='L')d=1;
			else if(d==1&&ch=='L')d=0;
			else if(d==1&&ch=='D')d=3;
			else if(d==2&&ch=='L')d=3;
			else if(d==2&&ch=='D')d=0;
			}
		}
		System.out.println(time);
	}

	private static boolean isIn(int row,int col) {
		return 0<=row&&row<N&&0<=col&&col<N?true:false;
	}
	private static boolean eatApple(int row,int col) {
		for(int idx =0;idx<apples.size();idx++) {
			if(row==apples.get(idx)[0]&&col==apples.get(idx)[1]) {
				apples.remove(idx);
				return true;
			}
		}
		return false;
	}
}
