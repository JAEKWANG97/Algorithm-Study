import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int a, b;

		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());

		int[][] board = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i != j && board[i][j] == 0) {
					board[i][j] = n + 1;
				}
			}
		}

		while (a != -1 && b != -1) {
			board[a][b] = 1;
			board[b][a] = 1;
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
		}

		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					board[i][j] = Math.min(board[i][k] + board[k][j], board[i][j]);
				}
			}
		}

		int max, min = Integer.MAX_VALUE;
		int[] dis = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			max = 0;
			for (int j = 1; j <= n; j++) {
				max = Math.max(max, board[i][j]);
				dis[i] = max;
			}
			min = Math.min(min, max);
		}

		int cnt = 0;
		for (int i = 1; i <= n; i++) {
			if (min == dis[i]) {
				cnt++;
				sb.append(i).append(" ");
			}
		}

		System.out.println(min + " " + cnt);
		System.out.println(sb.toString());
	}
}
