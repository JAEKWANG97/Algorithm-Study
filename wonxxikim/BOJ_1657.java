
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static long[] result;
	static bus[] busstop;

	static class bus {
		int start;
		int end;
		int weight;

		public bus(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		busstop = new bus[M]; //버스 정보
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			busstop[i] = new bus(A, B, C);
		}

		result = new long[N + 1]; //최단거리 배열
		Arrays.fill(result, Long.MAX_VALUE);
		result[1] = 0; //시작점 갱신
		
		if (!bellmanFord()) //음의 사이클 있는 경우
			System.out.println(-1);
		else { //음의 사이클 없는 경우
			for (int i = 2; i <= N; i++) {
				if (result[i] == Long.MAX_VALUE)
					System.out.println(-1);
				else
					System.out.println(result[i]);
			}
		}

	}

	public static boolean bellmanFord() {
		//N-1번 수행
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < M; j++) {
				bus b = busstop[j];

				if (result[b.start] != Long.MAX_VALUE && result[b.end] > result[b.start] + b.weight) {
					result[b.end] = result[b.start] + b.weight;
				}
			}
		}
		
		//음수 cycle 확인
		//갱신이 된다면 사이클이 있다는 뜻
		for (int i = 0; i < M; i++) {
			bus b = busstop[i];
			if (result[b.start] != Long.MAX_VALUE && result[b.end]>result[b.start] + b.weight)
				return false;
		}
		return true;
	}

}
