import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576 {
	static int N, M, box[][];
	static Queue<tomato> q = new LinkedList<>();
	static int[][] delta = {{0,1},{1,0},{-1,0},{0,-1}};
	static class tomato{
		int x; 
		int y;
		public tomato(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		box = new int[N][M];
		for(int i = 0 ; i<N ;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j<M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				if(box[i][j] == 1) {
					q.add(new tomato(i,j));
				}
			}
		}
		System.out.println(seek());

		
	}
	
	public static int seek() {
		int result = 1;
		while(!q.isEmpty()) {
			tomato present = q.poll();
			for(int d = 0 ; d<4 ; d++) {
				int nr = present.x+delta[d][0];
				int nc = present.y+delta[d][1];
				if(nr>=0 && nr<N && nc>=0 && nc<M && box[nr][nc] == 0) {
					box[nr][nc] = box[present.x][present.y]+1;
					 q.add(new tomato(nr, nc));
				}
			}
			
		}
		for(int i = 0 ; i<N ; i++) {
			for(int j = 0 ; j<M ; j++) {
				if(box[i][j] == 0) return -1;
				if(box[i][j] == -1) continue;
				if(box[i][j]>result) result = box[i][j];
			}
		}
		return result-1;
	}

}
