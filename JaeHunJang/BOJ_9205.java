
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 맥주 마시면서 걸어가기 - 60분
public class BOJ_9205 {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static Pos[] stores;
	static Pos start, end;
	static final String HAPPY = "happy\n", SAD = "sad\n";
	
	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + "]";
		}
	}
	
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	private static void init() throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
		StringTokenizer st;
		
		int x, y;
		for (int tc = 0; tc < t; tc++) {
			N = Integer.parseInt(br.readLine()); // 편의점 개수
			
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			start = new Pos(x, y); // 상근이네 집
			
			stores = new Pos[N]; // 편의점
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				stores[i] = new Pos(x, y);
			}
			
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			end = new Pos(x, y); // 페스티벌
			
			
			for (int i = 0; i < stores.length; i++) {
//				System.out.println(stores[i]);			
			}
			
			if (isMove(start, end)) {
				sb.append(HAPPY);
				continue;
			}
			
			solve();
		}
	}
	
	static void solve() throws Exception {
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(start);
		boolean[] visited = new boolean[N];
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			if (isMove(cur, end)) {
				sb.append(HAPPY);
				return;
			}
			
			for (int i = 0; i < N; i++) {
				if (visited[i]) continue;
				if (!isMove(cur, stores[i])) continue;
				
				visited[i] = true;
				q.offer(stores[i]);
			}
		}
		
		sb.append(SAD);
	}
	
	static boolean isMove(Pos s, Pos e) {
		return Math.ceil((Math.abs(e.x - s.x) + Math.abs(e.y - s.y)) / 50.0) <= 20;
	}
	
}
