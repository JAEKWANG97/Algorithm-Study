import java.util.*;
import java.io.*;

public class Main {
	
	static int n, m, arr[][], per[], map[][];
	static int sol = 210000000;
	static ArrayList<int[]> cctv = new ArrayList<>();
	static int[] dx = {0, 1, 0, -1};//위, 오, 아, 왼
	static int[] dy = {-1, 0, 1, 0};
	//방향 전환 과정 Cctv
	//1번 : 1방향 0(0), 1(1), 2(2), 3(3)
	//2번 : 2방향(180도) 0(0, 2), 1(1, 3)
	//3번 : 2방향(90도) 0(0, 1), 1(1, 2), 2(2, 3), 3(3, 0) 
	//4번 : 3방향 0(0, 1, 2), 1(1, 2 ,3), 2(2, 3, 0), 3(3, 0, 1)
	//5번 : 4방향 0(0, 1, 2, 3)
	
	//0 : 빈칸, 1 ~ 5 : cctv, 6 : 벽 , cctv 범위는 7로 표현하자
	
	static boolean isRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < n && x < m) return true;
		return false;
	}
	
	static void beam(int y, int x, int dir, int color) {//쏘좌
		int ny = y + dy[dir];
		int nx = x + dx[dir];
		
		while (true) {
			if (!isRange(ny ,nx)) break; //빔 멈춤
			if (map[ny][nx] == 6) break; //벽이면 멈춤
			if (map[ny][nx] == 0) {
				map[ny][nx] = color;				
			}
			ny += dy[dir];
			nx += dx[dir];
		}
		
	}
	
	static void solve() {//경우의 수가 되면?//per[i]는 방향, cctv[i]는 cctv 좌표
		int sum = 0;
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			map[i] = arr[i].clone();
		}
		for (int i = 0; i < cctv.size(); i++) {//빔 쏘기
			int curY = cctv.get(i)[0];
			int curX = cctv.get(i)[1];
			if (arr[curY][curX] == 1) {
				beam(curY, curX, per[i], 7);//한방향 쏘기~
			} else if (map[curY][curX] == 2) {
				beam(curY, curX, per[i], 7); //두방향(180도)
				beam(curY, curX, (per[i] + 2) % 4, 7);
			} else if (map[curY][curX] == 3) {
				beam(curY, curX, per[i], 7); //2방향(90도)
				beam(curY, curX, (per[i] + 1) % 4, 7);
			} else if (map[curY][curX] == 4) {
				beam(curY, curX, per[i], 7); //3방향
				beam(curY, curX, (per[i] + 1) % 4, 7);				
				beam(curY, curX, (per[i] + 2) % 4, 7);				
			} else if (map[curY][curX] == 5) {
				beam(curY, curX, 0, 7); //4방향
				beam(curY, curX, 1, 7); //4방향
				beam(curY, curX, 2, 7); //4방향				
				beam(curY, curX, 3, 7); //4방향		
			} 
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0) {
					sum++;
				}
			}
		}
		
		sol = Math.min(sol, sum);
	}
	
	static void dupPer(int cnt) {
		if (cnt == cctv.size()) {
			//가능한 경우의 수만
			for (int i = 0; i < cctv.size(); i++) {
				int curCctv = arr[cctv.get(i)[0]][cctv.get(i)[1]];
				
				if (curCctv == 2) {
					if (per[i] > 1) {
						return;
					}
				} else if (curCctv == 5) {
					if (per[i] != 0) {
						return;
					}
				}
			}
			solve();
			return;
		}
		for (int i = 0; i < 4; i++) {
			per[cnt] = i;
			dupPer(cnt + 1);
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] >= 1 && arr[i][j] <= 5) {
					cctv.add(new int[] {i, j});
				}
			}
		}
		per = new int[cctv.size()];
		dupPer(0);
		System.out.println(sol);
	}
}
