

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Integer>[] friend;
	static int answer;
	static boolean[] visit;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		friend = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			friend[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			friend[a].add(b);
			friend[b].add(a);
		}
		answer = 0;
		for (int i = 0; i < N; i++) {
			if (answer==1)
				break;
			visit = new boolean[N];
			visit[i] = true;
			dfs(i, 0);
		}
		System.out.println(answer);

	}

	public static void dfs(int num, int idx) {
		if (idx == 4) {
			answer=1;
			return;
		}
		for (int i : friend[num]) {
			if (!visit[i]) {
				visit[i] = true;
				dfs(i, idx + 1);
				visit[i] = false;
			}
		}
	}

}
