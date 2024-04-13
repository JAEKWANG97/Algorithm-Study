import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Point{
		int x, y, bomb, moveCnt;

		public Point(int x, int y, int bomb, int moveCnt) {
			this.x = x;
			this.y = y;
			this.bomb = bomb;
			this.moveCnt = moveCnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n,m;
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int[][] board = new int[n][m];
		
		for(int i = 0; i < n ; i++) {
			String input = br.readLine();
			for(int j = 0; j < m; j++) {
				board[i][j] = input.charAt(j) - '0';
			}
		}
		
		int answer = bfs(n, m, board);
	
		System.out.println(answer);
	}
	
	private static int bfs(int n, int m, int[][] board) {
		boolean[][][] visited = new boolean[n][m][2];
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(new Point(0, 0, 0, 1));
		
		visited[0][0][0] = true;
		
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		while(!queue.isEmpty()) {
 				Point poll = queue.poll();
				
 				if(poll.x == n-1 && poll.y == m-1) {
					return poll.moveCnt;
				}
				
				for(int i = 0; i < 4; i++){
					int nx = poll.x + dx[i];
					int ny = poll.y + dy[i];
					
					if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
						if(board[nx][ny] == 1 && poll.bomb == 0 && !visited[nx][ny][poll.bomb + 1] ) {
							visited[nx][ny][poll.bomb + 1] = true;
							queue.add(new Point(nx, ny, poll.bomb + 1, poll.moveCnt + 1));
						}
						
						if(board[nx][ny] == 0 && !visited[nx][ny][poll.bomb]) {
							visited[nx][ny][poll.bomb] = true;
							queue.add(new Point(nx, ny, poll.bomb, poll.moveCnt + 1));
						}
					}
				
			}
		}
		
		return -1;
	}
}

