import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
		
	static class Point{
		int x, y;

		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static final char JIHUN = 'J';
	private static final char FIRE = 'F';
	private static final char ROAD = '.';
	private static int r, c;
	private static char[][] board;
	private static int jihunCnt;
	private static boolean isSuccess;

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		board = new char[r][c];
		Queue<Point> jihunQ = new ArrayDeque<>();
		Queue<Point> fireQ = new ArrayDeque<>();
		
		for(int i = 0; i < r; i++) {
			String input = br.readLine();
			for(int j = 0; j < c; j++) {
				board[i][j] = input.charAt(j);
				
				if(board[i][j] == JIHUN) {
					board[i][j] = '.';
					jihunQ.add(new Point(i, j));
				}
				if(board[i][j] == FIRE) {
					fireQ.add(new Point(i, j));
				}
			}
		}
		
		boolean[][] visited = new boolean[r][c];
		isSuccess = true;
		jihunCnt = 1;
		int time = 0;
		boolean isFail = true;
		while(isFail) {
			time++;
			moveFire(fireQ);
			isFail = moveJiHun(jihunQ, visited);
			
			if(!isFail) {
				break;
			}
			
			if(!isSuccess) {
				System.out.println("IMPOSSIBLE");
				return;
			}
			
		}
		
		System.out.println(time);
	}
	
	private static boolean moveJiHun(Queue<Point> queue, boolean[][] visited) {

		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		boolean flag = false;

		if(!queue.isEmpty()) {
			int size = queue.size();
			for(int s = 0; s < size; s++) {
				Point poll = queue.poll();
				visited[poll.x][poll.y] = true;
				
				for(int i = 0; i < 4; i++) {
					int nx = poll.x + dx[i];
					int ny = poll.y + dy[i];
					
					if(nx < 0 || ny < 0 || nx >= r || ny >= c) {
						return false;	//탈출 성공
					}
					
					if(board[nx][ny] == ROAD && !visited[nx][ny]) {
						flag = true;
						visited[nx][ny] = true;
						board[nx][ny] = ROAD;
						queue.add(new Point(nx, ny));
					}
				}
			}
		}
		
		//탈출 실패
		if(!flag && queue.isEmpty()) {
			isSuccess = false;
		}

		return true;
	}
	
	private static void moveFire(Queue<Point> queue) {
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		
		if(!queue.isEmpty()) {
			int size = queue.size();
			for(int s = 0; s < size; s++) {
				Point poll = queue.poll();
				
				for(int i = 0; i < 4; i++) {
					int nx = poll.x + dx[i];
					int ny = poll.y + dy[i];
					
					if(nx < 0 || ny < 0 || nx >= r || ny >= c) {
						continue;
					}
					
					if(board[nx][ny]==ROAD) {
						board[nx][ny] = FIRE;
						queue.add(new Point(nx, ny));
					}
				}
			}
		}
	}
}
