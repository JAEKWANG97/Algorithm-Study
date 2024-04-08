
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

// 테트로미노 - 90분
public class BOJ_14500 {
	static StringBuilder sb = new StringBuilder();
	static int N, M, maxSum;
	static int[][] map, map90, map180, map270, mapRr, mapRc;
	// 왼쪽 위를 기준으로
	static int deltasR[][] = {{0, 0, 0, 0}, {0, 1, 2, 3}}; // ㅡ자
	static int deltasL[][] = {{0, 1, 2, 2}, {0, 0, 0, 1}}; // L자
	static int deltasRL[][] = {{0, 1, 2, 2}, {0, 0, 0, -1}}; // 대칭 L자
	static int deltas180L[][] = {{0, 0, 1, 2}, {0, 1, 0, 0}}; // 180도 L자
	static int deltasR180L[][] = {{0, 0, 1, 2}, {0, -1, 0, 0}}; // 대칭 180도 L자
	static int deltasS[][] = {{0, 0, 1, 1}, {0, 1, 0, 1}}; // 사각형
	static int deltasZ[][] = {{0, 1, 1, 2}, {0, 0, 1, 1}}; // Z자
	static int deltasRZ[][] = {{0, 1, 1, 2}, {1, 1, 0, 0}}; // 대칭 Z자
	static int deltasU[][] = {{0, 0, 0, 1}, {0, 1, 2, 1}}; // ㅜ자

	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	private static void init() throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		map180 = new int[N][M];
		mapRr = new int[N][M];
		mapRc = new int[N][M];

		map90 = new int[M][N];
		map270 = new int[M][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				map180[N-1-i][M-1-j] = map[i][j];
				mapRc[N-1-i][j] = map[i][j];
				mapRr[i][M-1-j] = map[i][j];
				map90[j][N-1-i] = map[i][j];
				map270[M-1-j][i] = map[i][j];
			}
		}
		solve();
	}
	
	static void solve() throws Exception {
		maxSum = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				maxSum = Math.max(maxSum, maxSum(i, j, map));
				maxSum = Math.max(maxSum, maxSum(i, j, map180));
				maxSum = Math.max(maxSum, maxSum(i, j, mapRc));
				maxSum = Math.max(maxSum, maxSum(i, j, mapRr));
				maxSum = Math.max(maxSum, maxSum(j, i, map90));
				maxSum = Math.max(maxSum, maxSum(j, i, map270));
			}
		}
		
		sb.append(maxSum);
	}
	
	static int maxSum(int r, int c, int[][] map) {
		int max = 0;
		max = Math.max(max, calcSum(r, c, deltasR, map));
		max = Math.max(max, calcSum(r, c, deltasL, map));
		max = Math.max(max, calcSum(r, c, deltasS, map));
		max = Math.max(max, calcSum(r, c, deltasZ, map));
		max = Math.max(max, calcSum(r, c, deltasU, map));
		max = Math.max(max, calcSum(r, c, deltasRL, map));
		max = Math.max(max, calcSum(r, c, deltasR180L, map));
		max = Math.max(max, calcSum(r, c, deltas180L, map));
		max = Math.max(max, calcSum(r, c, deltasRZ, map));
//		System.out.println(max);
		return max;
	}
	
	static int calcSum(int r, int c, int[][] polio, int[][] map) {
		int sum = 0;
		for (int d = 0; d < polio[0].length; d++) {
			int nr = polio[0][d] + r;
			int nc = polio[1][d] + c;
			
			if (nr < 0 || nr >= map.length || nc < 0 || nc >= map[0].length) {
				sum = -1;
				break;
			}
			sum += map[nr][nc];
		}
//		System.out.printf("r : %d | c : %d | max : %d\n", r, c, sum);
		return sum;
	}
	
	static void printMap(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println();
	}
	
	static void reverseMap() {
		
	}
}

