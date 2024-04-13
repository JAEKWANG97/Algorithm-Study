import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15683 { //백준 15683 감시 - 100분
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int n, m, map[][], direction[], newMap[][], cctvNum, d, min;
	static ArrayList<Integer> cctv = new ArrayList<>();
	static ArrayList<int[]> cctvLoca = new ArrayList<>();
	static int[][] directionByDX = {
			{},{-1},{0},{1},{0},
			{-1,1},{0,0},
			{-1,0},{0,1},{1,0},{0,-1},
			{-1,0,1},{0,1,0},{1,0,-1},{0,-1,0},
			{-1,0,1,0}
	};
	static int[][] directionByDY = {
			{},{0},{1},{0},{-1},
			{0,0},{-1,1},
			{0,1},{1,0},{0,-1},{-1,0},
			{0,1,0},{1,0,-1},{0,-1,0},{-1,0,1},
			{0,1,0,-1}
	};

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		min = Integer.MAX_VALUE;

		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j]!=0 && map[i][j]!=6) {
					cctv.add(map[i][j]);
					cctvLoca.add(new int[] {i, j});
				}
			}
		}
		direction = new int[cctv.size()];

		getDir(0);
		
		System.out.println(min);

	}//end main



	private static void getDir(int cnt) {
		if (cnt == cctv.size()) {
			//이제 direction을 이용해서 map배열을 수정하기
			newMap = new int[n][m];
			copyMapToNewMap();
//			System.out.println(Arrays.toString(direction));

			int cctvCnt = cctv.size();
			for (int i = 0; i < cctvCnt; i++) {
				cctvNum = cctv.get(i); //cctv 번호
				d = direction[i]; //방향 (1~15)

				int x = cctvLoca.get(i)[0]; //i번째 cctv의 x좌표
				int y = cctvLoca.get(i)[1]; //i번째 cctv의 y좌표

				//x,y에서 d방향으로 광선 쭉 뻗으면 됨
				
				for (int D = 0; D < directionByDX[d].length; D++) {
					int mul = 1;
					while (true) {
						int nx = x + directionByDX[d][D]*mul;
						int ny = y + directionByDY[d][D]*mul;
						
						if (0<=nx&&nx<n && 0<=ny&&ny<m) {
							if (newMap[nx][ny] != 6) {
								
								newMap[nx][ny] = -1;
								mul++;
								continue;
							}
						}
						break;
					}
				}
			}
//			for (int[] i : newMap) {
//				System.out.println(Arrays.toString(i));
//			}
			
			//newMap에서 0의 갯수를 세면 됨
			int zero = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (newMap[i][j]==0) {
						zero++;
					}
				}
			}
//			System.out.println(zero);
//			System.out.println();
			min = Math.min(min, zero); //최솟값 교체

			return;
		}

		if (cctv.get(cnt)==2) {
			for (int i = 5; i <= 6; i++) {
				direction[cnt] = i;
				getDir(cnt+1);
			}
		} else if (cctv.get(cnt)==1) {
			for (int i = 1; i <= 4; i++) {
				direction[cnt] = i;
				getDir(cnt+1);
			}
		} else if (cctv.get(cnt)==3) {
			for (int i = 7; i <= 10; i++) {
				direction[cnt] = i;
				getDir(cnt+1);
			}
		} else if (cctv.get(cnt)==4) {
			for (int i = 11; i <= 14; i++) {
				direction[cnt] = i;
				getDir(cnt+1);
			}
		} else if (cctv.get(cnt)==5) {
			direction[cnt] = 15;
			getDir(cnt+1);
		}
	}//end getDir

	//map 깊은복사 -> copyMap
	private static void copyMapToNewMap() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				newMap[i][j] = map[i][j];
			}
		}
	}//end copyMap

}//end class
