import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 공유기 설치 - 60분
public class BOJ_2110 {
	static StringBuilder sb = new StringBuilder();
	static int N, C;
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	private static void init() throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		long homes[] = new long[N];
//		TreeSet<Long> set = new TreeSet<>();

		for (int i = 0; i < N; i++) {
//			set.add(Long.parseLong(br.readLine()));
			homes[i] = Long.parseLong(br.readLine());
		}

		solution(homes);
	}

	private static void solution(long[] homes) {
		Arrays.sort(homes); // 이분 탐색을 위한 정렬

		// 공유기 설치 거리를 통한 이분 탐색
		long start = 1;
		long end = homes[N-1] - homes[0]+1;

		while(start < end) {
			long mid = (start + end) / 2;

			if (getCount(mid, homes) < C) {
				end = mid;
			} else {
				start = mid+1;
			}
		}


		sb.append(end-1);
	}

	private static int getCount(long distance, long[] homes) {
		int count = 1;
		long before = homes[0]; // 1번집 설치

		for (int i = 1; i < N; i++) {
			if (homes[i] - before >= distance) { // 공유기 설치 거리에 따라 공유기 설치 가능 개수 계산
				count++;
				before = homes[i];
			}
		}

		return count;
	}
}