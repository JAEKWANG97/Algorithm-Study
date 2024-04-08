import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14889 {
	static StringBuilder sb = new StringBuilder();
	static int minDiff, map[][];
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	private static void init() throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 사람 수
		
		minDiff = Integer.MAX_VALUE;
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		solve(N);
	}
	
	private static void solve(int N) throws Exception {
		combi(0, 0, N, new boolean[N]);
		
		sb.append(minDiff).append("\n");
	}
	
	private static void calcStatus(boolean visited[], int N) {
		int team1 = 0, team2 = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j) continue;
				if (visited[i] && visited[j]) {
					team1 += map[i][j];
				} else if (!visited[i] && !visited[j]) {
					team2 += map[i][j];
				}
			}
		}
		minDiff = Math.min(minDiff, Math.abs(team1 - team2));
//		System.out.println(team1 + "|" + team2);
	}
	
	private static void combi(int cnt, int start, int N, boolean visited[]) {
		if (cnt == N/2) {
//			System.out.println(Arrays.toString(visited));
			calcStatus(visited, N);
			return;
		}
		
		for (int i = start; i < N; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			combi(cnt+1, i+1, N, visited);
			visited[i] = false;
		}
	}
}

