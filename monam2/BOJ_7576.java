import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_7576 { //백준 7576 토마토(2d) - 33분 36초
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n,m,map[][];

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken()); //열
		n = Integer.parseInt(st.nextToken()); //행
		map = new int[n][m];
		ArrayList<int[]> tmtLocation = new ArrayList<int[]>();

		boolean noZero = true; //0이 없으면(이미 다익어있으면) true
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j]==1) { //토마토
					tmtLocation.add(new int[] {i,j,0});
				} else if (map[i][j]==0) noZero=false;
			}
		}

		if (noZero) {
			System.out.println(0);
			return;
		}
		System.out.println(stratBfs(tmtLocation));
	}//main

	private static int stratBfs(ArrayList<int[]> tmtLoca) {
		ArrayDeque<int[]> q = new ArrayDeque<int[]>();
		boolean[][] visited = new boolean[n][m];
		
		for (int[] arr : tmtLoca) {
			q.offer(arr);
			visited[arr[0]][arr[1]] = true;
		}
		int maxDay = 0;
		int[] dx = {-1,0,1,0};
		int[] dy = {0,1,0,-1};

		while (!q.isEmpty()) {
			int x = q.peek()[0];
			int y = q.peek()[1];
			int day = q.peek()[2];
			q.poll();
			
			maxDay = Math.max(maxDay, day);

			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];

				if (nx<0||ny<0 || nx>=n||ny>=m) continue;

				if (map[nx][ny]==0) {
					map[nx][ny] = 1;
					q.offer(new int[] {nx, ny, day+1});
				}
			}
		}

		if (!checkZero()) return -1;
		return maxDay;
	}//startBfs

	//0이 남아있으면 false 리턴
	private static boolean checkZero() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j]==0) return false;
			}
		}
		return true;
	}
}//class
