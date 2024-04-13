package algorithm_study.day_03_19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9205 {
	
	private static class Point{
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			List<Point> store = new ArrayList<>();
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			Point start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				store.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			st = new StringTokenizer(br.readLine());
			Point end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			store.add(end);
			bfs(start, n + 1, end, store);
		}
	}

	private static void bfs(Point start, int k, Point end, List<Point> store) {
		boolean flag = false;
		boolean[] visited = new boolean[k];
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(start);
		while(!queue.isEmpty()) {
			Point curr = queue.poll();
			if(curr.r == end.r && curr.c == end.c) {
				flag = true;
				break;
			}
			for (int i = 0; i < k; i++) {
				Point np = store.get(i);
				if((Math.abs(np.r - curr.r)+Math.abs(np.c - curr.c))<=1000 && !visited[i]) {
					visited[i] = true;
					queue.offer(np);
				}
			}
		}
		
		if(flag) {
			System.out.println("happy");
		} else {
			System.out.println("sad");
		}
	}

}
