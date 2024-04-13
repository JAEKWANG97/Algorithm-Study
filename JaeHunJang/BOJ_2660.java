import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 2660. 회장뽑기 / 50분 
public class BOJ_2660 {
	static StringBuilder sb = new StringBuilder();
	static int N, scores[];
	static List<Integer> list[];
	
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	static void init() throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		list = new List[N+1];
		scores = new int[N+1];
		
		
		int from, to;
		while (true) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			
			if(from == -1 && to == -1) break;
			
			if (list[from] == null) list[from] = new ArrayList<>();
			list[from].add(to);
			
			if (list[to] == null) list[to] = new ArrayList<>();
			list[to].add(from);
		}
		
		solve();
	}

	static void solve() {
		int score = N;
		
		for (int i = 1; i <= N; i++) {
			Queue<Integer> q = new ArrayDeque<>();
			q.offer(i);
			boolean visited[] = new boolean[N+1];
			visited[i] = true;
			
			int time = -1;
			while(!q.isEmpty()) {
				int size = q.size();
				time++;
				for (int s = 0; s < size; s++) {
					int cur = q.poll();
					
					if (list[cur] == null) continue;
					for(int to : list[cur]) {
						if (visited[to]) continue;
						visited[to] = true;
						q.offer(to);
					}
				}
			}
			
			scores[i] = time;
			score = Math.min(score, time);
		}
		
		
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			if (scores[i] == score) {
				sb.append(i).append(" ");
				cnt++;
			}
		}
		
		sb.insert(0, score + " " + cnt + "\n");
	}
}