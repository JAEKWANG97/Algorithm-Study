package algorithm_study.day_03_28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15685 {
	
	private static class Point{
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}	
	}
	
	private static int[] dx = {1, 0, -1, 0};
	private static int[] dy = {0, -1, 0, 1};
	private static int[] sx = {1, 0, 1};
	private static int[] sy = {0, 1, 1};
	
	private static int[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		map = new int[101][101];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			drawDragonCurve(x, y, d, g);
		}
		
//		for (int i = 0; i <=100; i++) {
//			for (int j = 0; j <=100; j++) {
//				System.out.print(map[j][i] + " ");
//			}
//			System.out.println();
//		}
		
		int result = countSqure();
		System.out.println(result);
		
	}

	private static int countSqure() {
		int cnt = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if(map[j][i] == 1) {
					boolean flag = true;
					for (int k = 0; k < 3; k++) {
						if(map[j + sy[k]][i + sx[k]] != 1) {
							flag = false;
							break;
						}
					}
					if(flag) cnt++;
				}
			}
		}
		return cnt;
	}

	private static void drawDragonCurve(int x, int y, int d, int g) {
		// 드래곤 커브의 세대: 리스트의 좌표 - 2
		List<Point> dragonCurve = new ArrayList<>();
		
		// 0세대 입력
		dragonCurve.add(new Point(x, y));
		dragonCurve.add(new Point((x + dx[d]), (y + dy[d])));
		
		for (int generation = 1; generation <= g; generation++) {
//			System.out.println("generation: " + generation);
			// 기존 세대 드래곤 커브의 끝 index
			int end = dragonCurve.size() - 1;
//			System.out.println(dragonCurve.toString());
			int oldIdx = end;
			int newIdx = end;
			// 다음 세대 드래곤 커브 그리는 방법
			// 기존 세대 드래곤 커브의 끝에서 시작, 이를 end라고 하자
			// 앞으로 그릴 드래곤 커브의 시작 점 역시 end 위치
			// 따라서 기존 드래곤 커브의 좌표를 oldIdx, 그릴 드래곤 커브의 시작 좌표를 newIdx라고 하면
			// 시작은 oldIdx = newIdx = end;
			// 1. oldIdx, oldIdx - 1가 이루는 방향 D구하기
			// 2. 방향 D를 시계 방향으로 회전시킨 방향 D'구하기 
			// 3. newIdx에서 D'의 방향으로 좌표 이동, 새로운 newIdx'구하기
			// 4. newIdx'를 dragonCurve에 추가
			// 이 과정을 oldIdx가 dragonCurve의 첫 좌표가 될 때까지 반복
			while(oldIdx != 0) {
				
				// 1.
				Point op = dragonCurve.get(oldIdx);
//				System.out.println("op: " + op);
				oldIdx -= 1;
				Point nop = dragonCurve.get(oldIdx);
//				System.out.println("nop: " + nop);
				int odx = nop.x - op.x;
				int ody = nop.y - op.y;
				int D = 0; 
				for (int i = 0; i < 4; i++) {
					if(dx[i] == odx && dy[i] == ody) D = i;
				}
				// 2.
				int nD = D - 1;
				if(nD == -1) nD += 4;
				// 3.
				Point np = dragonCurve.get(newIdx);
//				System.out.println("np: " + np);
//				System.out.println("nD: " + nD);
				Point nnp = new Point((np.x + dx[nD]), (np.y + dy[nD]));
//				System.out.println("nnp: " + nnp);
				// 4.
				dragonCurve.add(nnp);
				
				newIdx += 1;
			}
		}
		
//		System.out.println(dragonCurve.toString());
		
		for(Point p : dragonCurve) {
			map[p.x][p.y] = 1;
		}
	}
}
