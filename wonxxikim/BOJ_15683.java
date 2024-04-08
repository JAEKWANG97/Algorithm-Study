import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, room[][], cctv_size, cctv_direc[],map[][], result;
	static int delta[][] = {{0,1},{1,0},{0,-1},{-1,0}}; //오아왼위
	static cctv[] allcctv = new cctv[8];
	static class cctv{
		int type;
		int x;
		int y;
		public cctv(int type, int x, int y) {
			this.type = type;
			this.x = x;
			this.y = y;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		room = new int[N][M];
		cctv_size = 0;
		for(int i = 0 ; i<N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j<M ;j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				if(room[i][j] != 0 && room[i][j]!=6) {
					allcctv[cctv_size++] = new cctv(room[i][j],i,j);
					
				}
			}
		}
		cctv_direc = new int[cctv_size];
		result = Integer.MAX_VALUE;
		permutation(0);
		System.out.println(result);
		

	}
	
	public static void permutation(int cnt) {
		if(cnt == cctv_size) {
			map = new int[N][M];
			int answer = 0;
			for(int i = 0 ; i<N; i++) {
				map[i] = room[i].clone();
			}
			
			for(int i = 0 ; i<cctv_size; i++) {
				check(i);
			}
			for(int i = 0 ; i<N ; i++) {
				for(int j = 0 ; j<M ; j++) {
					if(map[i][j] == 0 ) answer++;
				}
			}
			if(answer<result) result = answer;
			return;
			
			
			
		}
		for(int i = 0 ; i<4; i++) {
			cctv_direc[cnt] = i;
			permutation(cnt+1);
		}
	}
	
	public static void check(int idx) {
		if(allcctv[idx].type==5) {
			for(int i = 0 ; i<4 ; i++) {
				see(idx, i);
			}
	
		}
		if(allcctv[idx].type == 4) {
			see(idx,cctv_direc[idx]);
			see(idx,(cctv_direc[idx]+1)%4);
			see(idx,(cctv_direc[idx]+2)%4);
		}
		if(allcctv[idx].type == 3) {
			see(idx,cctv_direc[idx]);
			see(idx,(cctv_direc[idx]+1)%4);
			
		}
		if(allcctv[idx].type == 2) {
			see(idx,cctv_direc[idx]);
			see(idx,(cctv_direc[idx]+2)%4);
			
		}
		if(allcctv[idx].type == 1){
			see(idx, cctv_direc[idx]);
		}
	}

	public static void see (int idx, int direction) {
		int nr = allcctv[idx].x+delta[direction][0];
		int nc = allcctv[idx].y+delta[direction][1];
		while(true) {
			if(nr< 0 || nr>=N || nc<0 || nc>=M) break;
			if(map[nr][nc]==6) break;
			if(map[nr][nc] == 0) map[nr][nc] = -1;
			nr += delta[direction][0];
			nc += delta[direction][1];
		}
			
		
	}

}
