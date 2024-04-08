
import java.io.*;
import java.util.*;

public class BOJ_16234 {
	static int[][] G, d = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	static int N, L, R;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		G = new int[N][N];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int m = 0; m < N; m++) {
				G[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 인구 이동이 없을 때까지
		boolean isMoved = true;
		int cnt = -1;
		while(isMoved) {
			isMoved = false;
			boolean[][] visited = new boolean[N][N];
			List<List<Integer>> totalList = new ArrayList<>();
			for (int n = 0; n < N; n++) {
				for (int m = 0; m < N; m++) {
					List<Integer> list = new ArrayList<>();
					list.add(n * N + m);
					visited[n][m] = true;
					dfs(n, m, visited, list);						
					if (list.size() > 1) {			
						isMoved = true;
						totalList.add(list);
					}
				}
			}
			move(totalList);
			cnt += 1;
		}
		System.out.println(cnt);
		
	}
	
	private static void dfs(int n, int m, boolean[][] visited, List<Integer> list) {
		for (int i = 0; i < 4; i++) {
			int ny = n + d[i][0];
			int nx = m + d[i][1];
			
			if (ny < 0 || ny > N - 1 || nx < 0 || nx > N - 1 || visited[ny][nx]) {
				continue;
			}
			if (diff(G[n][m],G[ny][nx])) {
				visited[ny][nx] = true;
				list.add(ny * N + nx);
				dfs(ny, nx, visited, list);
			}
		}	
	}
	
	private static void move(List<List<Integer>> totalList) {
		for (List<Integer> list : totalList) {			
			int sum = 0;
			for (int idx : list) {
				sum += G[idx / N][idx % N];
			}
			sum /= list.size();
			
			for (int idx : list) {
				G[idx / N][idx % N] = sum;
			}
		}
	}
	
	private static boolean diff(int a, int b) {
		int diff = Math.abs(b - a);
		if (diff < L || diff > R) {
			return false;
		}
		return true;
	}
}
