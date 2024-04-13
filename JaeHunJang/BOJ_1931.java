import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1931 {
	
	static StringBuilder sb = new StringBuilder();
	static int N, dp[][];
	static Meeting list[];
	static class Meeting implements Comparable<Meeting> {
		int s, e;

		public Meeting(int s, int e) {
			this.s = s;
			this.e = e;
		}
		
		@Override
		public int compareTo(Meeting o) {
			if (Integer.compare(this.e, o.e) == 0) {
				return Integer.compare(this.s, o.s);
			}
			return Integer.compare(this.e, o.e); // 종료시간 기준으로 정렬
		}

		@Override
		public String toString() {
			return "Meeting [s=" + s + ", e=" + e + "]";
		}
	}
	public static void main(String[] args) throws Exception {
		init();
		solve();
		System.out.println(sb.toString());
	}
	
	private static void init() throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 회의 개수
		list = new Meeting[N];
		
		int s, e;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken()); // 시작 시간
			e = Integer.parseInt(st.nextToken()); // 종료 시간
			list[i] = new Meeting(s, e);
		}
		
		Arrays.sort(list); // 정렬
	}
	
	private static void solve() throws Exception {
		Meeting cur = list[0]; // 처음 회의 담기
		
		int cnt = 1;
		for (int i = 1; i < N; i++) {
			if (cur.e <= list[i].s) { // 회의 끝난 다음 가능한 회의 넣기
				cnt++;
				cur = list[i];
			}
		}
		
		sb.append(cnt).append("\n");
	}
}

