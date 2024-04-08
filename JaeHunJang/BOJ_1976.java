import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// 백준 - 여행 가자
public class BOJ_1976 {
	static StringBuilder sb = new StringBuilder();
	static int parents[];

	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	private static void init() throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 도시의 수
		int M = Integer.parseInt(br.readLine()); // 여행 계획 도시의 수

		parents = new int[N+1]; // make-set
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}

		StringTokenizer st;
		for (int from = 1; from <= N; from++) {
			st = new StringTokenizer(br.readLine());
			int isConnect;
			for (int to = 1; to <= N; to++) {
				isConnect = Integer.parseInt(st.nextToken());
				if (isConnect == 1) union(from, to); // union, 크루스칼
			}
		}

		st = new StringTokenizer(br.readLine());
		boolean flag = true;
		int from = Integer.parseInt(st.nextToken());
		for (int i = 1; i < M; i++) {
			int to = Integer.parseInt(st.nextToken());
			int rootFrom = find(from);
			int rootTo = find(to);
			if (rootFrom == rootTo) { // 모두 부모가 같으면 여행 계획이 가능함
				from = to;
			} else {
				flag = false;
				break;
			}
		}

		if (flag) sb.append("YES");
		else sb.append("NO");
	}

	static int find(int n) {
		if (parents[n] == n) return n;
		return parents[n] = find(parents[n]);
	}

	static boolean union(int a, int b) {
		int pA = find(a);
		int pB = find(b);
		if (pA == pB) return false;
		parents[pA] = pB;
		return true;
	}
}

