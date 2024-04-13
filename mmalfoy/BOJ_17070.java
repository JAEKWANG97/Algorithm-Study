import java.util.*;
import java.io.*;

public class BOJ_17070 {
	static int[][] G;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		G = new int[N][N];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int m = 0; m < N; m++) {
				G[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		
		Queue<Pipe> q = new ArrayDeque<>();
		q.add(new Pipe(0, 1, 0));
		System.out.println(bfs(q, N));
	}
	
	static int bfs(Queue<Pipe> q, int N) {
		int cnt = 0;
		
		while(!q.isEmpty()) {
			Pipe pipe = q.poll();
			if (pipe.hy == N - 1 && pipe.hx == N - 1) {
				cnt += 1;
				continue;
			}
			
			int ny, nx;
			// 우, 우하, 하
			if (pipe.status == 0) {
				if (pipe.hx + 1 < N && G[pipe.hy][pipe.hx + 1] == 0) {
					q.offer(new Pipe(pipe.hy, pipe.hx + 1, 0));
				}
				
				if (pipe.hy + 1 < N && pipe.hx + 1 < N && 
						G[pipe.hy + 1][pipe.hx + 1] == 0 && G[pipe.hy][pipe.hx + 1] == 0 && G[pipe.hy + 1][pipe.hx] == 0) {					
					q.offer(new Pipe(pipe.hy + 1, pipe.hx + 1, 1));
				}		
				
			} else if (pipe.status == 1) {
				
				if (pipe.hx + 1 < N && G[pipe.hy][pipe.hx + 1] == 0) {
					q.offer(new Pipe(pipe.hy, pipe.hx + 1, 0));
				}
				
				if (pipe.hy + 1 < N && pipe.hx + 1 < N && 
						G[pipe.hy + 1][pipe.hx + 1] == 0 && G[pipe.hy][pipe.hx + 1] == 0 && G[pipe.hy + 1][pipe.hx] == 0) {					
					q.offer(new Pipe(pipe.hy + 1, pipe.hx + 1, 1));
				}		
				
				if (pipe.hy + 1 < N && G[pipe.hy + 1][pipe.hx] == 0) {
					q.offer(new Pipe(pipe.hy + 1, pipe.hx, 2));
				}
				
			} else if (pipe.status == 2) {
				if (pipe.hy + 1 < N && pipe.hx + 1 < N && 
						G[pipe.hy + 1][pipe.hx + 1] == 0 && G[pipe.hy][pipe.hx + 1] == 0 && G[pipe.hy + 1][pipe.hx] == 0) {					
					q.offer(new Pipe(pipe.hy + 1, pipe.hx + 1, 1));
				}		
				
				if (pipe.hy + 1 < N && G[pipe.hy + 1][pipe.hx] == 0) {
					q.offer(new Pipe(pipe.hy + 1, pipe.hx, 2));
				}	
			}
		}

		return cnt;
	}
	
	static class Pipe {
		int hy, hx, status;
		Pipe(int hy, int hx, int status) {
			this.hy = hy;
			this.hx = hx;
			this.status = status;
		}
	}
}
