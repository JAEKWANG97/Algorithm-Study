import java.io.*;
import java.util.*;

public class BOJ_20057 {
	
	static class Sand {
		int y, x, weight;

		public Sand(int y, int x, int weight) {
			this.y = y;
			this.x = x;
			this.weight = weight;
		}
	}
	static Queue<Sand> que = new ArrayDeque<>();
	static int arr[][], n, ty, tx, sol; //토네이도 좌표
 	static int cnt = 0; //초기cnt
 	static int allCnt = 0;
 	static int length = 1; //갈 수 있는 길이
 	static int lCnt = 0; //길이 카운트
 	static int dir = 0; //초기 방향
 	static int dx[] = {-1, 0, 1, 0};//왼, 아, 오, 위
 	static int dy[] = {0, 1, 0, -1};
 	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		ty = n / 2;
		tx = n / 2;
		
		arr = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while (++allCnt < n * n) {
			
			lCnt++;
			
			//토네이도 이동
			ty += dy[dir];
			tx += dx[dir];
//			System.out.println(ty + " " + tx);
			//모래 이동
			
			moveSand();

			
			if (lCnt == length) {
				if (cnt == 1) {
					cnt = 0;
					lCnt = 0;
					length++;
					dir = (dir + 1) % 4;
				} else {
					cnt = 1;
					lCnt = 0;
					dir = (dir + 1) % 4;
				}
			}
		}
		System.out.println(sol);
	}
	
	static boolean isRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < n && x < n) return true;
		else return false;
	}
	
	static void moveSand() {
		int movedSandSum = 0;
		
		
		//오른쪽 7
		int ry = ty + dy[(dir + 3) % 4];
		int rx = tx + dx[(dir + 3) % 4];
		
		int movedSand = (arr[ty][tx] * 7) / 100;
		movedSandSum += movedSand;
		if (isRange(ry, rx)) {//범위 안
			que.offer(new Sand(ry, rx, movedSand));
		} else {
			sol += movedSand;// 범위 밖
		}
		
		//10
		int rty = ry + dy[(dir) % 4];
		int rtx = rx + dx[(dir) % 4];		
		movedSand = (arr[ty][tx] * 10) / 100;
		movedSandSum += movedSand;		
		if (isRange(rty, rtx)) {
			que.offer(new Sand(rty, rtx, movedSand));
		} else {
			sol += movedSand;
		}
		
		//2
		rty = ry + dy[(dir + 3) % 4];
		rtx = rx + dx[(dir + 3) % 4];
		movedSand = (arr[ty][tx] * 2) / 100;
		movedSandSum += movedSand;		
		if (isRange(rty, rtx)) {
			que.offer(new Sand(rty, rtx, movedSand));
		} else {
			sol += movedSand;
		}
		
		
		//1
		rty = ry + dy[(dir + 2) % 4];
		rtx = rx + dx[(dir + 2) % 4];
		movedSand = (arr[ty][tx] * 1) / 100;
		movedSandSum += movedSand;		
		if (isRange(rty, rtx)) {
			que.offer(new Sand(rty, rtx, movedSand));
		} else {
			sol += movedSand;
		}
		
		
		//왼쪽 7
		int ly = ty + dy[(dir + 1) % 4];
		int lx = tx + dx[(dir + 1) % 4];		
		
		movedSand = (arr[ty][tx] * 7) / 100;
		movedSandSum += movedSand;
		if (isRange(ly, lx)) {//범위 안
			que.offer(new Sand(ly, lx, movedSand));
		} else {
			sol += movedSand;// 범위 밖
		}
		
		//10
		int lty = ly + dy[(dir) % 4];
		int ltx = lx + dx[(dir) % 4];		
		movedSand = (arr[ty][tx] * 10) / 100;
		movedSandSum += movedSand;		
		if (isRange(lty, ltx)) {
			que.offer(new Sand(lty, ltx, movedSand));
		} else {
			sol += movedSand;
		}
		
		//2
		lty = ly + dy[(dir + 1) % 4];
		ltx = lx + dx[(dir + 1) % 4];
		movedSand = (arr[ty][tx] * 2) / 100;
		movedSandSum += movedSand;		
		if (isRange(lty, ltx)) {
			que.offer(new Sand(lty, ltx, movedSand));
		} else {
			sol += movedSand;
		}
		
		
		//1
		lty = ly + dy[(dir + 2) % 4];
		ltx = lx + dx[(dir + 2) % 4];
		movedSand = (arr[ty][tx] * 1) / 100;
		movedSandSum += movedSand;		
		if (isRange(lty, ltx)) {
			que.offer(new Sand(lty, ltx, movedSand));
		} else {
			sol += movedSand;
		}
		
		int fronty = ty + dy[dir] * 2;
		int frontx = tx + dx[dir] * 2;
		movedSand = (arr[ty][tx] * 5) / 100;
		movedSandSum += movedSand;
		if (isRange(fronty, frontx)) {
			que.offer(new Sand(fronty, frontx, movedSand));
		} else {
			sol += movedSand;
		}
		
		int realFront = arr[ty][tx] - movedSandSum;
		
		if (isRange(ty + dy[dir], tx + dx[dir])) {
			arr[ty + dy[dir]][tx + dx[dir]] += realFront;
		} else {
			sol += realFront;
		}
		arr[ty][tx] = 0;
		
		while (!que.isEmpty()) {
			Sand cur = que.poll();
			arr[cur.y][cur.x] += cur.weight;
		}
	}
	
	
	
}
