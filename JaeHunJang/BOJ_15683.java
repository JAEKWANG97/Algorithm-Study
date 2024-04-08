import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 감시 - 60분
public class BOJ_15683 {
	static StringBuilder sb = new StringBuilder();
	static int N, M, minArea, map[][], copyMap[][], cameraDirection[], deltas[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	private static List<Camera> cameras;
	public static void main(String[] args) throws Exception {
		init();
		solve();
		print();
	}
	private static void print() {
		System.out.println(sb.toString());
	}
	
	private static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 사무실 크기
		M = Integer.parseInt(st.nextToken()); // 사무실 크기
		minArea = Integer.MAX_VALUE;
		cameraDirection = new int[8];
		Arrays.fill(cameraDirection, -1);
		cameras = new ArrayList<>();
		map = new int[N][M]; // 사무실
		copyMap = new int[N][M]; // 계산에 사용할 사무실 복제
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0 && map[i][j] < 6) {
					cameras.add(new Camera(i, j, map[i][j]));
				}
				copyMap[i][j] = map[i][j];
			}
		}
	}
	
	private static void solve() throws Exception {
		selectCameraDirection(0);
		sb.append(minArea);
	}
	
	private static void selectCameraDirection(int cnt) {
		if (cnt == cameras.size()) {
			calcArea();
			return;
		}
		
		int cameraNum = cameras.get(cnt).num;
		if (cameraNum == 2) {
			for (int d = 0; d < 2; d++) {
				cameraDirection[cnt] = d;
				selectCameraDirection(cnt+1);
			}
		} else if (cameraNum == 5) {
			cameraDirection[cnt] = 0;
			selectCameraDirection(cnt+1);
		} else {
			for (int d = 0; d < 4; d++) {
				cameraDirection[cnt] = d;
				selectCameraDirection(cnt+1);
			}
		}
		
	}
	
	private static void calcArea() {
		initCopyArr(); // 계산 전에 복사 배열 초기화
		for (int i = 0; i < cameras.size(); i++) {
			Camera camera = cameras.get(i);
			int d = cameraDirection[i];
			if (camera.num == 1) { // 1번 카메라는 상, 하, 좌, 우 1방향
				viewCamera(camera, d);
			}
			
			if (camera.num == 2) { // 2번 카메라는 상하, 좌우
				viewCamera(camera, d);
				viewCamera(camera, d+2);
			}
			
			if (camera.num == 3) { // 3번 카메라는 직각으로 4방
				viewCamera(camera, d % 4);
				viewCamera(camera, (d+1) % 4);
			}
			
			if (camera.num == 4) { // 4번 카메라는 3방향 ㅗ 로 4방
				viewCamera(camera, d % 4);
				viewCamera(camera, (d+1) % 4);
				viewCamera(camera, (d+2) % 4);
			}
			
			if (camera.num == 5) { // 5번 카메라는 4방향 전체
				for (int j = d; j < deltas.length; j++) {
					viewCamera(camera, j);
				}
			}
		}
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copyMap[i][j] == 0) count++;
			}
		}
		minArea = Math.min(count, minArea);
	}
	
	private static void viewCamera(Camera camera, int d) {
		int nr = camera.r + deltas[d][0];
		int nc = camera.c + deltas[d][1];
		while (isIn(nr, nc) && copyMap[nr][nc] != 6) {
			copyMap[nr][nc] = 7;
			nr += deltas[d][0];
			nc += deltas[d][1];
		}
	}
	
	
	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
	
	private static void printArr(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println();
	}
	
	private static void initCopyArr() {
		for (int i = 0; i < copyMap.length; i++) {
			for (int j = 0; j < copyMap[i].length; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
	}
	
	static class Camera {
		int r, c, num;

		public Camera(int r, int c, int num) {
			this.r = r;
			this.c = c;
			this.num = num;
		}
	}
}
