import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Point{
		int x,y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n,m;
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		char[][] board = new char[n][m];
		
		for(int i = 0; i < n ; i++) {
			String input = br.readLine();
			for(int j = 0; j < m; j++) {
				board[i][j] = input.charAt(j);
			}
		}
		
		int answer = 0;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(board[i][j] == 'L') {
					int result = bfs(new Point(i, j), board, n, m);
					answer = Math.max(answer, result);
				}
			}
		}
	
		System.out.println(answer);
	}
	
	private static int bfs(Point start, char[][] board, int n, int m) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(start);
		
		int[][] visited = new int[n][m];
		for(int i = 0; i < n; i++) {
			Arrays.fill(visited[i], -1);
		}
		visited[start.x][start.y] = 0;

		int max = 0;
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		while(!queue.isEmpty()) {
			Point poll = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = poll.x + dx[i];
				int ny = poll.y + dy[i];
				
				if(nx >= 0 && ny >= 0 && nx < n && ny < m 
						&& visited[nx][ny] == -1 && board[nx][ny] == 'L') {
					visited[nx][ny] = visited[poll.x][poll.y] + 1;
					queue.add(new Point(nx, ny));
					
					max = Math.max(visited[nx][ny], max);
				}
			}
		}
		
		return max;	
	}
}

