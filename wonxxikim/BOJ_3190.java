import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] delta = {{0,1},{1,0},{0,-1},{-1,0}}; //우하좌상
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int map[][] = new int[N][N];
		map[0][0] = 1;
		for(int i = 0 ; i<K ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			map[x][y] = 2;
		}
		int L = Integer.parseInt(br.readLine());
		int[] snake_time = new int[L];
		char[] snake_loc = new char[L];
		for(int i = 0 ; i<L ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			char C = st.nextToken().charAt(0);
			snake_time[i] = X;
			snake_loc[i] = C;
		}
		int d = 0;
		int x = 0;
		int y = 0;
		int cnt = 0; //시간
		int time = 0; //인덱스
		Queue<int[]> q = new LinkedList<>(); //꼬리의 위치
		q.add(new int[] {0,0});
		while(true) {
			if(time<L && cnt == snake_time[time]) {
				if(snake_loc[time]=='D') d= (d+1)%4;
				else d = (d+3)%4;
				time++;
			}
			int nr = x+delta[d][0];
			int nc = y+delta[d][1];
			if(nr==-1 || nr==N || nc==-1 || nc==N || map[nr][nc]==1) break;
			if(map[nr][nc] == 0) {
				int[] tail = q.poll();
				map[tail[0]][tail[1]] = 0;
			}
			
			map[nr][nc] = 1;
			q.add(new int[] {nr,nc});
			x = nr;
			y = nc;
			cnt++;
		}
		System.out.println(cnt+1);
	}

}
