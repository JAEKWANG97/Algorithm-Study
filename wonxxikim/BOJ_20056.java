package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static int[][] delta = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
	static class fire{
		int x;
		int y;
		int m;
		int s;
		int d;
		boolean check;
		public fire(int x, int y, int m, int s, int d) {
			this.x = x;
			this.y = y;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Queue<fire> q = new LinkedList<>();
		for(int i = 0 ; i<M ; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken()); //질량
			int s = Integer.parseInt(st.nextToken()); //속력
			int d = Integer.parseInt(st.nextToken()); //방향
			q.add(new fire(x,y,m,s,d));
		}

		for(int num = 0 ; num<K ; num++) {
			ArrayList<fire>[][] map = new ArrayList[N][N];
			for(int i = 0 ; i<N ; i++) {
				for(int j = 0 ; j<N ; j++) {
					map[i][j] = new ArrayList<fire>();
				}
			}
			while(!q.isEmpty()) {
				fire cur = q.poll();
				cur.x = cur.x+delta[cur.d][0]*cur.s; 
				if(cur.x<0) cur.x = N-Math.abs(cur.x)%N;
				if(cur.x>=N) cur.x = cur.x%N;
				cur.y = cur.y+delta[cur.d][1]*cur.s;
				if(cur.y<0) cur.y = N-Math.abs(cur.y)%N;
				if(cur.y>=N) cur.y = cur.y%N;
				map[cur.x][cur.y].add(cur);
			}
			for(int r = 0 ; r<N ; r++) {
				for(int c = 0 ; c<N ;c++) {
					if(map[r][c].size()==0) continue;
					if(map[r][c].size()==1) {
						q.add(map[r][c].get(0));
					}else {
						int sum_m = 0;
						int sum_s = 0;
						int check = 0;
						for(fire f : map[r][c]) {
							sum_m += f.m;
							sum_s+=f.s;
							check+=f.d%2; 
						}
						if(check==0 || check==map[r][c].size()) {
							if(sum_m/5==0)continue;
							q.add(new fire(r,c,sum_m/5,sum_s/map[r][c].size(),0));
							q.add(new fire(r,c,sum_m/5,sum_s/map[r][c].size(),2));
							q.add(new fire(r,c,sum_m/5,sum_s/map[r][c].size(),4));
							q.add(new fire(r,c,sum_m/5,sum_s/map[r][c].size(),6));
						}else {
							if(sum_m/5==0) continue;
							q.add(new fire(r,c,sum_m/5,sum_s/map[r][c].size(),1));
							q.add(new fire(r,c,sum_m/5,sum_s/map[r][c].size(),3));
							q.add(new fire(r,c,sum_m/5,sum_s/map[r][c].size(),5));
							q.add(new fire(r,c,sum_m/5,sum_s/map[r][c].size(),7));
						}
					}
				}
			}
			
		}
		int result = 0;
		while(!q.isEmpty()) {
			fire cur = q.poll();
			result+=cur.m;
		}
		System.out.println(result);
	}

}
