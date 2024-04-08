package algorithm_study.day_03_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SEA_4014 {

	private static int N, X;
	private static int[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			
			int result = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			for (int r = 0; r < N; r++) {
				map[r] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			
			for (int i = 0; i < N; i++) {
				if(rowCheck(map[i])) {
//					System.out.println(i + ": " + "true");
					result += 1;
				}
//				else System.out.println(i + ": " + "false");
			}
			
//			System.out.println("#" + test_case + " " + result);
			
			int[][] rotatedMap = rotateMap(map);
			
			for (int i = 0; i < N; i++) {
				if(rowCheck(rotatedMap[i])) result += 1;
			}
			
			System.out.println("#" + test_case + " " + result);
		}
		
	}

	private static int[][] rotateMap(int[][] map) {
		int[][] rotatedMap = new int[N][N];
		
		for (int c = 0; c < N; c++) {
			int[] nRow = new int[N];
			for (int r = 0; r < N; r++) {
				nRow[r] = map[r][c]; 
			}
			rotatedMap[c] = nRow;
		}
		
//		for (int r = 0; r < N; r++) {
//			for (int c = 0; c < N; c++) {
//				System.out.print(rotatedMap[r][c] + " ");
//			}
//			System.out.println();
//		}
		return rotatedMap;
	}

	private static boolean rowCheck(int[] row) {
		int now = row[0];
		int count = 1;
		boolean isDown = false;
		
		for (int i = 1; i < N; i++) {
			if(isDown) {
				if(row[i] == now) {
					count += 1;
					if(count == X) {
						isDown = false;
						count = 0;
					}
				}
				else {
					return false;
				}
				
			}
			else {
				if(row[i] == now) {
					count += 1;
					continue;
				}
				if(now - row[i] == 1) {
					isDown = true;
					now = row[i];
					count = 1;
				}
				else if(now - row[i] == -1) {
					if(count < X) return false;
					count = 1;
					now = row[i];
				}
				else return false;
			}
		}
		
		if(isDown) {
			return false;
		}
		
		return true;
	}

}
  