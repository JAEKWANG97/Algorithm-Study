
import java.util.*;
import java.io.*;

public class BOJ_21608 {
	static int[][] G;
	static boolean[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		G = new int[N + 1][N + 1];
		map = new boolean[N * N + 1][N * N + 1]; // 인접 리스트
		int[] di = new int[] { 0, 0, -1, 1 };
		int[] dj = new int[] { -1, 1, 0, 0 };

		for (int n = 1; n < N * N + 1; n++) {
			st = new StringTokenizer(br.readLine(), " ");

			int curStudent = Integer.parseInt(st.nextToken());
			Set<Integer> likes = new HashSet<>();
			for (int m = 0; m < 4; m++) {
				int like = Integer.parseInt(st.nextToken());
				likes.add(like);
				map[curStudent][like] = true;
			}

			// 1.비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸
			Node node = new Node(0, 0, 0);
			int maxLike = Integer.MIN_VALUE;
			int maxEmpty = Integer.MIN_VALUE;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (G[i][j] != 0) {
						continue;
					}
					int likeCnt = 0, emptyCnt = 0;
					// 상하좌우에 좋아하는 학생 인접 칸 수 확인
					for (int k = 0; k < 4; k++) {
						int ni = i + di[k];
						int nj = j + dj[k];
						if (ni < 1 || ni > N || nj < 1 || nj > N) {
							continue;
						}
						if (likes.contains(G[ni][nj])) {
							likeCnt += 1;
						}
						if (G[ni][nj] == 0) {
							emptyCnt += 1;
						}

					}
					// 학생수 같으면 큐에 삽입 or 더 크면 큐 변경
					if (likeCnt > maxLike) {
						node = new Node(i, j, emptyCnt);
						maxLike = likeCnt;
						maxEmpty = emptyCnt;
					} else if (likeCnt == maxLike && emptyCnt > maxEmpty) {
						node = new Node(i, j, emptyCnt);
						maxEmpty = emptyCnt;
					}
				}
			}

			G[node.i][node.j] = curStudent;
		}
		// 결과 출력
		int result = 0;
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				result += calculate(i, j);
			}
		}
		System.out.println(result);
	}

	private static int calculate(int y, int x) {
		int cnt = 0, cur = G[y][x];
		int[] result = new int[] { 0, 1, 10, 100, 1000 };
		int[][] d = new int[][] { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 }, };
		
		for (int i = 0; i < 4; i++) {
			int ny = y + d[i][0];
			int nx = x + d[i][1];
			if (ny < 1 || ny >= G.length || nx < 1 || nx >= G.length) {
				continue;
			}
			if (map[cur][G[ny][nx]]) {
				cnt += 1;
			}
		}
		return result[cnt];
	}
}

class Node {
	int i, j, cnt;

	public Node(int i, int j, int cnt) {
		super();
		this.i = i;
		this.j = j;
		this.cnt = cnt;
	}
	
}