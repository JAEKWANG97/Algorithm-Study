package algorithm_study.april.day_04_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2343 {

	private static int n, m;
	private static int[] lectures;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		lectures = new int[n];

		st = new StringTokenizer(br.readLine(), " ");
		int maxValue = 0;
		for (int i = 0; i < n; i++) {
			lectures[i] = Integer.parseInt(st.nextToken());
			maxValue = Math.max(maxValue, lectures[i]);
		}
		System.out.println(binarySearch(maxValue, Integer.MAX_VALUE));
	}

	public static long binarySearch(long start, long end) {
		while (start <= end) {
			long sum = 0;
			long mid = (start + end) / 2;
			int count = 1;

			for (int i = 0; i < n; i++) {
				sum += lectures[i];
				if (sum > mid) {
					sum = lectures[i];
					count += 1;
				}
			}
			if (count <= m) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return start;
	}
}