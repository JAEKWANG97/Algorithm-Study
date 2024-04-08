import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 15685. 드래곤 커브 / 90분 
public class BOJ_15685 {
	static StringBuilder sb = new StringBuilder();
	static int N, deltas[][] = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
	static Queue<Dragon> dragons, lastDragons;
	static class Dragon {
		int r, c, d, age, maxAge;
		List<Pos> curve;
		List<Integer> dir;

		public Dragon(int r, int c, int d, int maxAge) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.maxAge = maxAge;
			age = 0;
			curve = new ArrayList<>();
			dir = new ArrayList<>();
		}
	}
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	static void init() throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 드래곤 커브 수
		dragons = new ArrayDeque<>(); // 성장시켜야할 드래곤
		lastDragons = new ArrayDeque<>(); // 성장을 끝낸 드래곤
		
		int r, c, d, age;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			c = Integer.parseInt(st.nextToken()) + 50;
			r = Integer.parseInt(st.nextToken())+ 50;
			d = Integer.parseInt(st.nextToken());
			age = Integer.parseInt(st.nextToken());
			Dragon dragon = new Dragon(r, c, d, age);
			
			dragon.curve.add(new Pos(r, c));
			dragon.dir.add(d);
			dragons.offer(dragon);
		}
		
		solve();
	}
	
	static void solve() throws Exception {
		while(!dragons.isEmpty()) {
			Dragon dragon = dragons.poll();
			
			if (dragon.age == dragon.maxAge) { // 성장을 다한 드래곤은 넘김
				lastDragons.offer(dragon);
				continue;
			}
			
			int size = dragon.dir.size();
			for (int i = size-1; i >= 0; i--) {
				dragon.dir.add((dragon.dir.get(i) + 1) % 4);
			}
			dragon.age++;
			dragons.offer(dragon);
		}
		
		int cnt = 0;
		int level = 1;
		int map[][] = new int[200][200];
		while(!lastDragons.isEmpty()) {
			Dragon dragon = lastDragons.poll();
//			System.out.println(dragon.dir);
			int r = dragon.r;
			int c = dragon.c;
			map[r][c] = level;
//			System.out.printf("%d, %d%n", r, c);
			for (int d : dragon.dir) {
				int nr = deltas[d][0] + r;
				int nc = deltas[d][1] + c;
//				System.out.printf("%d, %d%n", nr, nc);
				map[nr][nc] = level;
				r = nr;
				c = nc;
			}
			level++;
		}
		for (int i = 0; i < map.length-1; i++) {
			for (int j = 0; j < map[i].length-1; j++) {
				if (map[i][j] > 0 && map[i+1][j] > 0 && map[i][j+1] > 0 && map[i+1][j+1] > 0) {
//					printArr(map);
					cnt++;
				}
			}
		}
		
		sb.append(cnt);
	}
	
	static void printArr(int map[][]) {
		for (int i = 0; i < map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println();
	}
	
}