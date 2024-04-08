import java.util.*;
import java.io.*;

public class BOJ_2458 {
	static int[][] D;
	final static int INF = 10000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			D = new int[N + 1][N + 1];
			for (int n = 1; n < N + 1; n++) {
				Arrays.fill(D[n], INF);
				D[n][n] = 0;
			}
			
			int in, out;
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				out = Integer.parseInt(st.nextToken());
				in = Integer.parseInt(st.nextToken());				
				D[out][in] = 1;
			}
			
			for (int k = 1; k < N + 1; k++) {
				for (int i = 1; i < N + 1; i++) {
					for (int j = 1; j < N + 1; j++) {
						if(k != j && k != i ) {
							D[i][j] = Integer.min(D[i][j], D[i][k] + D[k][j]);							
						}
					}
				}
			}
			
			int cnt = 0;
			for (int i = 1; i < N + 1; i++) {
				boolean flag = true;
				for (int j = 1; j < N + 1; j++) {
					if(D[i][j] == INF && D[j][i] == INF) {
						flag = false;
						break;
					}
				}
				if (flag) {
					cnt += 1;
				}
			}

			System.out.println(cnt);
		
	}
}
