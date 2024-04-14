import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int[][] delta = {{1,0},{0,1},{-1,0},{0,-1}};
	static class room implements Comparable<room>{
		int r;
		int c;
		int cnt;
		public room(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt =cnt;
		}
		public int compareTo(room o) {
			return this.cnt-o.cnt;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		int black = 0;
		for(int i = 0 ; i<N ; i++) {
			String str = br.readLine();
			for(int j = 0 ; j<N ;j++) {
				map[i][j] = str.charAt(j)-'0'; //0:검은방, 1:흰방
				if(map[i][j]==0) black++;
			}
		}
		int result = 0;
		boolean[][][] visit = new boolean[black][N][N];
		PriorityQueue<room> pq = new PriorityQueue<>();
		pq.add(new room(0,0,0));
		while(!pq.isEmpty()) {
			room cur = pq.poll();
			if(cur.r==N-1 && cur.c==N-1) {
				result = cur.cnt;
				break;
			}
			if(visit[cur.cnt][cur.r][cur.c]) continue;
			visit[cur.cnt][cur.r][cur.c] = true;
			for(int d = 0 ; d<4 ;d++) {
				int nr = cur.r+delta[d][0];
				int nc = cur.c+delta[d][1];
				if(nr>=0 && nr<N && nc>=0 && nc<N) {
					if(map[nr][nc] == 1 && !visit[cur.cnt][nr][nc]) {
						pq.add(new room(nr,nc,cur.cnt));
					}else if(map[nr][nc] == 0 && !visit[cur.cnt+1][nr][nc]){
						pq.add(new room(nr,nc,cur.cnt+1));
					}
				}
			}
			
		}
		System.out.println(result);
	}
}
