import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1697 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, K, minTime;
	
	public static void main(String[] args) throws Exception {
		init();
		solve();
		print();
	}
	static void print() {
		System.out.println(sb.toString());
	}
	
	static void init() throws Exception { 
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	}
	
	static void solve() throws Exception {
		bfs();
	}
	
	static void bfs() {
		int visited[] = new int[100001];
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(N);
		
		int time = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				int n = q.poll();
				if (n == K) {
					sb.append(time).append("\n");
					return;
				}
				
				if (n < 0 || n > 100000) continue;
				if (visited[n] == 1) continue;
				visited[n] = 1;
				
				if (n > K) {
					q.offer(n-1);
				} else {
					q.offer(n+1);
					q.offer(n-1);
					q.offer(n*2);
				}
			}
			time++;
		}
	}
}