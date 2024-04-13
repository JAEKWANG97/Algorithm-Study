
import java.io.*;
import java.util.*;

public class BOJ_14499 {
	static int N, M, x, y, K;
	static int[] dice = new int[7];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int[][] G = new int[N][M];
		
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int m = 0; m < M; m++) {
				G[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int[][]d = new int[][] {{}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}}; 
		for (int k = 0; k < K; k++) {
			int op = Integer.parseInt(st.nextToken());
			int ny = y + d[op][0];
			int nx = x + d[op][1];
			
			if (ny < 0 || ny > N - 1 || nx < 0 || nx > M - 1) {
				continue;
			}
			
			switch(op) {
			case 1:
				one();
				break;
			case 2:
				two();
				break;
			case 3:
				three();
				break;
			case 4:
				four();
				break;
			}
			
			if (G[ny][nx] == 0) {
				G[ny][nx] = dice[6];
			} else {
				dice[6] = G[ny][nx];
				G[ny][nx] = 0;
			}
			y = ny;
			x = nx;
			
			System.out.println(dice[1]);
		}
		
		// 이동시마다 주사위 윗면, 만약 바깥인 경우에는 무시(continue)
	}
	
	// 우
	private static void one() {
		int buf = dice[4];
		dice[4] = dice[6];
		dice[6] = dice[3];
		dice[3] = dice[1];
		dice[1] = buf;
	}
	
	//좌
	private static void two() {
		int buf = dice[3];
		dice[3] = dice[6];
		dice[6] = dice[4];
		dice[4] = dice[1];
		dice[1] = buf;
	}
	//상
	private static void three() {
		int buf = dice[5];
		dice[5] = dice[6];
		dice[6] = dice[2];
		dice[2] = dice[1];
		dice[1] = buf;
	}
	
	//하
	private static void four() {
		int buf = dice[2];
		dice[2] = dice[6];
		dice[6] = dice[5];
		dice[5] = dice[1];
		dice[1] = buf;
	}
	
	
}
