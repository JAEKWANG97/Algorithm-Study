package algorithm_study.day_03_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16928 {

	private static class PointInfo{
		int value, ladder, snake;

		public PointInfo(int value, int ladder, int snake) {
			super();
			this.value = value;
			this.ladder = ladder;
			this.snake = snake;
		}

		@Override
		public String toString() {
			return "PointInfo [value=" + value + ", ladder=" + ladder + ", snake=" + snake + "]";
		}
		
	}
	
	private static class QueueData{
		PointInfo info;
		int breadth;
		
		public QueueData(PointInfo info, int breadth) {
			super();
			this.info = info;
			this.breadth = breadth;
		}
		
		@Override
		public String toString() {
			return "QueueData [info=" + info + ", breadth=" + breadth + "]";
		}
	}
	
	private static int[] dc = {1, 2, 3, 4, 5, 6};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		PointInfo[] map = new PointInfo[101];

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x] = new PointInfo(x, y, 0);
		}
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			map[u] = new PointInfo(u, 0, v);
		}
		
		for (int i = 1; i <= 100; i++) {
			if(map[i] != null) continue;
			map[i] = new PointInfo(i, 0, 0);
		}
		
		bfs(map);
	}

	private static void bfs(PointInfo[] map) {
		boolean[] visited = new boolean[101];
		Queue<QueueData> queue = new ArrayDeque<>();
		visited[map[1].value] = true;
		queue.offer(new QueueData(map[1], 0));
		while(!queue.isEmpty()) {
			QueueData curr = queue.poll();
			if(curr.info.value == 100) {
				System.out.println(curr.breadth);
				System.exit(0);
			}
			for (int i = 0; i < 6; i++) {
				int nValue = curr.info.value + dc[i];
				if(nValue >= 0 && nValue <= 100 && !visited[nValue]) {
					visited[nValue] = true;
					if(map[nValue].snake != 0) {
						if(map[nValue].snake >= 0 && map[nValue].snake <= 100 && !visited[map[nValue].snake]) {
							visited[map[nValue].snake] = true;
							queue.offer(new QueueData(map[map[nValue].snake], curr.breadth + 1));
						}	
					}
					if(map[nValue].ladder != 0) {
						if(map[nValue].ladder >= 0 && map[nValue].ladder <= 100 && !visited[map[nValue].ladder]) {
							visited[map[nValue].ladder] = true;
							queue.offer(new QueueData(map[map[nValue].ladder], curr.breadth + 1));
						}	
					}
					if(map[nValue].ladder == 0 && map[nValue].snake == 0) {
						queue.offer(new QueueData(map[nValue], curr.breadth + 1));
					}
				}
			}
		}
	}
}
