import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static class Point{
		int x, y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static final char WATER = '*';
	private static final char END = 'D';
	private static final char EMPTY = '.';
	private static final char ROCK = 'X';
	private static final char START = 'S';
	
	private static int[] dx = {0, 1, 0, -1};
	private static int[] dy = {1, 0, -1, 0};
	private static int r, c;
 	private static char[][] board;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		board = new char[r][c];
		
		Queue<Point> water = new ArrayDeque<>();
		Queue<Point> start = new ArrayDeque<>();
		for(int i = 0; i < r; i++) {
			String input = br.readLine();
			for(int j = 0; j < c; j++) {
				board[i][j] = input.charAt(j);
				if(board[i][j] == START) {
					start.add(new Point(i, j));
				}
				if(board[i][j] == WATER) {
					water.add(new Point(i, j));
				}
			}
		}
		
		int time = 0;
		boolean success = false;
		while(!success) {
			if(start.isEmpty()) {
				break;
			}
			moveWater(water);
			success = moveStart(start);
			time++;
//			printTest();
		}
		
		if(success) {
			System.out.println(time);
		} else {
			System.out.println("KAKTUS");
		}
	}
	
	private static void moveWater(Queue<Point> water) {
		int size = water.size();
		for(int s = 0; s < size; s++) {
			Point poll = water.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = poll.x + dx[i];
				int ny = poll.y + dy[i];
				
				if(!isIn(nx, ny) || board[nx][ny] == ROCK || board[nx][ny] == END || board[nx][ny] == WATER) {
					continue;
				}
				water.add(new Point(nx, ny));
				board[nx][ny] = WATER;
			}
		}
	}
	
	private static boolean moveStart(Queue<Point> start) {
		int size = start.size();
		for(int s = 0; s < size; s++) {
			Point poll = start.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = poll.x + dx[i];
				int ny = poll.y + dy[i];
				
				if(!isIn(nx, ny) || board[nx][ny] == ROCK || board[nx][ny] == WATER || board[nx][ny] == START) {
					continue;
				}
				
				if(board[nx][ny] == END) {
					return true;
				}
				start.add(new Point(nx, ny));
				board[nx][ny] = START;
			}
		}
		return false;
	}
	
	private static void printTest() {
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
		
		System.out.println("==========================");
	}
	
	private static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < r && ny < c; 
	}
}
