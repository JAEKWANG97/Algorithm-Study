package algorithm_study.day_04_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17070 {

	private static int N, ans;
	private static int[][] map;
	private static int[] vertical = {1, 0};
	private static int[] horizontal = {0, 1};
	private static int[] diagonal = {1, 1};

			
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		movePipe(1, 2, "horizontal");
		System.out.println(ans);
	}
	
	public static void movePipe(int r, int c, String pipeState) {
		
		int nr, nc;
		
		if(r == N && c == N) {
			ans++;
			return;
		}
		
		if(pipeState.equals("vertical")) {
			
			if(verticalCheck(r + vertical[0], c + vertical[1])) {
				nr = r + vertical[0];
				nc = c + vertical[1];
				movePipe(nr, nc, "vertical");
			}
			if(diagonalCheck(r + vertical[0], c + vertical[1], r + horizontal[0], c + horizontal[1], r + diagonal[0], c + diagonal[1])) {
				nr = r + diagonal[0];
				nc = c + diagonal[1];
				movePipe(nr, nc, "diagonal");
			}
		}
		else if(pipeState.equals("horizontal")) {
			
			if(horizontalCheck(r + horizontal[0], c + horizontal[1])) {
				nr = r + horizontal[0];
				nc = c + horizontal[1];
				movePipe(nr, nc, "horizontal");
			}
			if(diagonalCheck(r + vertical[0], c + vertical[1], r + horizontal[0], c + horizontal[1], r + diagonal[0], c + diagonal[1])) {
				nr = r + diagonal[0];
				nc = c + diagonal[1];
				movePipe(nr, nc, "diagonal");
			}
		}
		else if(pipeState.equals("diagonal")) {
			
			if(horizontalCheck(r + horizontal[0], c + horizontal[1])) {
				nr = r + horizontal[0];
				nc = c + horizontal[1];
				movePipe(nr, nc, "horizontal");
			}
			if(diagonalCheck(r + vertical[0], c + vertical[1], r + horizontal[0], c + horizontal[1], r + diagonal[0], c + diagonal[1])) {
				nr = r + diagonal[0];
				nc = c + diagonal[1];
				movePipe(nr, nc, "diagonal");
			}
			if(verticalCheck(r + vertical[0], c + vertical[1])) {
				nr = r + vertical[0];
				nc = c + vertical[1];
				movePipe(nr, nc, "vertical");
			}
		}
	}
	
	public static boolean verticalCheck(int nr, int nc) {
		if(nr >= 1 && nr <= N && nc >= 1 && nc <= N && map[nr][nc] != 1) {
			return true;
		}
		return false;
	}
	
	public static boolean horizontalCheck(int nr, int nc) {
		if(nr >= 1 && nr <= N && nc >= 1 && nc <= N && map[nr][nc] != 1) {
			return true;
		}
		return false;
	}
	
	public static boolean diagonalCheck(int v_nr, int v_nc, int h_nr, int h_nc, int d_nr, int d_nc) {
		if(verticalCheck(v_nr, v_nc) && horizontalCheck(h_nr, h_nc) && d_nr >= 1 && d_nr <= N && d_nc >= 1 && d_nc <= N && map[d_nr][d_nc] != 1) {
			return true;
		}
		return false;
	}

}
