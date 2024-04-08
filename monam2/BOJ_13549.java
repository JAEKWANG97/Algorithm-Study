import java.util.ArrayDeque;
import java.util.Scanner;

public class BOJ_13549 { //백준 13549 숨바꼭질3 - 20분
	private static class Point{
		int p;
		int time;
		public Point(int p, int time) {
			super();
			this.p = p;
			this.time =time;
		}
	}//point
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		ArrayDeque<Point> q = new ArrayDeque<Point>();
		boolean[] visited = new boolean[100001];
		int[] dist = {0,-1,1};
		
		q.offer(new Point(n,0));
		visited[n] = true;
		
		while(!q.isEmpty()) {
			int x = q.peek().p;
			int t = q.poll().time;
			if (x==k) {
				System.out.println(t);
				return;
			}
			
			for (int i = 0; i < 3; i++) {
				int nx = 0;
				int d = 0;
				if (i==0) {
					nx = x*2;
				} else {
					nx = x + dist[i];
					d = 1;
				}
				
				if (0<=nx && nx<=100000 && !visited[nx]) {
					q.offer(new Point(nx, t+d));
					visited[nx] = true;
				}
			}
		}
	}//main
}//class
