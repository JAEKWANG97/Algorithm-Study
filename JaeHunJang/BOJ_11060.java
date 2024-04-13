import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 점프 점프 - 60 분
public class BOJ_11060 {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		if (N == 1) {
			sb.append(0);
			return;
		}

		solve(arr, N);
	}

	static void solve(int[] arr, int N){
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(0);
		boolean visited[] = new boolean[N];
		visited[0] = true;
		int dp[] = new int[N];

		while(!q.isEmpty()) {
			int cur = q.poll();
			for (int i = 1; i <= arr[cur]; i++) {
				int next = cur + i;
				if (next >= N || visited[next]) {
					continue;
				}

				visited[next] = true;
				q.offer(next);
				dp[next] = dp[cur] + 1;
			}
		}

		if (dp[N-1] > 0) {
			sb.append(dp[N-1]);
		} else sb.append(-1);
	}
}