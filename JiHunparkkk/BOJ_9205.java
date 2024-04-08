import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static class Point{
		int x, y, idx;
		
		public Point(int x, int y, int idx) {
			this.x = x;
			this.y = y;
			this.idx = idx;
		}
	}
	
	private static Point home;
	private static Point[] market;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= t; test_case++) {
			int n = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			int x,y;
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			home = new Point(x, y, 0);
			
			market = new Point[n + 2];
			for(int i = 1; i <= n + 1; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				market[i] = new Point(x, y, i);
			}
			
			String answer = bfs(n);
			System.out.println(answer);
		}
	}
	
	private static String bfs(int n) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(home);
		
		int[] distance = new int[n + 2];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[0] = 0;	// 집
		while(!queue.isEmpty()) {
			Point poll = queue.poll();
			
			for(int i = 1; i <= n + 1; i++) {
				int diff = Math.abs(poll.x - market[i].x) + Math.abs(poll.y - market[i].y);
				
				if(diff <= 1000 && distance[i] > diff + distance[poll.idx]) {
					distance[i] = diff + distance[poll.idx];
					queue.add(new Point(market[i].x, market[i].y, market[i].idx));
					if(i == n + 1) {
						return "happy";
					}
				}
			}	
		}
		return "sad";
	}
}
