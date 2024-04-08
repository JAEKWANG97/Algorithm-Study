package ex0312;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4179 {
	private static class Fire{
		int x;
		int y;
		public Fire(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
	private static class Person{
		int x;
		int y;
		public Person(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,-1,1};
	private static int r,c;
	private static int[][] arr;
	private static Queue<Fire> fir = new LinkedList<>();
	private static Queue<Person> per = new LinkedList<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		arr = new int[r][c];
		
		for(int i = 0; i < r; i++) {
			String s = br.readLine();
			for(int j = 0; j < c; j++) {
				arr[i][j] = s.charAt(j);
				
				if(arr[i][j] == 'J') {
					per.offer(new Person(i, j));
				}
				else if(arr[i][j] == 'F') {
					fir.offer(new Fire(i, j));
				}
			}
		}
		
		bfs();
	}//main end
	
	private static void bfs() {
		int count = 0;
		while(true) {
			count++;
			
			int len = fir.size();
			while(len > 0) {
				len--;
				Fire fire = fir.poll();
				
				for(int d = 0; d < 4; d++) {
					int nx = fire.x + dx[d];
					int ny = fire.y + dy[d];
					
				if(nx >= 0 && ny >= 0 && nx < r && ny < c) {
					if(arr[nx][ny] == '.' || arr[nx][ny] == 'J')
					fir.offer(new Fire(nx, ny));
					arr[nx][ny] = 'F';	
					}
				}
			}
			
			int js = per.size();
			while(js>0) {
				js--;
				Person person = per.poll();
				
				for(int d = 0; d < 4; d++) {
					int nx = person.x + dx[d];
					int ny = person.y + dy[d];
					
					if((nx < 0 || ny < 0 || nx >= r || ny >= c)){
						System.out.println(count);
						return;
					}
					
					if(nx >= 0 && ny >= 0 && nx < r && ny < c && arr[nx][ny] == '.') {
						per.offer(new Person(nx, ny));
						arr[nx][ny] = 'F';
					}
				}
			}
			if(per.isEmpty()) {
				System.out.println("IMPOSSIBLE");
				return;
			}
		}
	}//bfs end
}//class end

