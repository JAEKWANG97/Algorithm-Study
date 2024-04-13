import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1931 { //백준 1931 회의실 배정 - 25분
	
	private static class Time implements Comparable<Time>{
		int start, end;

		public Time(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Time o) {
			if (this.end == o.end) {
				return Integer.compare(this.start, o.start);
			}
			return Integer.compare(this.end, o.end);
		}
	}//Time
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int n;
	static Time[] timeTable;
	
	public static void main(String[] args) throws IOException {
		n = Integer.parseInt(br.readLine());
		timeTable = new Time[n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			timeTable[i] = new Time(s,e);
		}
		
		Arrays.sort(timeTable);
		int cnt = 1;
		int endTime = timeTable[0].end;
		for (int i = 1; i < timeTable.length; i++) {
			if (timeTable[i].start >= endTime) {
				cnt++;
				endTime = timeTable[i].end;
			}
		}
		
		System.out.println(cnt);
	}//main
}//class
