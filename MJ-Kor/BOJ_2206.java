package algorithm_study.day_03_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206 {
	
	private static int N, M;
	private static int[] dr = {-1, 0, 1, 0};
	private static int[] dc = {0, 1, 0, -1};
	private static int[][] originalMap;
	private static int[][] distMap;
	private static boolean[][][] visited;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String str;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		originalMap = new int[N][M];
		distMap = new int[N][M];
		visited = new boolean[2][N][M];
		distMap[0][0] = 1;
		
		for (int r = 0; r < N; r++) {
			str = br.readLine();
			for (int c = 0; c < M; c++) {
				originalMap[r][c] = str.charAt(c) - '0';
			}
		}
		
		bfs();
	}
	
	public static void bfs() {
		int nr, nc;
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {0, 0, 0});
		
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			if(current[0] == N - 1 && current[1] == M - 1) {
				System.out.println(distMap[current[0]][current[1]]);
				System.exit(0);
			}
			
			for (int i = 0; i < 4; i++) {
				nr = current[0] + dr[i];
				nc = current[1] + dc[i];
				if(nr >= 0 && nr < N && nc >= 0 && nc < M) {
					if(originalMap[nr][nc] == 1) {
						// 지금까지 벽을 부순적이 없고, 이 벽을 만난적이 없어야 함
						// 벽을 부수고 나서 이동한 곳은 애초에 다시 갈 필요가 없다
						if(current[2] == 0 && !visited[1][nr][nc]) {
							visited[current[2]][nr][nc] = true;
							distMap[nr][nc] = distMap[current[0]][current[1]] + 1;
							queue.offer(new int[] {nr, nc, 1});
						}
					}
					else {
						if(!visited[current[2]][nr][nc]) {
							visited[current[2]][nr][nc] = true;
							distMap[nr][nc] = distMap[current[0]][current[1]] + 1;
							queue.offer(new int[] {nr, nc, current[2]});
						}
					}
				}
			}
		}
		System.out.println(-1);
	}
}
