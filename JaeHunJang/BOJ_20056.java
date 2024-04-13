import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 마법사 상어와 파이어볼 / 120분
public class BOJ_20056 {
	static StringBuilder sb = new StringBuilder();
	static int N, M, K, deltas[][] = {{-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}, {0,-1},{-1,-1}};
	static Queue<Fireball> fires, newFires;
	
	static class Fireball {
		int r, c, m, s, d;

		public Fireball(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Fireball [r=" + r + ", c=" + c + ", m=" + m + ", s=" + s + ", d=" + d + "]";
		}
	}
	
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	private static void init() throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		

		newFires = new ArrayDeque<>();
		fires = new ArrayDeque<>();

		
		int r,c,m,d,s;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			Fireball fb = new Fireball(r-1, c-1, m, s, d);

			fires.offer(fb);
		}
		
		solve();
	}
	
	static void solve() throws Exception {		
		for (int i = 0; i < K; i++) { // K번 명령
			int size = fires.size();
			List<Fireball> map[][] = new List[N][N];
			for (int j = 0; j < size; j++) { // 파이어볼 이동
				Fireball cur = fires.poll();

				cur.r = (N + cur.r + deltas[cur.d][0] * (cur.s%N)) % N;
				cur.c = (N + cur.c + deltas[cur.d][1] * (cur.s%N)) % N;

				if(map[cur.r][cur.c] == null) map[cur.r][cur.c] = new ArrayList<>();
				map[cur.r][cur.c].add(cur); // 이동한 위치에 파이어볼 개수 카운팅
				fires.offer(cur); // 파이어볼 다시 저장
			}

			for (int r = 0; r < N; r++) { // 2개 이상 있는지 전체 순회
				for (int c = 0; c < N; c++) {
					if (map[r][c] == null) continue;
					if (map[r][c].size() > 1) { // 같은 위치에 2개 이상 있으면
						Fireball sum = new Fireball(r, c, 0, 0, 0);
						int evenCnt = 0;
						int oddCnt = 0;

						for (Fireball cur : map[r][c]) {
							if (cur.r == r && cur.c == c) { // 겹치는 파이어 볼 합치기
								sum.m += cur.m;
								sum.s += cur.s;
								if (cur.d % 2 == 1) {
									oddCnt++;
								} else {
									evenCnt++;
								}
							}
							fires.remove(cur);
						}
						
						sum.m = sum.m / 5; // 질량 나누기
						if (sum.m == 0) continue; // 질량이 0이면 소멸
						sum.s = sum.s / map[r][c].size(); // 속력 나누기
						divideFire(evenCnt, oddCnt, sum);
					}
				}
			}
		}
		
		int sumM = 0;
		while(!fires.isEmpty()) {
			sumM += fires.poll().m;
		}
		sb.append(sumM);
	}
	
	static void divideFire(int evenCnt, int oddCnt, Fireball fb) {
		if (evenCnt > 0 && oddCnt > 0) {
			for (int i = 1; i < 8; i+=2) {
				fires.offer(new Fireball(fb.r, fb.c, fb.m, fb.s, i));
			}
		} else {
			for (int i = 0; i < 8; i+=2) {
				fires.offer(new Fireball(fb.r, fb.c, fb.m, fb.s, i));
			}
		}
	}
}

