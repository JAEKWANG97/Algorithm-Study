
import java.util.*;
import java.io.*;

public class BOJ_4179 {
	static int R, C;
	static char[][] G;	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		G = new char[R][C];
		
		Queue<Move> qJ = new ArrayDeque<>();
		Queue<Move> qF = new ArrayDeque<>();
		
		for (int r = 0; r < R; r++) {
			String row = br.readLine();
			for (int c = 0; c < C; c++) {
				G[r][c] = row.charAt(c);
				if (G[r][c] == 'J') {
					qJ.offer(new Move(r, c, 0));
				} 
				if (G[r][c] == 'F') {
					qF.offer(new Move(r, c, 0));
				}
			}
		}
		int result = bfs(qJ, qF);
		System.out.println(result == -1 ? "IMPOSSIBLE" : result);
	}
	
	static int bfs(Queue<Move> qJ, Queue<Move> qF) {
		int[] dr = new int[] {-1, 1, 0, 0,};
		int[] dc = new int[] {0, 0, -1, 1};
		boolean[][] visited = new boolean[R][C];
		int depth = 0;		
		while (true) {
			
			while (!qF.isEmpty() && qF.peek().depth == depth) {
				Move f = qF.poll();
				//불 먼저
				for (int i = 0; i < 4; i++) {
					int nr = f.r + dr[i];
					int nc = f.c + dc[i];
					
					if (nr < 0 || nr > R - 1 || nc < 0 || nc > C - 1 
							|| G[nr][nc] == '#' || G[nr][nc] == 'F') {
						continue;
					}
					G[nr][nc] = 'F';
					qF.offer(new Move(nr, nc, depth + 1));
				}				
			}
			
			while (!qJ.isEmpty() && qJ.peek().depth == depth) {				
				Move j = qJ.poll();
				
				for (int i = 0; i < 4; i++) {
					int nr = j.r + dr[i];
					int nc = j.c + dc[i];
					
					if (nr < 0 || nr > R - 1 || nc < 0 || nc > C - 1) {
						return j.depth + 1;
					}
					
					if (visited[nr][nc] || G[nr][nc] != '.') {
						continue;
					}
					visited[nr][nc]= true; 
					qJ.offer(new Move(nr, nc, j.depth + 1));
				}	
			}
			
			depth += 1;		
			if (qJ.isEmpty()) {
				return -1;
			}
		}
	}
}

class Move {
	int r;
	int c;
	int depth;
	
	Move(int r, int c, int depth) {
		this.r = r;
		this.c = c;
		this.depth = depth;
	}
	
}
