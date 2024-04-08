import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static class Block{
		int x, y;
		
		public Block(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static class Group extends Block implements Comparable<Group>{
		int rainbow;
		int cnt;
		
		public Group(int x, int y, int rainbow, int cnt) {
			super(x, y);
			this.rainbow = rainbow;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Group o2) {
			if(o2.cnt == cnt) {
				if(o2.rainbow == rainbow) {
					if(o2.x == x) {
						return o2.y - y;
					}
					return o2.x - x;
				}
				return o2.rainbow - rainbow;
			}
			return o2.cnt - cnt;
		}
	}
	
	private static int n, m;
	private static int[][] board;
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, 1, -1};
	private static final int DELETED = -2;
		
	public static void main(String[] args) throws IOException{
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st;

		 st = new StringTokenizer(br.readLine());
		 n = Integer.parseInt(st.nextToken());
		 m = Integer.parseInt(st.nextToken());
		 
		 board = new int[n][n];
		 for(int i = 0; i < n; i++) {
			 st = new StringTokenizer(br.readLine());
			 for(int j = 0; j < n; j++) {
				 board[i][j] = Integer.parseInt(st.nextToken());
			 }
		 }
		 
		 int answer = 0;
		 Group find = findGroup();
		 while(find != null) {
			 //블록 제거 및 점수 획득
			 answer += deleteAndScore(find);
			 
			 //중력 작용
			 applyGravity();
			 // 회전
			 rotate();
			 //중력 작용
			 applyGravity();
			 
			 find = findGroup();
		 }
		 System.out.println(answer);
	}
	
	private static Group findGroup() {
		PriorityQueue<Group> pq = new PriorityQueue<>();	// 기준 블록 및 크기, 무지개 블록 수 	
		int[][] visited = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(board[i][j] > 0 && visited[i][j] != board[i][j]) {
					Group group = setGroup(new Block(i, j), board[i][j], visited);
					if(group != null) {
						pq.add(group);
					}
				}
			}
		}
		
		if(pq.isEmpty()) {
			return null;
		}
		return pq.poll();
	}
	
	private static Group setGroup(Block block ,int num, int[][] visited) {
		Queue<Block> queue = new ArrayDeque<>();
		queue.add(block);
		visited[block.x][block.y] = num;
		
		int totalCnt = 1;
		int rainbow = 0;
		while(!queue.isEmpty()) {
			Block poll = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = poll.x + dx[i];
				int ny = poll.y + dy[i];
				
				if(isIn(nx, ny) && (board[nx][ny] == 0 || board[nx][ny] == num) && visited[nx][ny] != num) {
					visited[nx][ny] = num;
					queue.add(new Block(nx, ny));
					totalCnt++;
					if(board[nx][ny] == 0) {
						rainbow++;
					}
				}
			}
		}
		
		if(totalCnt >= 2) {
			return new Group(block.x, block.y, rainbow, totalCnt);
		} 
		return null;
	}
	
	private static int deleteAndScore(Group group) {
		Queue<Block> queue = new ArrayDeque<>();
		queue.add(new Block(group.x, group.y));
		int num = board[group.x][group.y];
		board[group.x][group.y] = DELETED;
		
		while(!queue.isEmpty()) {
			Block poll = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = poll.x + dx[i];
				int ny = poll.y + dy[i];
				
				if(isIn(nx, ny) && (board[nx][ny] == 0 || board[nx][ny] == num)) {
					board[nx][ny] = DELETED;
					queue.add(new Block(nx, ny));
				}
			}
		}
		
		return group.cnt * group.cnt;
	}
	
	private static void applyGravity() {
		for(int i = 0; i < n; i++) {	//열
			for(int j = n - 1; j >= 0; j--) {	//행
				int idx = j;
				while(idx < n && idx - 1 >= 0 && board[idx - 1][i] >= 0 && board[idx][i] == DELETED) {
					board[idx][i] = board[idx - 1][i];
					board[idx - 1][i] = DELETED;
					idx++;
				}
			}
		}
	}
	
	private static void rotate() {
		int[][] newBoard = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				newBoard[n - j - 1][i] = board[i][j];
			}
		}
		board = newBoard;
	}
	
	private static void testPrint() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	private static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < n && ny < n;
	}
}
