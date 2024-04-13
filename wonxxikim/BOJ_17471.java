import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, people[], size, result;
	static ArrayList<Integer>[] map;
	static boolean choose[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 구역의 개수

		StringTokenizer st = new StringTokenizer(br.readLine());
		people = new int[N + 1]; // 구역별 인구수
		int[] test = new int[N];
		for (int i = 1; i <= N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}

		map = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			map[i] = new ArrayList<>();
		}

		for (int i = 1; i < N + 1; i++) { // 각 구역과 인접한 구역의 정보
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()); // 인접한 구역의 개수
			for (int j = 0; j < num; j++) {
				int space = Integer.parseInt(st.nextToken());
				map[i].add(space);
			}
		}
		result = Integer.MAX_VALUE;

		// 선거구 나누기

		for (int i = 1; i <= N / 2; i++) {
			size = i;
			choose = new boolean[N + 1];
			combination(0, 1);
		}
		if (result == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(result);

	}

	public static void combination(int cnt, int start) {
		if (cnt == size) {
			int[] A = new int[size];
			int[] B = new int[N - size];
			int idx_a = 0;
			int idx_b = 0;
			for (int i = 1; i <= N; i++) {
				if (choose[i]) {
					A[idx_a++] = i;
				}
				else {
					B[idx_b++] = i;
				}
			}
			if (isconnect(A, size) && isconnect(B, N - size)) { // 연결되어있다면
				int sum_a = 0;
				int sum_b = 0;
				for (int i = 1; i <= N; i++) {
					if (choose[i])
						sum_a += people[i];
					else
						sum_b += people[i];
				}
				if (result > Math.abs(sum_a - sum_b))
					result = Math.abs(sum_a - sum_b);

			}
			return;
		}
		for (int i = start; i <= N; i++) {
			choose[i] = true;
			combination(cnt + 1, i + 1);
			choose[i] = false;
		}
	}
	
	public static boolean isconnect(int[] A, int check_size) {
		boolean[] check = new boolean[N+1];
		Queue<Integer> q = new LinkedList<>();
		q.add(A[0]);
		while(!q.isEmpty()) {
			int present = q.poll();
			check[present] = true;
			for(int x : map[present]) {
				if(!check[x] && choose[x] == choose[A[0]]) q.add(x);
			}
		}
		for(int x : A) {
			if(!check[x]) return false;
		}
		return true;
	}


}
