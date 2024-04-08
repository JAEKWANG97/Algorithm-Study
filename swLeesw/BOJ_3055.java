import java.util.*;
import java.io.*;

public class BOJ_3055 {
	
	static class Pos {
		int y, x, depth;

		public Pos(int y, int x, int depth) {
			this.y = y;
			this.x = x;
			this.depth = depth;
		}
	}
	
	static int r, c;
	static char arr[][];
	static boolean visited[][];
	static int dx[] = {1, -1, 0, 0};
	static int dy[] = {0, 0, 1, -1};
	static Queue<Pos> gosmQue = new ArrayDeque<>();
	static Queue<Pos> waterQue = new ArrayDeque<>();
	/*
	 * . 빔
	 * * 물
	 * X 돌
	 * D 비버 굴
	 * S 고슴도치
	 */
	
	static boolean isRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < r && x < c) return true;
		else return false;
	}
	
	static int bfs() {
		int time = 0;
		while (true) {
			//워터큐 현재 depth까지만 돌리기
			while (!waterQue.isEmpty() && waterQue.peek().depth == time) {
				Pos waterCur = waterQue.poll();
				
				for (int i = 0; i < 4; i++) {
					int ny = waterCur.y + dy[i];
					int nx = waterCur.x + dx[i];
					//범위 밖, 돌, 물 차있는 곳이면 다음
					if (!isRange(ny, nx) || arr[ny][nx] == 'X' || arr[ny][nx] == 'D' || visited[ny][nx]) continue;
					
					waterQue.offer(new Pos(ny, nx, waterCur.depth + 1));
					visited[ny][nx] = true;
				}
				
			}
			
			//고슴큐 현재depth까지만 돌리기
			while (!gosmQue.isEmpty() && gosmQue.peek().depth == time) {
				Pos gosmCur = gosmQue.poll();
				
				if (arr[gosmCur.y][gosmCur.x] == 'D') { //굴 발견!
					return gosmCur.depth;
				}
				
				for (int i = 0; i < 4; i++) {
					int ny = gosmCur.y + dy[i];
					int nx = gosmCur.x + dx[i];
					//범위 밖, 돌, 물 차있는 곳이면 다음
					if (!isRange(ny, nx) || arr[ny][nx] == 'X' || visited[ny][nx]) continue;

					gosmQue.offer(new Pos(ny, nx, gosmCur.depth + 1));
					visited[ny][nx] = true;
				}
			}

			
			if (waterQue.isEmpty() && gosmQue.isEmpty()) break;
			
			time++;
		}
		
		return -1;
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		arr = new char[r][c];
		visited = new boolean[r][c]; //0 비버 1 물
		for (int i = 0; i < r; i++) {
			String str = br.readLine();
			for (int j = 0; j < c; j++) {
				arr[i][j] = str.charAt(j);
				if (arr[i][j] == 'S') {
					gosmQue.offer(new Pos(i, j, 0));
					visited[i][j] = true;
				} else if (arr[i][j] == '*') {
					waterQue.offer(new Pos(i, j, 0));
					visited[i][j] = true;
				}
			}
		}
		
		int sol = bfs();
		if (sol != -1) {
			System.out.println(sol);
		} else {
			System.out.println("KAKTUS");
		}
		
	}
	
}
