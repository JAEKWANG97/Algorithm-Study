import java.util.*;
import java.io.*;

public class Main {
	
	static class Pos {
		int y, x;
		
		public Pos (int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static class Dust {
		int y, x, weight;

		public Dust(int y, int x, int weight) {
			super();
			this.y = y;
			this.x = x;
			this.weight = weight;
		}
	}
	
	static int r, c, t, arr[][];
	static int dx[] = {0, 1, 0, -1};
	static int dy[] = {-1, 0, 1, 0};
	static Pos tAc, bAc;
	
	static boolean isRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < r && x < c) return true;
		else return false;
	}
	
	static void spreadDust() { //먼지 뿌리는 함수
		Queue<Dust> que = new ArrayDeque<>(); //뿌려지는 먼지들
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (arr[i][j] > 0) { //먼지 발견
					int spreadValue = arr[i][j] / 5; //뿌려지는 양
					if (spreadValue == 0) continue; //확산 안되는 양이면 확산x
					for (int k = 0; k < 4; k++) {
						int ny = i + dy[k];
						int nx = j + dx[k];
						
						if (isRange(ny, nx) && arr[ny][nx] >= 0) { //확산 가능하면 뿌리기
							arr[i][j] -= spreadValue;
							que.offer(new Dust(ny, nx, spreadValue)); //나중에 넣어야 함
						}
					}
				}
			}
		}
		
		while (!que.isEmpty()) {
			Dust cur = que.poll();
			arr[cur.y][cur.x] += cur.weight; //확산
		}
		
	}
	
	static void spreadAir() { //먼지 필터
		//tac, bac 순환 함수를 구현하자
		//tac구간 (반시계 방향)
		int sy = 0;
		int ey = tAc.y;
		int sx = 0;
		int ex = c - 1;
		
		//왼쪽
		for (int i = ey - 1; i > sy; i--) {
			arr[i][0] = arr[i - 1][0];
		}
		//위쪽
		for (int i = sx; i < ex; i++) {
			arr[0][i] = arr[0][i + 1];
		}
		//오른쪽
		for (int i = sy; i < ey; i++) {
			arr[i][ex] = arr[i + 1][ex];
		}
		//아래쪽
		for (int i = ex; i > sx + 1; i--) {
			arr[ey][i] = arr[ey][i - 1];
		}
		arr[ey][1] = 0;
		
		//bac구간 (시계 방향)
		sy = bAc.y;
		ey = r - 1;
		sx = 0;
		ex = c - 1;
		//왼쪽
		for (int i = sy + 1; i < ey; i++) {
			arr[i][0] = arr[i + 1][0];
		}
		//아랫쪽
		for (int i = sx; i < ex; i++) {
			arr[ey][i] = arr[ey][i + 1];
		}
		//오른쪽
		for (int i = ey; i > sy; i--) {
			arr[i][ex] = arr[i - 1][ex];
		}
		//윗쪽
		for (int i = ex; i > sx + 1; i--) {
			arr[sy][i] = arr[sy][i - 1];
		}
		arr[sy][1] = 0;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		
		arr = new int[r][c];
		
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				if (arr[i][j] == -1) { //에어컨 판별
					if (tAc == null) {
						tAc = new Pos(i, j);
					} else {
						bAc = new Pos(i, j);
					}
				}
			}
		}
		while (t > 0) {
			//먼지 뿌리기
			spreadDust();
			//공기 순환
			spreadAir();
			t--;
		}
		
		int solve = 0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (arr[i][j] > 0) {
					solve += arr[i][j];
				}
			}
		}
		System.out.println(solve);
	}
	
}
