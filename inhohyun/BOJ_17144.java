import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17144 {
	static class xy {
		int x, y, spreadDust;
		
		
		
		xy(int x, int y, int s) {
			this.x = x;
			this.y = y;
			this.spreadDust = s;
		}

	}

	static int R, C, T;
	static int[] dx = { 0, 0, 1, -1 }, dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		init();
			
	}
	
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // 행
		C = Integer.parseInt(st.nextToken()); // 열
		T = Integer.parseInt(st.nextToken()); // T초 뒤에 남아있는 미세먼지

		int[][] map = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int cnt = 0;
		while(true) {
			if(cnt == T) break;
			cnt++;
			spread(map);
//			System.out.println("공기청정기 작동 전");
//			print(map);
//			System.out.println("----------------");
//			System.out.println("공기청정기 작동 후");
			map = airCleaner(map);
//			print(map);
			
			
		}
		int answer = sumDust(map);
		System.out.println(answer);
		
		
	
	}
	static int sumDust(int[][] map) {
		int sum = 0;
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] != -1) {
					sum += map[i][j];
				}
			}
		}
		return sum;
	}
	static int[][] airCleaner(int[][] map) {
		//공기청청기의 위아래 좌표 가져오기
		int top = 0;
		int bottom = 0;
		for(int i = 0; i < R; i++) {
			if(map[i][0] == -1) {
				top = i;
				bottom = i+1;
				break;
			}
		}
		
		//위에 순환시키기
        for (int x = top - 1; x > 0; x--) {
            map[x][0] = map[x - 1][0];
        }
 
        for (int y = 0; y < C - 1; y++) {
            map[0][y] = map[0][y + 1];
        }
 
        for (int x = 0; x < top; x++) {
            map[x][C - 1] = map[x + 1][C - 1];
        }
 
        for (int y = C - 1; y > 1; y--) {
            map[top][y] = map[top][y - 1];
        }
 
        map[top][1] = 0; // 공기청정기로 나가는 곳이므로 먼지는 0이다.
        
        
        //아래 순환시키기
        for (int x = bottom + 1; x < R - 1; x++) {
            map[x][0] = map[x + 1][0];
        }
 
        for (int y = 0; y < C - 1; y++) {
            map[R - 1][y] = map[R - 1][y + 1];
        }
 
        for (int x = R - 1; x > bottom; x--) {
            map[x][C - 1] = map[x - 1][C - 1];
        }
 
        for (int y = C - 1; y > 1; y--) {
            map[bottom][y] = map[bottom][y - 1];
        }
 
        map[bottom][1] = 0; // 공기청정기로 나가는 곳이므로 먼지는 0이다.
	
		
		
		return map;
	}

	// 먼지들을 확산시킬 메서드
	static int[][] spread(int[][] map) {
		Queue<xy> dust = searchDust(map); // 먼지들의 좌표 가져오기
		while (!dust.isEmpty()) {
			xy dd = dust.poll();
			int x = dd.x;
			int y = dd.y;
			int cnt  = 0; // 나눠준 방향 개수
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(!isCheck(nx, ny)) continue;
				
				if(map[nx][ny] == -1) continue;
				
				cnt++;		
				map[nx][ny] += dd.spreadDust;		
			}
			
			map[x][y] -= (dd.spreadDust * cnt); // (r, c)에 남은 미세먼지의 양은 Ar,c - ⌊Ar,c/5⌋×(확산된 방향의 개수) 이다.	
		}
		
		return map;
	}

	// 먼지들의 위치를 반환할 메서드
	static Queue<xy> searchDust(int[][] map) {
		Queue<xy> dust = new ArrayDeque<>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] != 0 && map[i][j] != -1) {
					dust.add(new xy(i, j, map[i][j] / 5)); 
				}
			}
		}

		return dust;

	}

	static boolean isCheck(int x, int y) {
		if (x < 0 || x >= R || y < 0 || y >= C) {
			return false;

		}
		return true;
	}
	
	static void print(int[][] map) {
		for(int i = 0; i <R; i++) {
			for(int j = 0; j < C; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
