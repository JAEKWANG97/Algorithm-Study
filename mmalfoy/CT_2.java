import java.io.*;
import java.util.*;

public class CT_2 {
	static int N, M;
	static Map<Integer, Rabbit> map = new HashMap<Integer, Rabbit>();
	static List<Rabbit> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Q = Integer.parseInt(br.readLine());
		// 명령어 100
		StringTokenizer st = new StringTokenizer(br.readLine());
		st.nextToken();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());

		for (int p = 0; p < P; p++) {
			int pid = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			Rabbit rbt = new Rabbit(pid, d, 0, 0, 1, 1);
			list.add(rbt);
			map.put(pid, rbt);
		}

		int[][] d = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

		// 명령어 200, 300만 받음
		for (int q = 0; q < Q - 2; q++) {
			st = new StringTokenizer(br.readLine());
			if (Integer.parseInt(st.nextToken()) == 300) {
				int pid = Integer.parseInt(st.nextToken());
				int L = Integer.parseInt(st.nextToken());
				map.get(pid).dist *= L;
			} else {
				int K = Integer.parseInt(st.nextToken());
				int S = Integer.parseInt(st.nextToken());
				Map<Integer, Integer> selected = new HashMap<>();
				for (int k = 0; k < K; k++) {
					// 1. 우선순위 계산
					Collections.sort(list);
					Rabbit rbt = list.get(0);
					rbt.cnt += 1;
					selected.put(rbt.id, 1);

					// 2. 우좌하상 이동
					int maxY = 0, maxX = 0, maxXY = 0;
					for (int i = 0; i < 4; i++) {
						int ny = rbt.y;
						int nx = rbt.x;
						int dy = d[i][0];
						int dx = d[i][1];
						for (int jmp = 0; jmp < rbt.dist; jmp++) {
							ny += dy;
							nx += dx;
							// 칸 벗어나면 반댓방향으로
							if (ny < 1 || ny > N || nx < 1 || nx > M) {
								dy *= -1;
								dx *= -1;
								ny += dy * 2;
								nx += dx * 2;
							}
						}


						if (ny + nx > maxXY) {
							maxY = ny;
							maxX = nx;
							maxXY = maxX + maxY;
						} else if (ny + nx == maxXY) {
							if (ny > maxY) {
								maxY = ny;
								maxX = nx;
							} else if (ny == maxY && nx > maxX) {
								maxX = nx;
							}
						}
					}
					// 3. 4칸중 우선순위 칸으로 이동
					rbt.y = maxY;
					rbt.x = maxX;
					// 4. 나머지 토끼 점수 += maxXY
					for (int i = 1; i < list.size(); i++) {
						list.get(i).score += maxXY;
					}
					
				}
				// 5. K번 시행 후, 우선순위 높은 토끼에 점수 S
				Rabbit maxRabbit = null;
				int maxScore = 0;
				for (Rabbit rbt : list) {
					if (selected.containsKey(rbt.id) && rbt.score > maxScore) {
						maxRabbit = rbt;
						maxScore = rbt.score;
					}
				}
				maxRabbit.score += S;
			}
		}

		// 명령어 400, MAX(모든 토끼의 경주중 얻은 점수) 출력
		int result = 0;
		for (Rabbit rbt : list) {
			if (rbt.cnt != 0 && rbt.score > result) {
				result = rbt.score;
			}
		}
		System.out.println(result);
	}
}

class Rabbit implements Comparable<Rabbit> {
	int id, dist, cnt, score, y, x;

	public Rabbit(int id, int dist, int cnt, int score, int y, int x) {
		this.id = id;
		this.dist = dist;
		this.cnt = cnt;
		this.score = score;
		this.y = y;
		this.x = x;
	}

	@Override
	public int compareTo(Rabbit o) {
		int cmp = Integer.compare(cnt, o.cnt);
		if (cmp == 0) {
			cmp = Integer.compare(y + x, o.y + o.x);
		}
		if (cmp == 0) {
			cmp = Integer.compare(y, o.y);
		}
		if (cmp == 0) {
			cmp = Integer.compare(x, o.x);
		}
		if (cmp == 0) {
			cmp = Integer.compare(id, o.id);
		}
		return cmp;
	}

}