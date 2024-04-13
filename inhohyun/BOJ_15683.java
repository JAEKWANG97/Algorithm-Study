
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15683 {
	static class xy {
		int x, y;

		xy(int x, int y) {
			this.x = x;
			this.y = y;

		}
	}

	static int n, m, map[][];
	static List<xy>[] cctv; // cctv의 위치
	static int min_place = 65;
	public static void main(String[] args) throws IOException {
		init(); // 입력 받기
		
		solution(); // cctv 감시 실행

	}

	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		// 원본 배열 입력
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cctv = new LinkedList[6];
		for(int i = 0; i<6; i++) {
			cctv[i] = new LinkedList<>();
		}
		
		// cctv의 위치 저장
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				switch (map[i][j]) {
				case 1:
					cctv[1].add(new xy(i, j));
					break;
				case 2:
					cctv[2].add(new xy(i, j));
					break;
				case 3:
					cctv[3].add(new xy(i, j));
					break;
				case 4:
					cctv[4].add(new xy(i, j));
					break;
				case 5:
					cctv[5].add(new xy(i, j));
					break;

				}
			}
		}
	}
	
	static void solution() {
		search1(map, 0);
		
		System.out.println(min_place);
	}
	
	// 1번 cctv부터 돌리기
	static void search1(int[][] map, int cnt) {
		if (cnt == cctv[1].size()) {
			search2(map, 0);
			return;
		}

		int[][] copy_map = new int[n][m];
		// 복사하기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				copy_map[i][j] = map[i][j];
			}
		}
		
		int x = cctv[1].get(cnt).x;
		int y = cctv[1].get(cnt).y;

		// 위
		searchUp(copy_map, x, y);
		search1(copy_map, cnt + 1);
		
		int[][] copy_map2 = new int[n][m];
		// 복사하기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				copy_map2[i][j] = map[i][j];
			}
		}
		
		// 아래
		searchDown(copy_map2, x, y);
		search1(copy_map2, cnt + 1);
		
		int[][] copy_map3 = new int[n][m];
		
		// 복사하기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				copy_map3[i][j] = map[i][j];
			}
		}
		
		// 왼
		searchLeft(copy_map3, x, y);
		search1(copy_map3, cnt + 1);
		
		int[][] copy_map4 = new int[n][m];
		// 복사하기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				copy_map4[i][j] = map[i][j];
			}
		}
		// 오른
		searchRight(copy_map4, x, y);
		search1(copy_map4, cnt + 1);
	}

	static void search2(int[][] map, int cnt) {
		if (cnt == cctv[2].size()) {
			search3(map, 0);
			return;
		}
		
		int[][] copy_map = new int[n][m];
		// 복사하기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				copy_map[i][j] = map[i][j];
			}
		}
		
		int x = cctv[2].get(cnt).x;
		int y = cctv[2].get(cnt).y;
		// 위, 아래
		searchUp(copy_map, x, y);
		searchDown(copy_map, x, y);
		search2(copy_map, cnt + 1);
		
		int[][] copy_map2 = new int[n][m];
		// 복사하기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				copy_map2[i][j] = map[i][j];
			}
		}
		//오른, 왼
		searchLeft(copy_map2, x, y);
		searchRight(copy_map2, x, y);
		search2(copy_map2, cnt + 1);		
		
		
		
		
	}
	static void search3(int[][] map, int cnt) {
		if (cnt == cctv[3].size()) {
			search4(map, 0);
			return;
		}
		
		int[][] copy_map = new int[n][m];
		// 복사하기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				copy_map[i][j] = map[i][j];
			}
		}
		
		int x = cctv[3].get(cnt).x;
		int y = cctv[3].get(cnt).y;
		
		// 위, 오른
		searchUp(copy_map, x, y);
		searchRight(copy_map, x, y);
		search3(copy_map, cnt + 1);
		
		int[][] copy_map2 = new int[n][m];
		// 복사하기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				copy_map2[i][j] = map[i][j];
			}
		}
		//위, 왼
		searchUp(copy_map2, x, y);
		searchLeft(copy_map2, x, y);
		search3(copy_map2, cnt + 1);		
		
		int[][] copy_map3 = new int[n][m];
		// 복사하기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				copy_map3[i][j] = map[i][j];
			}
		}
		//왼, 아래
		searchLeft(copy_map3, x, y);
		searchDown(copy_map3, x, y);
		search3(copy_map3, cnt + 1);
		
		int[][] copy_map4 = new int[n][m];
		// 복사하기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				copy_map4[i][j] = map[i][j];
			}
		}
		
		//아래, 오른
		searchRight(copy_map4, x, y);
		searchDown(copy_map4, x, y);
		search3(copy_map4, cnt + 1);
		
	}
	
	static void search4(int[][] map, int cnt) {
		if (cnt == cctv[4].size()) {
			search5(map, 0);
			return;
		}
		int[][] copy_map = new int[n][m];
		
		
		// 복사하기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				copy_map[i][j] = map[i][j];
			}
		}
		int x = cctv[4].get(cnt).x;
		int y = cctv[4].get(cnt).y;
		
		//아래 빼고 다
		searchRight(copy_map, x, y);
		searchUp(copy_map, x, y);
		searchLeft(copy_map, x, y);
		search4(copy_map, cnt + 1);
		
		int[][] copy_map2 = new int[n][m];
		
		
		// 복사하기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				copy_map2[i][j] = map[i][j];
			}
		}
		
		//오른 빼고 다
		searchUp(copy_map2, x, y);
		searchLeft(copy_map2, x, y);
		searchDown(copy_map2, x, y);
		search4(copy_map2, cnt + 1);
		
		int[][] copy_map3 = new int[n][m];
		
		
		// 복사하기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				copy_map3[i][j] = map[i][j];
			}
		}
		
		//위 빼고 다
		searchLeft(copy_map3, x, y);
		searchDown(copy_map3, x, y);
		searchRight(copy_map3, x, y);
		search4(copy_map3, cnt + 1);
		
		int[][] copy_map4 = new int[n][m];
		
		
		// 복사하기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				copy_map4[i][j] = map[i][j];
			}
		}
		
		
		//왼 빼고 다	
		searchUp(copy_map4, x, y);
		searchDown(copy_map4, x, y);
		searchRight(copy_map4, x, y);
		search4(copy_map4, cnt + 1);
		
	}
	
	//마지막 cctv
	static void search5(int[][] map, int cnt) {
		if (cnt == cctv[5].size()) {
			//마지막은 빈 구역 계산해주기
			min_place = Math.min(min_place, sumZero(map)); 
			return;
		}
		int[][] copy_map = new int[n][m];
		
		
		// 복사하기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				copy_map[i][j] = map[i][j];
			}
		}
		
		
		int x = cctv[5].get(cnt).x;
		int y = cctv[5].get(cnt).y;
		
		//모든 방향 탐색하기
		searchUp(copy_map, x, y);
		searchDown(copy_map, x, y);
		searchRight(copy_map, x, y);
		searchLeft(copy_map, x, y);
		search5(copy_map, cnt + 1);
	
	}

	// 위
	static void searchUp(int[][] arr, int x, int y) {
		for (int i = x - 1; i >= 0; i--) {
			if (arr[i][y] == 6) {
				break;
				
			} else if (arr[i][y] == 0) {
				arr[i][y] = -1;
			}
		}

	}

	// 아래
	static void searchDown(int[][] arr, int x, int y) {
		for (int i = x + 1; i < n; i++) {
			if (arr[i][y] == 6) {
				break;
			} else if (arr[i][y] == 0) {
				arr[i][y] = -1;
			}
		}
	}

	// 왼
	static void searchLeft(int[][] arr, int x, int y) {
		for (int i = y - 1; i >= 0; i--) {
			if (arr[x][i] == 6) {
				break;
			} else if (arr[x][i] == 0) {
				arr[x][i] = -1;
			}
		}
	}

	// 오른
	static void searchRight(int[][] arr, int x, int y) {
		for (int i = y + 1; i < m; i++) {
			if (arr[x][i] == 6) {
				break;
			} else if (arr[x][i] == 0) {
				arr[x][i] = -1;
			}
		}
	}
	
	//해당 배열의 0의 값을 리턴
	static int sumZero(int[][] arr) {
		int num = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j <m; j++) {
				if(arr[i][j] == 0)
				num += 1;
			}
		}
		
		return num;
	}

}
