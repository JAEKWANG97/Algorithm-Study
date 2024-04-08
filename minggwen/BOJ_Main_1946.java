import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Main_1946 {

	static class Applicant implements Comparable<Applicant>{
		int interview, score;
		public Applicant() {};
		public Applicant(int interview, int score) {
			this.interview = interview;
			this.score = score;
		}

		@Override
		public int compareTo(Applicant o) {
			return this.interview-o.interview;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			int N = Integer.parseInt(br.readLine());
			Queue<Applicant> que = new PriorityQueue<>();
			for(int n=1;n<=N;n++) {
				st = new StringTokenizer(br.readLine());
				int score = Integer.parseInt(st.nextToken());
				int interview = Integer.parseInt(st.nextToken());
				que.add(new Applicant(interview,score));
			}
			int result = 0;
			int min = Integer.MAX_VALUE;
			while(!que.isEmpty()) {
				Applicant a = que.poll();
				if(a.score<min) {
					result++;
					min = a.score;
				}
			}
			System.out.println(result);
		}
	}

}

