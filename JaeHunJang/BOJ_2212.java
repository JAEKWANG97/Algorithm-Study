import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 센서 / 90분
public class BOJ_2212 {
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	private static void init() throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 센서 개수
		int K = Integer.parseInt(br.readLine()); // 집중국 개수
		int[] sensors = new int[N]; // 센서 좌표

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			sensors[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(sensors);

		Integer[] diffDist = new Integer[N-1];
		for (int i = 0; i < N-1; i++) {
			diffDist[i] = sensors[i+1] - sensors[i]; // 센서들의 거리차이 구하기
		}
		
		Arrays.sort(diffDist, Collections.reverseOrder());

		int sum = 0;
		for (--K; K < N-1; K++) {
			sum += diffDist[K]; // 센서들의 거리합
		}

		sb.append(sum);
	}
}
