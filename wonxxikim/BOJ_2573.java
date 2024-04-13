import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] delta = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	static int map[][], N, M, ice;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0)
					ice++;
			}
		}

		int time = 0;
		while (true) {
			if (issplit() || ice == 0)
				break;
			time++;
			boolean[][] visit = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 0)
						continue;
					visit[i][j] = true;
					int minus = 0;
					for (int d = 0; d < 4; d++) {
						int nr = i+ delta[d][0];
						int nc = j+delta[d][1];
						if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visit[nr][nc] && map[nr][nc] == 0) {
							minus++;
						}
					}
					if (map[i][j] - minus <= 0) {
						map[i][j] = 0;
						ice--;
					} else
						map[i][j] -= minus;
				}
			}
		}
		if (ice == 0)
			System.out.println(0);
		else System.out.println(time);

	}

	public static boolean issplit() {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			if (!q.isEmpty())
				break;
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0) {
					q.add(new int[] { i, j });
					visit[i][j] = true;
					break;
				}
			}

		}
		int cnt = 1;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x= cur[0];
			int y = cur[1];
			
			for(int d= 0 ; d<4 ; d++) {
				int nr = x+delta[d][0];
				int nc = y+delta[d][1];
				if(nr>=0 && nr<N && nc>=0 && nc<M && !visit[nr][nc] && map[nr][nc]>0) {
					q.add(new int[] {nr,nc});
					visit[nr][nc] = true;
					cnt++;
				}
			}
		}
		if(cnt==ice) return false;
		else return true;
	}
}
