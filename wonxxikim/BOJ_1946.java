

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class person implements Comparable<person>{
		int score1;
		int score2;
		public person(int score1, int score2) {
			super();
			this.score1 = score1;
			this.score2 = score2;
		}
		@Override
		public int compareTo(person o) {
			// TODO Auto-generated method stub
			return this.score1-o.score1;
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			person[] people = new person[N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int score1 = Integer.parseInt(st.nextToken());
				int score2 = Integer.parseInt(st.nextToken());
				people[i] = new person(score1,score2);
			}
			Arrays.sort(people);
			int fail = 0;
			int min = people[0].score2;
			for(int i = 1 ; i<N ; i++) {
				if(min<people[i].score2) {
					fail++;
				}
				if(min>people[i].score2) {
					min = people[i].score2;
				}
			}
			System.out.println(N-fail);
			
		}
	}

}
