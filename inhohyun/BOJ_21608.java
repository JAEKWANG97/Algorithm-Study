
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_21608 {
	static int n, map[][], students[], sum;
	static Map<Integer, Set<Integer>> preferences; // 좋아하는 학생 관리
	static int[] dx = { 0, 0, 1, -1 }, dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		init(); // 초기화


		solution();
		
		System.out.println(sum);

//		print(); // map 찍어보기
	}

	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		preferences = new HashMap<>();
		map = new int[n][n];

		
		students = new int[n *n];
		// 좋아하는 학생 저장
		for (int i = 0; i < n * n; i++) {
			st = new StringTokenizer(br.readLine());

			int tmp = Integer.parseInt(st.nextToken());
			
			
			students[i] = tmp;
			preferences.put(tmp, new HashSet());

			for (int j = 0; j < 4; j++) {
				preferences.get(tmp).add(Integer.parseInt(st.nextToken()));
			}

		}
		
		
	}

	
//	// 자리 배치하기
//	static void place() {
//		// target이 갈 수 있는 모든 좌표를 저장해두기
//
//		while (!keys.isEmpty()) {
//			List<Seat> canSeat = new LinkedList<>();
//			int target = keys.poll(); // 몇 번 학생을 배치할 것인지
//
//			// 앉을 수 있는 모든 좌석 저장하기
//			for (int i = 0; i < n; i++) {
//				for (int j = 0; j < n; j++) {
//					if (map[i][j] != 0)
//						continue; // 이미 사람이 있으면 건뛰
//
//					canSeat.add(new Seat(i, j, SPrefer(i, j, target), SEmpty(i, j)));
//				}
//			}
//			
//			Collections.sort(canSeat); // 조건대로 정렬
//			map[canSeat.get(0).x][canSeat.get(0).y] = target;
//
//		}
//
//	}
	
	// 앉을 좌석 찾기
	static Seat findSeat(int student) {
		Seat seat = null;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// 이미 자리에 누구 앉아있으면 skip
				if (map[i][j] > 0) {
					continue;
				}
				// 현재 자리의 정보 (x y 좌표, 인접한 좋아하는 학생 수, 빈칸 수)
				Seat cur = new Seat(i, j, SPrefer(i, j, student), SEmpty(i, j));
				// 비교할 seat이 null이라면 초기화 후 skip
				if (seat == null) {
					seat = cur;
					continue;
				}

				// 이전 좌석과 현재 좌석 비교
				if (seat.compareTo(cur) > 0) {
					seat = cur;
				}
			}
		}
		return seat;
	}

	// 근처의 좋아하는 학생 수를 리턴
	static int SPrefer(int x, int y, int target) {
		int cnt = 0;
		for (int d = 0; d < 4; d++) {
			int di = x + dx[d];
			int dj = y + dy[d];
			if (0 > di || di >= n || 0 > dj || dj >= n)
				continue;

			// 상하좌우가 좋아하는 학생에 포함되면 증가
			if (preferences.get(target).contains(map[di][dj])) {
				cnt++;
			}
		}

		return cnt;
		
	}

	// 근처의 빈 자리 수를 리턴
	static int SEmpty(int x, int y) {
		int cnt = 0;
		for (int d = 0; d < 4; d++) {
			int di = x + dx[d];
			int dj = y + dy[d];
			if (0 > di || di >= n || 0 > dj || dj >= n)
				continue;

			// 상하좌우가 빈칸이면
			if (map[di][dj] == 0) {
				cnt++;
			}
		}
		return cnt;

	}

	static class Seat implements Comparable<Seat> {
		int x, y, SumPrefer, SumEmpty; // 좌석의 좌표와 해당 자리의 좋아하는 학생 수, 빈 자리 수를 저장

		Seat(int x, int y, int SumPrefer, int SumEmpty) {
			this.x = x;
			this.y = y;
			this.SumPrefer = SumPrefer;
			this.SumEmpty = SumEmpty;

		}

		@Override
		public int compareTo(Seat o) {

			// 좋아하는 학생 우선
			if (SumPrefer != o.SumPrefer) {
				return -(SumPrefer - o.SumPrefer); // 큰 순으로 리턴
			}

			// 빈칸 순으로 우선
			if (SumEmpty != o.SumEmpty) {
				return -(SumEmpty - o.SumEmpty);
			}

			// 행 순
			if (x != o.x) {
				return -(x - o.x);
			}
			// 열 순
			return -(y - o.y);
		}

	}

	// 정답 구하기 -> 여기서 문제가 있었음
	static void solution() {
		// 1. 학생들 자리 배치
		for (int i = 0; i < students.length; i++) {
			Seat seat = findSeat(students[i]);
			map[seat.x][seat.y] = students[i];
		}

		// 2. 점수 합산
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// 주변 학생 수에 따라 점수 합산
				int count = SPrefer(i, j, map[i][j]);
				if (count > 0) {
					sum += (int) Math.pow(10, count - 1);
				}
			}
		}
	}
	
	// map 찍어보기
	static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

}
