package algorithm_study.day_03_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1931 {

	static class Meeting implements Comparable<Meeting>{
		int start;
		int end;
		
		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Meeting o) {
			return this.end!= o.end ? this.end - o.end : this.start - o.start;
		}


	}
	
	private static int N;
	private static Meeting[] meetings;
	private static List<Meeting> list = new ArrayList<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		meetings = new Meeting[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			meetings[i] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(meetings);
		
		list.add(meetings[0]);
		for (int i = 1; i < N; i++) {
			if(list.get(list.size() - 1).end <= meetings[i].start) {
				list.add(meetings[i]);
			}
		}
		
		System.out.println(list.size());
	}

}
