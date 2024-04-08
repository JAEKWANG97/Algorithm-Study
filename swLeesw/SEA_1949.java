import java.io.*;
import java.util.*;


public class SEA_1949 {
	
	static class Pos {
		int y, x;
		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static int t, n, k, arr[][], sol;
	static ArrayList<Pos> list;
	static boolean visited[][];
	static int dx[] = {0, 0, 1, -1};
	static int dy[] = {1, -1, 0, 0};
	
	static boolean isRange(int y, int x) {
		if (x >= 0 && y >= 0 && x < n && y < n) return true;
		else return false;
	}
	
	static void dfs(int sy, int sx, int depth, boolean bCheck) { //bCheck : 벽 깎기 체크
		visited[sy][sx] = true;
		sol = Math.max(sol, depth); //값 갱신
		for (int i = 0; i < 4; i++) {
			int ny = sy + dy[i];
			int nx = sx + dx[i];
			if (!isRange(ny, nx)) continue; //범위 밖이면 다음으로
			if (visited[ny][nx]) continue;
			if (arr[sy][sx] > arr[ny][nx]) { //낮은 곳으로 내려가는거면
				dfs(ny, nx, depth + 1, bCheck);
			} else if (!bCheck){ //같거나 다음이 더 높으면 && 부술 힘이 남아 있으면
				if (arr[ny][nx] - k < arr[sy][sx]) {//k만큼 깎은게 더 낮으면
					int tmp = arr[ny][nx];//이전 값 저장
					arr[ny][nx] = arr[sy][sx] - 1;//넣기
					dfs(ny, nx, depth + 1, true);
					arr[ny][nx] = tmp; //이전 값 복구
				}
			}
		}
		visited[sy][sx] = false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		t = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= t; tc++) {
			int maxValue = 0;
			st = new StringTokenizer(br.readLine());
			sol = 0;
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			arr = new int[n][n];
			visited = new boolean[n][n];
			list = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					maxValue = Math.max(maxValue, arr[i][j]);
				}
 			}
			//높은 봉우리 리스트 만들기
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (maxValue == arr[i][j]) {
						list.add(new Pos(i, j));
					}
				}
			}
			//초기화
			//변형 dfs 백트래킹
			for (int i = 0; i < list.size(); i++) {
				dfs(list.get(i).y, list.get(i).x, 1, false);
			}

			
			System.out.println("#" + tc + " " + sol);
		}
		
		
	}
	
}
