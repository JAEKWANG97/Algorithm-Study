import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static class computer implements Comparable<computer>{
		int com;
		int time;
		public computer(int com, int time) {
			super();
			this.com = com;
			this.time = time;
		}
		@Override
		public int compareTo(computer o) {
			// TODO Auto-generated method stub
			return this.time-o.time;
		}
		
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc= 1 ; tc<=T ; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			ArrayList<computer>[] list = new ArrayList[n+1];
			for(int i =0 ; i<=n ; i++) {
				list[i] = new ArrayList<computer>(); 
			}
			for(int i = 0 ; i<d ; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				list[b].add(new computer(a,s));
			}
			PriorityQueue<computer> pq = new PriorityQueue<>();
			pq.add(new computer(c,0));
			boolean[] visit = new boolean[n+1];
			int cnt = 0 ; 
			int time = 0;
			while(!pq.isEmpty()) {
				computer cur = pq.poll();
				if(visit[cur.com]) continue;
				visit[cur.com] = true;
				cnt++;
				time = cur.time;
				for(computer next : list[cur.com]) {
					if(!visit[next.com]) {
						pq.add(new computer(next.com,next.time+time));
					}
				}
			}
			System.out.println(cnt+" "+time);
		}
	}

}
