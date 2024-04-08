package ssafy_1monthTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_4014 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc < T + 1; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][N];
			int answer = 0;
			for (int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < N; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
				}
			} // 입력 끝

			// 가로검사
			for (int y = 0; y < N; y++) {
				if (checkRow(map, y, X))
					answer++;
			}
			// 세로검사
			for (int x = 0; x < N; x++) {
				if (checkColumn(map, x, X))
					answer++;
			}

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

	private static boolean checkRow(int[][] map, int y, int size) {
		int hight = map[y][0];
		int N = map.length;
		int[] arr = Arrays.copyOf(map[y], N);
		boolean[] fix = new boolean[N];
		//System.out.print(Arrays.toString(arr) + ": ");
		for (int i = 0; i < N-1; i++) {
			// 1. 본인 기준 앞이 나보다 작음 -> 앞으로 설치
			//System.out.println(i + ": " + arr[i] + " " + arr[i+1]);
			if (arr[i] < arr[i + 1]) {
				if (arr[i+1] - arr[i] != 1) return false;
				// j = 바뀔놈 idx
				for (int j = i; j > i - size; j--) {
					if (j < 0 || arr[j] != arr[i + 1] - 1 || fix[j])
						return false;
					fix[j] = true;
				}				
			}			
			else if (arr[i] > arr[i + 1]) {
				if (arr[i] - arr[i + 1] != 1) return false;
				// j = 바뀔놈 idx
				for (int j = i + 1; j < i + 1 + size; j++) {
					if (j >= N || arr[j] != arr[i + 1] || fix[j])
						return false;
					fix[j] = true;
				}
				i += size - 1;
			}
			// 2. 본인기준 앞이 나보다 큼 -> 뒤로 설치
			
			//System.out.println(i +" = O!");
		}
		//System.out.print("O!");
		return true;
	}

	private static boolean checkColumn(int[][] map, int x, int size) {
		int hight = map[0][x];
		int N = map.length;
		int[] arr = new int[N];
		boolean[] fix = new boolean[N];
		for (int i = 0; i < N; i++) {
			arr[i] = map[i][x];
		}
		//System.out.print(Arrays.toString(arr) + ": ");
		for (int i = 0; i < N-1; i++) {
			// 1. 본인 기준 앞이 나보다 작음 -> 앞으로 설치
			//System.out.println(i + ": " + arr[i] + " " + arr[i+1]);
			
			if (arr[i] < arr[i + 1]) {
				if (arr[i+1] - arr[i] != 1) return false;
				// j = 바뀔놈 idx
				for (int j = i; j > i - size; j--) {
					if (j < 0 || arr[j] != arr[i + 1] - 1 || fix[j])
						return false;
					fix[j] = true;
				}				
			}			
			else if (arr[i] > arr[i + 1]) {
				if (arr[i] - arr[i + 1] != 1) return false;
				// j = 바뀔놈 idx
				for (int j = i + 1; j < i + 1 + size; j++) {
					if (j >= N || arr[j] != arr[i + 1] || fix[j])
						return false;
					fix[j] = true;
				}
				i += size - 1;
			}

		}
		//System.out.print("O!");
		return true;
	}

}
