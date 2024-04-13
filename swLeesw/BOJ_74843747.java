import java.io.*;
import java.util.*;

public class Main {
	
	static class Info {
		int y, x, depth;
		public Info(int y, int x, int depth) {
			this.y = y;
			this.x = x;
			this.depth = depth;
		}
	}
	
	static int r, c, arr[][], jy, jx;
	static boolean jVisited[][], fVisited[][];
	static int dx[] = {1, -1, 0, 0};
	static int dy[] = {0, 0, 1, -1};
	//queue생성
	static Queue<Info> jQue = new ArrayDeque<>();
	static Queue<Info> fQue = new ArrayDeque<>();
	
	
	static boolean isRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < r && x < c) return true;
		else return false;
	}
	
	static void fireJihoon() { //불 피우기
		int curDepth;
		if (!fQue.isEmpty()) {
			curDepth = fQue.peek().depth;			
		} else {
			return;
		}
		while (!fQue.isEmpty()) {
			if (curDepth != fQue.peek().depth) break;//한 단계만 불 지르기
			Info cur = fQue.poll();
			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				int nDepth = cur.depth + 1;
				if (isRange(ny, nx) && !fVisited[ny][nx] && arr[ny][nx] == 0) {
					//범위 안, 방문한한 불, 벽x
					fVisited[ny][nx] = true;
					fQue.offer(new Info(ny, nx, nDepth));
				}
			}
		}
	}
	
	static boolean checkJihoonCanLive(int y, int x) {
		if (y == r - 1 || x == c - 1 || x == 0 || y == 0) return true;
		else return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		//벽 #:1
		//빈 곳.: 0
		//지훈이J 
		//불이 난 곳F
		arr = new int[r][c];
		jVisited = new boolean[r][c];
		fVisited = new boolean[r][c];
		
		for (int i = 0; i < r; i++) {
			String str = br.readLine();
			for (int j = 0; j < c; j++) {
				char c = str.charAt(j);
				if (c == '#') {
					arr[i][j] = 1;
				} else if (c == 'J') {
					jVisited[i][j] = true;
					jQue.offer(new Info(i, j, 0));
				} else if (c == 'F') {
					fVisited[i][j] = true;
					fQue.offer(new Info(i, j, 0));
				}
			}
		}
		//init end
		
		int curDepth = jQue.peek().depth;
		fireJihoon(); //시작 전 태우고 시작
		while (!jQue.isEmpty()) {
			Info cur = jQue.poll();
			if (curDepth != cur.depth) {
				fireJihoon(); //한번 태우기
				curDepth = cur.depth;
			}
			
			if (checkJihoonCanLive(cur.y, cur.x)) {
				System.out.println(cur.depth + 1);
				return;
			}
			
			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				int nDepth = cur.depth + 1;
				
				if (isRange(ny, nx) && !jVisited[ny][nx]
						&& arr[ny][nx] == 0 && !fVisited[ny][nx]) {
					//범위 안, 지훈이 방문 체크, 벽 체크, 불 방문 체크
					jVisited[ny][nx] = true;
					jQue.offer(new Info(ny, nx, nDepth));
				}
			}
			
		}
		
		System.out.println("IMPOSSIBLE");
	}
	
}
