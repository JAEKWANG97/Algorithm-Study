import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

// 감소하는 수 - 40분
public class BOJ_1038 {
	static StringBuilder sb = new StringBuilder();
	static int N;
	
	static class Num {
		long num, last;

		public Num(long cur, long last) {
			this.num = cur;
			this.last = last;
		}
	}
	
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	private static void init() throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		solve();
	}
	
	private static void solve() throws Exception {
		bfs();
	}
	
	private static void bfs() {
		Queue<Num> q = new ArrayDeque<>();
		for (long i = 0; i < 10; i++) {
			q.offer(new Num(i, i));
		}
		
		int cnt = 0;
		while(!q.isEmpty()) {
			Num cur = q.poll();
			
			if (cnt == N) { // N번째 수 출력
				sb.append(cur.num);
				return;
			}
			cnt++;
			for (int i = 0; i < cur.last; i++) {
				q.offer(new Num(cur.num * 10 + i, i));
			}
			
		}
		
		sb.append(-1); // N번째 감소하는 수를 찾지 못하면 -1 출력
		
	}
}
