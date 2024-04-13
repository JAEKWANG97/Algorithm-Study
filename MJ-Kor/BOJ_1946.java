package algorithm_study.day_04_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1946 {
	
	private static class Grade implements Comparable<Grade>{
		int paper, interview;

		public Grade(int paper, int interview) {
			super();
			this.paper = paper;
			this.interview = interview;
		}
		@Override
		public String toString() {
			return "Grade [paper=" + paper + ", interview=" + interview + "]";
		}
		@Override
		public int compareTo(Grade o) {
			return this.paper - o.paper;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			int cnt = 1;
			
			Grade[] grades = new Grade[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				grades[i] = new Grade(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			Arrays.sort(grades);
			
			int min = grades[0].interview;
			
			for (int i = 1; i < N; i++) {
				if(min < grades[i].interview) continue;
				cnt++;
				min = grades[i].interview;
			}
			
			System.out.println(cnt);
		}
	}
}
