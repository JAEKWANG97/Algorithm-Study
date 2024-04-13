import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n][m];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] dx = {0, 0, 0, -1, 1};
		int[] dy = {0, 1, -1, 0, 0};
		// 동 서 북 남
		jusawe ju = new jusawe(0, 0, 0, 0, 0, 0);
		// 처음은 0으로 셋팅
		
		st = new StringTokenizer(br.readLine());
		for(int o=0;o<k;o++) {
			int order = Integer.parseInt(st.nextToken());
			
			// 일단 굴리기
			x = x+dx[order];
			y = y+dy[order];
			if(x<0||y<0||x>=n||y>=m) {
				// 범위를 벗어나게 되면
				x -= dx[order];
				y -= dy[order];
				continue;
				// 다음 조건으로 넘어간다.
			}
			
			if(order == 1) {
				// 동쪽으로 굴리기
				int tmp = ju.left;
				ju.left = ju.now;
				ju.now = ju.right;
				ju.right = ju.nowtop;
				ju.nowtop = tmp;
			}else if(order == 2) {
				// 서쪽으로 굴리기
				int tmp = ju.left;
				ju.left = ju.nowtop;
				ju.nowtop = ju.right;
				ju.right = ju.now;
				ju.now = tmp;
			}else if(order == 3) {
				// 북쪽으로 굴리기
				int tmp = ju.now;
				ju.now=ju.top;
				ju.top = ju.nowtop;
				ju.nowtop = ju.bottom;
				ju.bottom = tmp;
			}else if(order == 4) {
				// 남쪽으로 굴리기
				int tmp = ju.now;
				ju.now = ju.bottom;
				ju.bottom = ju.nowtop;
				ju.nowtop = ju.top;
				ju.top = tmp;
			}
			
			// 주사위를 굴렸을 때, 이동하는 칸에 쓰여있는 수가 0이면 주사위의 바닥면에 쓰여있는 수가 복사된다.
			if (map[x][y]==0) {
				map[x][y] = ju.now;
			}else {
				// 아닐 경우에는 반대로
				ju.now = map[x][y];
				map[x][y] = 0;
			}
			
			sb.append(ju.nowtop).append("\n");
		}
		System.out.println(sb);
	}
}

class jusawe {
	int now, left, right, top, bottom, nowtop;

	public jusawe(int now, int left, int right, int top, int bottom, int nowtop) {
		super();
		this.now = now;
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
		this.nowtop = nowtop;
	}

	@Override
	public String toString() {
		return "jusawe [now=" + now + ", left=" + left + ", right=" + right + ", top=" + top + ", bottom=" + bottom
				+ ", nowtop=" + nowtop + "]";
	}
	
}
출처: https://jainn.tistory.com/312 [Dogfootruler Kim:티스토리]