import java.util.*;
import java.io.*;

public class BOJ_21609 {
	// 검정 블록, 무지개 블록, 빈 칸
	static int BLACK = -1, RAINBOW = 99, EMPTY = 0;
	static int N, M, sum;	// 격자 크기, 색상 개수, 점수 합
	static int[][] map;	// 격자
	static int[] dx = { -1, 0, 1, 0 }, dy = { 0, 1, 0, -1 };	// 상 우 하 좌
	static boolean[][] visited;	// 방문 배열

	public static void main(String[] args) throws Exception {
		init(); // 입력

		solution(); // 풀이

		printResult(); // 결과 출력
	}

	static void solution() {
		while (true) {
			// 1. 크기가 가장 큰 블록 크룹을 찾는다.
			Block standardBlock = findMaxBlockGroupe();
			// 더 이상 블록 그룹이 없는 경우
			if (standardBlock == null) {
				return;
			}

			// 점수 합산
			sum += standardBlock.sum * standardBlock.sum;

			// 2. 1에서 찾은 블록 그룹의 모든 블록을 제거한다.
			removeBlock(standardBlock);

			// 3. 격자에 중력이 작용한다.
			applyGravity();

			// 4. 격자가 90도 반시계 방향으로 회전한다.
			rotateMap();

			// 5. 다시 격자에 중력이 작용한다.
			applyGravity();
		}
	}

	static Block findMaxBlockGroupe() {
		visited = new boolean[N][N];
		Block maxBlock = new Block(0, 0, EMPTY, Integer.MIN_VALUE, Integer.MIN_VALUE);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 검정 블록이면 skip
				if (map[i][j] == BLACK) {
					continue;
				}
				// 무지개 블록이면 skip
				if (map[i][j] == RAINBOW) {
					continue;
				}
				// 비어있으면 skip
				if (map[i][j] == EMPTY) {
					continue;
				}
				// 이미 방문했으면 skip
				if (visited[i][j]) {
					continue;
				}

				// bfs 탐색 전에 무지개 블록만 방문 처리 초기화
				initRainBowVisited();
				
				Block cur = bfsBlock(i, j, map[i][j]);

				// 그룹 블록 수 2개보다 작으면 skip
				if (cur == null) {
					continue;
				}
				// 최대 블록 수 갱신
				if (maxBlock.compareBlock(cur)) {
					maxBlock = cur;
				}
			}
		}

		// 초기값 그대로라면 블록그룹 못찾았으므로 null return
		if (maxBlock.color == EMPTY) {
			return null;
		}
		return maxBlock;
	}

	// 무지개 블록만 방문 처리 초기화 -> 다른 색깔의 블록에서도 무지개 블록은 방문할 수 있으므로
	static void initRainBowVisited() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == RAINBOW) {
					visited[i][j] = false;
				}
			}
		}
	}

	// x, y 좌표부터 같은 color 블록 bfs 탐색
	static Block bfsBlock(int x, int y, int color) {
		Queue<Integer> qx = new LinkedList<>();
		Queue<Integer> qy = new LinkedList<>();
		qx.offer(x);
		qy.offer(y);
		visited[x][y] = true;
		int sum = 1;
		int rainbowSum = 0;

		while (!qx.isEmpty()) {
			int cx = qx.poll();
			int cy = qy.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];

				// 범위 나가면 skip
				if (outOfRange(nx, ny)) {
					continue;
				}
				// 검정 블록이면 skip
				if (map[nx][ny] == BLACK) {
					continue;
				}
				// 비어있으면 skip
				if (map[nx][ny] == EMPTY) {
					continue;
				}
				// 이미 방문했으면 skip
				if (visited[nx][ny]) {
					continue;
				}
				// color 다를때 무지개 블록일때만 넣기
				if (map[nx][ny] != color) {
					if (map[nx][ny] == RAINBOW) {
						rainbowSum++;
						sum++;
						visited[nx][ny] = true;
						qx.offer(nx);
						qy.offer(ny);
					}
					continue;
				}

				sum++;
				visited[nx][ny] = true;
				qx.offer(nx);
				qy.offer(ny);
			}
		}

		// 그룹에 속한 블록의 개수가 2보다 작으면 null 리턴
		if (sum < 2) {
			return null;
		} else {
			return new Block(x, y, color, sum, rainbowSum);
		}
	}

	// 범위 체크
	static boolean outOfRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}

	// 기준 블럭과 같은 그룹 모두 제거
	static void removeBlock(Block block) {
		Queue<Integer> qx = new LinkedList<>();
		Queue<Integer> qy = new LinkedList<>();
		visited = new boolean[N][N];
		qx.offer(block.x);
		qy.offer(block.y);
		visited[block.x][block.y] = true;
		map[block.x][block.y] = EMPTY;

		while (!qx.isEmpty()) {
			int cx = qx.poll();
			int cy = qy.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				// 범위 나가면 skip
				if (outOfRange(nx, ny)) {
					continue;
				}
				// 검정 블록이면 skip
				if (map[nx][ny] == BLACK) {
					continue;
				}
				// 비어있으면 skip
				if (map[nx][ny] == EMPTY) {
					continue;
				}
				// 이미 방문했으면 skip
				if (visited[nx][ny]) {
					continue;
				}
				// color 다를때 무지개 블록일때만 넣기
				if (map[nx][ny] != block.color) {
					if (map[nx][ny] == RAINBOW) {
						map[nx][ny] = EMPTY;
						visited[nx][ny] = true;
						qx.offer(nx);
						qy.offer(ny);
					}
					continue;
				}

				map[nx][ny] = EMPTY;
				visited[nx][ny] = true;
				qx.offer(nx);
				qy.offer(ny);
			}
		}
	}

	// 중력 작용
	static void applyGravity() {
		for (int i = 0; i < N; i++) {
			for (int j = N - 2; j >= 0; j--) {
				if (map[j][i] == BLACK) {
					continue;
				}
				if (map[j][i] == EMPTY) {
					continue;
				}
				moveBlock(j, i);
			}
		}
	}

	// 블록 한개씩 옮기기
	static void moveBlock(int x, int y) {
		int cx = x;
		while (true) {
			cx++;
			// 범위 벗어나면 break
			if (cx >= N) {
				break;
			}
			// 검정 블록이면 break;
			if (map[cx][y] == BLACK) {
				break;
			}
			// 빈 칸 아니면 break;
			if (map[cx][y] != EMPTY) {
				break;
			}
		}
		cx--;

		// 안움직였으면 return
		if (cx == x) {
			return;
		}

		map[cx][y] = map[x][y];
		map[x][y] = EMPTY;
	}

	// 90도 반시계 방향으로 격자 회전
	static void rotateMap() {
		int[][] map_copy = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map_copy[i][j] = map[j][N - i - 1];
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = map_copy[i][j];
			}
		}
	}

	// 결과 출력
	static void printResult() {
		System.out.println(sum);
	}

	// 입력
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sum = 0;
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) {
					map[i][j] = BLACK;
				} else if (map[i][j] == 0) {
					map[i][j] = RAINBOW;
				}
			}
		}
	}

	// 블록 클래스
	static class Block {
		// 기준 블록의 x y 좌표, 색상, 블록그룹의 총 블록 수, 블록그룹에 속한 무지개 블록 수
		int x, y, color, sum, rainbowSum;

		public Block(int x, int y, int color, int sum, int rainbowSum) {
			this.x = x;
			this.y = y;
			this.color = color;
			this.sum = sum;
			this.rainbowSum = rainbowSum;
		}

		public boolean compareBlock(Block other) {
			// 나보다 other이 max이면 true

			// 블록 수로 비교
			if (this.sum != other.sum)
				return this.sum < other.sum;

			// 무지개 블록 수로 비교
			if (this.rainbowSum != other.rainbowSum)
				return this.rainbowSum < other.rainbowSum;

			// 행으로 비교
			if (this.x != other.x)
				return this.x < other.x;

			// 행으로 비교
			return this.y < other.y;
		}
	}
}
