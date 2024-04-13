import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 2458. 키 순서 / 90분
public class BOJ_2458 {
	static StringBuilder sb = new StringBuilder();
	static int N, M, map[][];
	
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	static void init() throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];

		int from, to;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());

			map[from][to] = 1; // 키: from < to
		}

		solve();
	}
	
	static void solve() throws Exception {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (map[i][k] == 1 && map[k][j] == 1) map[i][j] = 1;
					// i 와 j 사이의 경유지 k에 대해서 알고 있다면 i < j라는 걸 알아낼 수 있다.
				}
			}
		}

		int answer = 0;
		for (int i = 1; i <= N; i++) {
			int cnt = 0;
			for (int j = 1; j <= N; j++) {
				if (map[i][j] == 1 || map[j][i] == 1) cnt++; // 진입차수와 진출 차수 계산
			}
			if (cnt == N-1) answer++; // 자신을 제외한 나머지 학생의 키순서를 알면 순서를 앎
		}

		sb.append(answer);
	}
}