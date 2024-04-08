import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	
	private static class Point{
		int z, x, y;

		public Point(int z, int x, int y) {
			this.z = z;
			this.x = x;
			this.y = y;
		}		
	}
	
	private static int l,r,c;
	private static char[][][] board;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			l = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			board = new char[l][r][c];
			Point start = null, end = null;
			
			if(l == 0 && r ==0 && c == 0) {
				break;
			}
			
			for(int i = 0; i < l; i++) {
				for(int j = 0; j < r; j++) {
					String input = br.readLine();
					for(int k = 0; k < c; k++) {
						board[i][j][k] = input.charAt(k);
						
						if(board[i][j][k] == 'S') {
							start = new Point(i, j, k);
						}
						if(board[i][j][k] == 'E') {
							end = new Point(i, j, k);
						}
					}
				}
				br.readLine();	//빈 칸 입력
			}
			
			String answer = bfs(start, end);
			System.out.println(answer);
			
		}//while 종료
	}
	
	private static String bfs(Point start, Point end) {
		int[][] dzxy = {{0, 0, 1}, {0, 0, -1}, {0, 1, 0}, 
				{0, -1, 0}, {-1, 0, 0}, {1, 0, 0}};	//동,서,남,북,상,하
		int[][][] visited = new int[l][r][c];
		
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(start);
		visited[start.z][start.x][start.y] = 1;	//1부터 시작이므로 최종값은 -1 처리 필요
		
		while(!queue.isEmpty()) {
			Point poll = queue.poll();
			
			if(board[poll.z][poll.x][poll.y] == 'E') {
				return "Escaped in "+ (visited[poll.z][poll.x][poll.y] - 1)+" minute(s).";
			}
			
			for(int i = 0; i < 6; i++) {
				int nz = poll.z + dzxy[i][0];
				int nx = poll.x + dzxy[i][1];
				int ny = poll.y + dzxy[i][2];
				
				if(isIn(nz, nx, ny) && visited[nz][nx][ny] == 0 && board[nz][nx][ny] != '#') {
					visited[nz][nx][ny] = visited[poll.z][poll.x][poll.y] + 1;
					queue.add(new Point(nz, nx, ny));
				}
			}
		}
		
		return "Trapped!";
	}
	
	private static boolean isIn(int nz, int nx, int ny) {
		return nz >= 0 && nz < l &&
				nx >= 0 && nx < r &&
				ny >= 0 && ny < c;
	}
}
