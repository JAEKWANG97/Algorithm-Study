import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	private static class Dragon{
		int x, y, d, g;
		List<Integer> direction = new ArrayList<>();
		
		public Dragon(int x, int y, int d, int g) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.g = g;
			setDirection();
		}
		
		private void setDirection() {
			direction.add(d);	//0 세대 저장 방향
			for(int i = 1; i <= g; i++) {
				int size = direction.size();
				
				for (int j = size - 1; j >= 0; j--) {
					int num = direction.get(j);
					direction.add((num + 1) % 4);
				}
			}
		}
		
		private void move(int[][] board) {
			board[x][y] = 1;
			int nx = x, ny = y;
			
			for(int i = 0; i < direction.size(); i++) {
				int nextDir = direction.get(i);
				nx += dxy[nextDir][0];
				ny += dxy[nextDir][1];
				
				board[nx][ny] = 1;
			}
		}
	}
	
	private static int[][] dxy = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
		
	public static void main(String[] args) throws IOException{
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st;
		 
		 int n = Integer.parseInt(br.readLine());
		 int[][] board = new int[102][102];
		 
		 for(int i = 0; i < n; i++) {
			 st = new StringTokenizer(br.readLine());
			 int y = Integer.parseInt(st.nextToken());
			 int x = Integer.parseInt(st.nextToken());
			 int d = Integer.parseInt(st.nextToken());
			 int g = Integer.parseInt(st.nextToken());
			 Dragon dragon = new Dragon(x, y, d, g);
			 dragon.move(board);
		 }
		 
		 int answer = cntSquare(board);
		 System.out.println(answer);
	}
	
	private static int cntSquare(int[][] board) {
		int result = 0;
		
		for(int i = 0; i < 101; i++) {
			for(int j = 0; j < 101; j++) {
				int cnt = 0;
				if(board[i][j] == 1) {
					for(int k = i; k <= i + 1; k++) {
						for(int z = j; z <= j + 1; z++) {
							if(board[k][z] == 1) {
								cnt++;
							}
						}
					}
					if(cnt == 4) {
						result++;
					}
				}
			}
			
		}
		return result;
	}
}

