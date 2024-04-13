
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 주사위 굴리기 / 60분
public class BOJ_14499 {
	static StringBuilder sb = new StringBuilder();
	static int map[][], dice[][];

	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	private static void init() throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 지도
		int M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken()); // 시작 좌표
		int y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken()); // 명령 개수

		dice = new int[4][3]; // 주사위

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		int d = 0, deltas[][] = {{0,0}, {0,1}, {0,-1}, {-1,0}, {1,0}};
		for (int i = 0; i < K; i++) {
			d = Integer.parseInt(st.nextToken());
			int nx = deltas[d][0] + x;
			int ny = deltas[d][1] + y;

			if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
			switch (d) {
				case 1: rotateRow(); break;
				case 2: rotateRow(); rotateRow(); rotateRow(); break;
				case 3: rotateCol(); break;
				case 4: rotateCol(); rotateCol(); rotateCol(); break;
			}
			toggle(nx, ny);
			x = nx;
			y = ny;
			sb.append(dice[1][1]).append("\n");
		}
	}

	static void toggle(int r, int c) {
		if (map[r][c] == 0) {
			map[r][c] = dice[3][1];
		} else {
			dice[3][1] = map[r][c];
			map[r][c] = 0;
		}
	}

	static void rotateRow() {
		int temp = dice[3][1];
		dice[3][1] = dice[1][2];
		dice[1][2] = dice[1][1];
		dice[1][1] = dice[1][0];
		dice[1][0] = temp;
	}

	static void rotateCol() {
		int temp = dice[3][1];
		dice[3][1] = dice[2][1];
		dice[2][1] = dice[1][1];
		dice[1][1] = dice[0][1];
		dice[0][1] = temp;
	}

	static void printMap(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println();
	}
}

