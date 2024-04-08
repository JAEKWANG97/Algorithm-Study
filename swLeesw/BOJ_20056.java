import java.util.*;
import java.io.*;

public class Main {
	
	static class FireBall {
		int m, s, d;//질량, 속도, 방향
		
		public FireBall() {
		}
		
		public FireBall(int m, int s, int d) {
			super();
			this.m = m;
			this.s = s;
			this.d = d;
		}
		
	}
	
	static class NextFire extends FireBall {
		int y, x;
		
		public NextFire() {
			super();
		}
		public NextFire(int y, int x, int m, int s, int d) {
			super(m, s, d);
			this.y = y;
			this.x = x;
		}
		
	}
	
	static int n, m, k;
	static Queue<FireBall> arr[][];
	static int dx[] = {0, 1, 1, 1, 0, -1, -1, -1};
	static int dy[] = {-1, -1, 0, 1, 1, 1, 0, -1};
	
	static int oddEven(int num) {
		if (num % 2 == 0) {
			return 2;
		} else {
			return 1;
		}
	}
	
	static boolean isRange(int ty, int tx) {
		if (ty >= 0 && tx >= 0 && ty < n && tx < n) return true;
		else return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		arr = new LinkedList[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = new LinkedList<>();
			}
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int ty = Integer.parseInt(st.nextToken()) - 1;
			int tx = Integer.parseInt(st.nextToken()) - 1;
			int tm = Integer.parseInt(st.nextToken());
			int ts = Integer.parseInt(st.nextToken());
			int td = Integer.parseInt(st.nextToken());
			arr[ty][tx].offer(new FireBall(tm, ts, td));
		}
		//initEnd
		
		while (k > 0) {
			//모든 불 이동
			Queue<NextFire> que = new LinkedList<>();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					while (!arr[i][j].isEmpty()) {
						FireBall curBall = arr[i][j].poll();
						int ny = i + dy[curBall.d] * curBall.s;
						int nx = j + dx[curBall.d] * curBall.s;
						
						if(ny < 0) ny = n - Math.abs(ny) % n;
						if(ny >= n) ny = ny % n;
						if(nx < 0) nx = n - Math.abs(nx) % n;
						if(nx >= n) nx = nx % n;
//						if (isRange(ny, nx)) { //이동 가능하면 que에 넣기
							que.offer(new NextFire(ny, nx, curBall.m, curBall.s, curBall.d));
//						} 
						
					}
					
				}
			}
			
			while (!que.isEmpty()) {
				NextFire cur = que.poll();
				arr[cur.y][cur.x].offer(new FireBall(cur.m, cur.s, cur.d));
			}
			
			//이동 후 충돌판정
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					int size = arr[i][j].size();
					if (size > 1) { //1개 이상이면
						int mSum = 0;//질량 합
						int sSum = 0; //속도 합
						int oe = oddEven(arr[i][j].peek().d);//첫 방향
						boolean flag = false;//false: 0246, true: 1357
						while (!arr[i][j].isEmpty()) {
							FireBall cur = arr[i][j].poll();
							mSum += cur.m;
							sSum += cur.s;
							if (oe != oddEven(cur.d)) {//방향 다르면 true
								flag = true;
							}
						}
						
						mSum /= 5;
						sSum /= size;
						if (mSum == 0) continue; //질량 0이면 소멸
						if (flag) { //1357
							for (int k = 1; k < 8; k += 2) {
								arr[i][j].offer(new FireBall(mSum, sSum, k));
							}
						} else { //0246
							for (int k = 0; k < 8; k += 2) {
								arr[i][j].offer(new FireBall(mSum, sSum, k));								
							}
						}
					}
				}
			}
			
			k--;
		}
		//답
		int sol = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				while (!arr[i][j].isEmpty()) {
//					System.out.println(arr[i][j].peek().m + " " + arr[i][j].peek().s + " " + arr[i][j].peek().d);
					sol += arr[i][j].poll().m;
					
				}
			}
		}
		System.out.println(sol);
	}
	
}
