import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Pair {
	public int first;
	public int second;
	
	public Pair() {
		
	}
	public Pair(int first, int second) {
		this.first = first;
		this.second = second;
	}
}

public class BOJ_2573 {
	
	static int n, m;
	static int checkCnt = 0;
	static int[][] arr;
	static int[][] connected;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	
	//범위 판별
	static boolean isRange(int y, int x) {
		if (x >= 0 && y >= 0 && x < m && y < n) {
			return true;
		}
		return false;
	}
	
	//connected 초기화
	static void init() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				connected[i][j] = 0;
			}
		}
	}
	
	static int cntConnected() {
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] != 0 && connected[i][j] == 0) {//섬 존재, 방문 x면
					cnt++; //연결 요소++
					findConnected(i, j, cnt);
				}
			}
		}
		return cnt;
	}
	
	static void findConnected(int sy, int sx, int cnt) {
		Queue<Pair> que = new LinkedList<>();
		connected[sy][sx] = cnt;
		que.add(new Pair(sy, sx));
		while (!que.isEmpty()) {
			Pair tmp = que.poll();
			int y = tmp.first;
			int x = tmp.second;
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (isRange(ny, nx) && arr[ny][nx] != 0 && connected[ny][nx] == 0) {
					connected[ny][nx] = cnt;
					que.add(new Pair(ny, nx));
				}
			}
			
		}
	}
	
	static void reduceIce() {
		int[][] tmp = new int[n][m];//배열 초기화
		for (int i = 0 ; i  < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] != 0) {
					int check = 0;
					for (int k = 0; k < 4; k++) {
						int ny = i + dy[k];
						int nx = j + dx[k];
						if (isRange(ny, nx) && arr[ny][nx] == 0) {
							check++;
						}
					}
					tmp[i][j] = check;
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] < tmp[i][j]) {
					arr[i][j] = 0;
				}
				else {
					arr[i][j] -= tmp[i][j];
					
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		connected = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//////입력 끝/////////
		//1. inital 연결 요소 갯수 
		checkCnt = cntConnected();
		//2. 빙하 줄이고 연결 요소 갯수 찾기(연결요소 0이면 0 출력 후 리턴)
		int cntYear = 0;
		while (true) {
			reduceIce();
			init();
			cntYear++;
			int tmpCnt = cntConnected();
			if (tmpCnt == 0) {
				System.out.println(0);
				return;
			}
			if (tmpCnt != checkCnt) {
				System.out.println(cntYear);
				return;
			}
			
		}
		
	}

}
