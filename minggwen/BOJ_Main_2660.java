import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Main_2660 {

	static class Member{
		List<Integer> friends = new ArrayList<>();
		public Member() {
		}
		@Override
		public String toString() {
			return "friends=" + friends + "]";
		}
		
	}
	static Member[] members;
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st  =null;
		N = Integer.parseInt(br.readLine());
		members = new Member[N+1];
		for(int n=1;n<=N;n++) {
			members[n] = new Member();
		}
		while(true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(a==-1&&b==-1) break;
			members[a].friends.add(b);
			members[b].friends.add(a);
		}
		int min = Integer.MAX_VALUE;
		List<Integer> min_members = new ArrayList<>();
		for(int idx =1;idx<=N;idx++) {
			int score = bfs(idx);
			if(score<min) {
				min = score;
				min_members.clear();
				min_members.add(idx);
			}else if(score==min) {
				min_members.add(idx);
			}
		}
		System.out.println(min+" "+min_members.size());
		for(int m : min_members) {
			System.out.print(m+" ");
		}
		
	}
	public static int bfs(int idx) {
		Queue<Integer> que = new ArrayDeque<>();
		boolean visited[] = new boolean[N+1];
		int k=0;
		for(int n:members[idx].friends) {
			visited[n]=true;
			que.add(n);
		}
		visited[idx] = true;
		while(!que.isEmpty()) {
			int size = que.size();
			k++;
			for(int s=0;s<size;s++) {
				int friend = que.poll();
				for(int ff: members[friend].friends) {
					if(visited[ff])continue;
					visited[ff] = true;
					que.add(ff);
				}
			}
		}
		return k;
	}

}
