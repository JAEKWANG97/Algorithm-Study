import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static ArrayList<Integer>[] list;
	static class candidate implements Comparable<candidate>{
		int idx;
		int score;
		public candidate(int idx, int score) {
			this.idx = idx;
			this.score = score;
		}

		@Override
		public int compareTo(candidate o) {
			// TODO Auto-generated method stub
			return this.score-o.score;
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 회원 수
		list = new ArrayList[N+1];
		for(int i = 1 ; i<=N ; i++) {
			list[i] = new ArrayList<>();
		}
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if(x==-1 && y==-1) break;
			list[x].add(y);
			list[y].add(x);
		}
		candidate[] candidates = new candidate[N+1];
		candidates[0]= new candidate(0,Integer.MAX_VALUE);
		for(int i = 1; i<=N; i++) {
			candidates[i] = new candidate(i,score(i));
		}
		Arrays.sort(candidates);
		int result_score = candidates[0].score;
		int result_count = 0;
		for(int i = 0 ; i<=N ; i++) {
			if(candidates[i].score==result_score) {
				result_count++;
			}
			else break;
		}
		int[] result_idx  = new int[result_count];
		for(int i = 0 ; i<result_count; i++) {
			result_idx[i] = candidates[i].idx;
		}
		Arrays.sort(result_idx);
		System.out.println((result_score-1)+" "+result_count);
		for(int i : result_idx) {
			System.out.print(i+" ");
		}


	}
	public static int score(int num) {
		int[] visit = new int[N+1];
		int result = 0;
		Queue<Integer> q = new LinkedList<>();
		q.add(num);
		visit[num] = 1;
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int n : list[cur]) {
				if(visit[n]==0) {
					visit[n]=visit[cur]+1;
					result = Math.max(result, visit[n]);
					q.add(n);
				}
			}
		}
		return result;
	}


}
