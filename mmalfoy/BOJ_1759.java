package mmalfoy;

import java.io.*;
import java.util.*;

public class BOJ_1759 {
	private static char[] tokens, permutation;
	private static int L, C;
	private static boolean[] visited;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");

		tokens = new char[C];
		for (int c = 0; c < C; c++) {
			tokens[c] = st.nextToken().charAt(0);
		}

		Arrays.sort(tokens);
		permutation = new char[L];
		visited = new boolean[C];
		nCr(0, 0, 0, 0);
		System.out.println(sb.toString());
	}

	private static void nCr(int start, int depth, int vowCnt, int conCnt) {
		if (depth == L) {
			if (vowCnt < 1 || conCnt < 2) {
				return;
			}

			for (int i = 0; i < L; i++) {
				sb.append(permutation[i]);
			}
			sb.append('\n');
			return;
		}

		for (int c = start; c < C; c++) {
			if (!visited[c]) {
				visited[c] = true;
				permutation[depth] = tokens[c];
				if (tokens[c] == 'a' || tokens[c] == 'e' || tokens[c] == 'i' || tokens[c] == 'o' || tokens[c] == 'u') {
					nCr(c + 1, depth + 1, vowCnt + 1, conCnt);
				} else {
					nCr(c + 1, depth + 1, vowCnt, conCnt + 1);
				}				
				visited[c] = false;
			}
		}

	}

}