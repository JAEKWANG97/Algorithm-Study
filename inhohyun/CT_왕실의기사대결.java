import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class CT_왕실의기사대결 {
	static class Knight {
		int r;
		int c;
		int h;
		int w;
		int health;
		boolean isDead;
		int damage;

		public Knight() {}
		public Knight(int r, int c, int h, int w, int health) {
			this.r = r;
			this.c = c;
			this.h = h;
			this.w = w;
			this.health = health;
		}

		
	}
	static int[][] MAP;
	static final int[] dr = {-1,0,1,0};
	static final int[] dc = {0,1,0,-1};
	static int L;
	static List<Knight> knights;
	static boolean[] affected;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		MAP = new int[L+1][L+1];
		
		for(int i=1; i<=L; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=L; ++j) {
				MAP[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		knights = new ArrayList<>();
		knights.add(new Knight()); 
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			knights.add(new Knight(r, c, h, w, k));
		}
		
		while(Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int knightNum = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			
			if(!checkWall(knightNum, dir)) {
				move(knightNum, dir);
			}
		}
		
		int ans = 0;
		for(int i=1; i<=N; ++i) {
			if(!knights.get(i).isDead) {
				ans += knights.get(i).damage;
			}
		}
		
		System.out.println(ans);
	}
	private static void move(int knightNum, int dir) {
		for(int n=1; n<affected.length; ++n) { 
			if(!affected[n] || n == knightNum) continue;
			Knight k = knights.get(n);
			
			for(int i=k.r; i<k.r+k.h; ++i) {
				for(int j=k.c; j<k.c+k.w; ++j) {
					int nr = i + dr[dir];
					int nc = j + dc[dir];

					if(MAP[nr][nc] == 1)
						++k.damage;
				}
			}
		}
		
		for(int i=1; i<knights.size(); ++i) { 
			if(affected[i]) {
				Knight k = knights.get(i);
				k.r += dr[dir];
				k.c += dc[dir];
				
				if(i == knightNum) continue;
				
				if(k.health <= k.damage) {
					k.isDead = true;
				}
			}
		}
	}
	private static boolean checkWall(int knightNum, int dir) {
		if(knights.get(knightNum).isDead) return true;
		
		int[][] knightMap = new int[L+1][L+1];
		
		for(int n=1; n<knights.size(); ++n) { 
			Knight k = knights.get(n);
			
			if(k.isDead) continue;
			
			for(int i=k.r; i<k.r+k.h; ++i) {
				for(int j=k.c; j<k.c+k.w; ++j) {
					knightMap[i][j] = n;
				}
			}
		}
		
		Knight k = knights.get(knightNum);
		Queue<int[]> q = new LinkedList<>();
		affected = new boolean[knights.size()];
		boolean[][] v = new boolean[L+1][L+1];
		
		for(int i=k.r; i<k.r+k.h; ++i) { 
			for(int j=k.c; j<k.c+k.w; ++j) {
				q.offer(new int[] {i, j});
				v[i][j] = true;
			}
		}
		affected[knightNum] = true;
		
		while(!q.isEmpty()) {
			int[] poll = q.poll();
				
			int nr = poll[0] + dr[dir];
			int nc = poll[1] + dc[dir];
			
	
			if(nr<1 || nr>L || nc<1 || nc>L || MAP[nr][nc] == 2) return true;
			else if(knightMap[nr][nc] != knightNum && !v[nr][nc]) { 
				k = knights.get(knightMap[nr][nc]);
				
				for(int i=k.r; i<k.r+k.h; ++i) {
					for(int j=k.c; j<k.c+k.w; ++j) {
						q.offer(new int[] {i, j});
						affected[knightMap[nr][nc]] = true;
						v[i][j] = true;
					}
				}
			}
		}
		
		return false;
	}
}
