import java.io.*;
import java.util.*;


public class BOJ_21609 {
	
	static class Pos {
		int y, x;

		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
	}
	
	static class Group {
		int sy, sx, groupCnt, rainbowCnt;

		public Group(int sy, int sx, int groupCnt, int rainbowCnt) {
			this.sy = sy;
			this.sx = sx;
			this.groupCnt = groupCnt;
			this.rainbowCnt = rainbowCnt;
		}
		
		
		
		
	}
	
	static int n, m, arr[][], sol;
	static int dy[] = {0, 1, 0, -1};
	static int dx[] = {1, 0, -1, 0};
	
	static boolean isRange(int y, int x) {
		return y >= 0 && x >= 0 && y < n && x < n;
	}
	
	//검정 블럭 -1, 무지개 블럭 0, 나머지는 m 이하의 자연수의 색
	//블럭의 색은 모두 같아야함 검은 블럭 포함x 무지개는 포함 가능
	//그룹에 속한 블록의 개수는 2 이상
	//기준 : 무지개 블록이 아닌 블록 중에서 행, 열의 번호가 가장 작은 블록
	/*
	 * 게임
	 * 1. 크기가 가장 큰 블록 찾기 -> 여러개면 무지개 블록이 가장 많은 블록 -> 기준 블록의 행이 가장 큰 것 -> 열이 가장 큰 것
	 * 2.해당 블록 그룹의 모든 블록 다 제거
	 * 3. 중력 -> 반시계 90도 -> 중력
	 * * 중력 : 검은색 블록을 제외한 모든 블록이 행의 번호가 큰 칸으로 이동(이래로 이동)
	 */
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while (true) {
			int score = popGroup();
			if (score == -1) {
				break;
			}
			sol += score;
//			System.out.println("---------------");
//			for (int[] i : arr) {
//				for (int j : i) {
//					System.out.print(j + " ");
//				}
//				System.out.println();
//			}
//			System.out.println(sol);
			gravity();
			turnArray();
			gravity();			
//			System.out.println("---------------");
//			for (int[] i : arr) {
//				for (int j : i) {
//					System.out.print(j + " ");
//				}
//				System.out.println();
//			}
//			System.out.println(sol);
		}
		
		System.out.println(sol);
		
		
	}
	
	static int popGroup() {
		PriorityQueue<Group> pq = new PriorityQueue<>((o1, o2) -> {
			
			if (o1.groupCnt != o2.groupCnt) {
				return o2.groupCnt - o1.groupCnt;
			}
			if (o1.rainbowCnt != o2.rainbowCnt) {
				return o2.rainbowCnt - o1.rainbowCnt;
			}
			if (o1.sy != o2.sy) {
				return o2.sy - o1.sy;
			}
			return o2.sx - o1.sx;
		}); //그룹 우선순위
		//연결요소 만들고 그룹 우선순위 정하기
		Queue<Pos> que = new ArrayDeque<>();
		
		int connectedCnt = 0;
		int visited[][] = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (arr[i][j] >= 1 && arr[i][j] <= m && visited[i][j] == 0) { //1 ~ 9 && 방문 x
					int gCnt = 1; //그룹에 있는 얘들 개수
					int rCnt = 0; //무지개 개수
					connectedCnt++;
					que.offer(new Pos(i, j));
					visited[i][j] = connectedCnt;
					boolean inVisited[][] = new boolean[n][n];
					inVisited[i][j] = true;
					while (!que.isEmpty()) {
						Pos cur = que.poll();
						
						for (int k = 0; k < 4; k++) {
							int ny = cur.y + dy[k];
							int nx = cur.x + dx[k];
							
							if (!isRange(ny, nx) || inVisited[ny][nx]) {
								continue;
							}
							
							if (arr[ny][nx] == 0) { //0일 때
								rCnt++;
								gCnt++;
								inVisited[ny][nx] = true;
								que.offer(new Pos(ny, nx));
								
								
							} else if (arr[ny][nx] == arr[i][j]) { //같을 떄
								gCnt++;
								que.offer(new Pos(ny, nx));
								inVisited[ny][nx] = true;
								visited[ny][nx] = connectedCnt;
							}
							
							
						}
						
					}
					pq.offer(new Group(i, j, gCnt, rCnt));
				}
			}
		}
		//끝
		//우선순위 제일 높은거 제거
		
		if (pq.isEmpty()) return -1;
		if (pq.peek().groupCnt < 2) { //그룹의 총 개수가 1 이하이면
			return -1; //종료
		}
		
		
		
		Group rGroup = pq.poll();
		int color = arr[rGroup.sy][rGroup.sx];
		
		que.offer(new Pos(rGroup.sy, rGroup.sx));
		arr[rGroup.sy][rGroup.sx] = -2;
		
		
		
		while(!que.isEmpty()) {
			Pos cur = que.poll();
			
			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if (!isRange(ny, nx)) continue;
				
				if (arr[ny][nx] == 0 || arr[ny][nx] == color) {
					que.offer(new Pos(ny, nx));
					arr[ny][nx] = -2;
				}
				
			}
			
		}
		
		return rGroup.groupCnt * rGroup.groupCnt;
	}
	
	
	
	
	static void turnArray() {//배열 돌리기
		int tArr[][] = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				tArr[i][j] = arr[i][j];
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = tArr[j][n - i - 1];
			}
		}
	}
	//제거 블록 -2로 표현하자, 검은색은 -1
		static void gravity() { //중력 작용
			
			for (int j= 0; j < n; j++) { //열
				for (int i = n - 1; i >= 1; i--) { //행 아래부터 위로
					if (arr[i][j] == -2 && arr[i - 1][j] != -1) {// 비어있는 곳이면
						boolean flag = false;
						for (int k = i - 1; k >= 0; k--) {
							//-1인지 체크
							if (arr[k][j] == -1) {
								break;
							}
							//블럭인지 체크
							if (arr[k][j] != -2) {
								flag = true;
								break;
							}
						}
						if (flag) { //블럭 존재하면
							remove(i, j);
							i++;
						}
					}
				}
			}
		}
		
		static void remove(int y, int x) {
			boolean flag = false;
			for (int i = y; i >= 1; i--) {
				if (arr[i - 1][x] == -1) {//위의 블럭이 검정이면
					arr[i][x] = -2;
					flag = true;
					break;
				}
				arr[i][x] = arr[i - 1][x];
			}
			
			if (!flag) {
				arr[0][x] = -2;
			}
		}
	
	
	
	
}
