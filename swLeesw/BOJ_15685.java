import java.io.*;
import java.util.*;

public class BOJ_15685 {
	
	static class Dragon {
		int y, x;
		List<Integer> dir;
		
		public Dragon(int y, int x, int initDir) {
			this.y = y;
			this.x = x;
			dir = new ArrayList<>();
			dir.add(initDir);
		}
		
		//드레곤이동
		public void gguckGgi() {
			int ny = this.y;
			int nx = this.x;
			
			for (int i = 0; i < dir.size(); i++) {
				int curDir = dir.get(i);
				ny += dy[curDir];
				nx += dx[curDir];
				arr[ny][nx] = true;
			}
		}
		//드레곤 세대교체
		public void nextGeneration() {
			int size = dir.size();
			
			for (int i = size - 1; i >= 0; i--) {
				int nextDir = (dir.get(i) + 1) % 4;
				dir.add(nextDir);
			}
		}
	}
	
	static boolean sCheck(int y, int x) {
		int cnt = 0;
		
		for (int i = y; i < y + 2; i++) {
			for (int j = x; j < x + 2; j++) {
				if (arr[i][j]) cnt++;
			}
		}
		
		if (cnt == 4) {
			return true;
		} else {
			return false;
		}
		
	}
	
	static int n;
	static boolean arr[][];
	static int dy[] = {0, -1, 0, 1}; //오 위 왼 아
	static int dx[] = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		arr = new boolean[101][101];
		
		for (int i = 0; i < n; i++) {
			//드레곤 생성
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			arr[y][x] = true;
			Dragon dragon = new Dragon(y, x, dir);
			
			//드래곤 꺾기
			
			for (int j = 0; j < g; j++) {
				dragon.nextGeneration();
			}
			
			dragon.gguckGgi();
		}
		
		int sol = 0;
		for (int i = 0; i < 101 - 1; i++) {
			for (int j = 0; j < 101 - 1; j++) {
				if (sCheck(i, j)) sol++;
			}
		}
		System.out.println(sol);
	}
	
}
