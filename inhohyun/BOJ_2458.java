
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2458 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int INF = 1_000 * 1_000;
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] graph = new int[n + 1][n + 1];

		// 그래프 초기화
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				graph[i][j] = INF;
			}

		}

		// 입력
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph[a][b] = 1;
			
		}

		// 플로이드로 최단거리 갱신
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (graph[i][j] > graph[i][k] + graph[k][j]) {
						graph[i][j] = graph[i][k] + graph[k][j];
					}

				}
			}
		}

		// 모든 학생과 비교 가능한 학생 -> 거리가 maxvalue가 아닌 학생의 수가 n-1명인 학생
		int ans = 0;
		for (int i = 1; i <= n; i++) {
			int cnt = 0;
			for (int j = 1; j <= n; j++) {
				if(graph[i][j] != INF || graph[j][i] != INF) { // 이걸 다 통과해버리는 에러 
					cnt++;
				}
//				System.out.println("test cnt : "+ cnt);
			}
			
			if(cnt == n-1) {
				ans++;
			}
		}
		
		System.out.println(ans);

	}
}
