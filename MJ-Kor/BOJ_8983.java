package algorithm_study.day_04_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_8983 {

	private static int result = 0;
	private static int M, N, L;
	private static int[] rowLoc;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		rowLoc = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			rowLoc[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(rowLoc);
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			hunt(0, M - 1, r, c);
		}
		System.out.println(result);
	}

	private static void hunt(int start, int end, int r, int c) {
		while(start <= end) {
			int mid = (start + end) / 2;
			long dist = Math.abs(rowLoc[mid] - r) + c;
			if(dist <= L) {
				result++;
				break;
			}
			if(r < rowLoc[mid]) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
	}
}
