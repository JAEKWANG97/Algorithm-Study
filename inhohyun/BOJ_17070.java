import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17070 {
	static int n, map[][], answer;
	public static void main(String[] args) throws IOException {
		init();
	}
	
	static void init() throws  IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		map = new int[n][n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		answer = 0;
		dfs(0,1, 0); // d: 방향, 0 -> 가로, 1 -> 세로 2 -> 대각
		System.out.println(answer);
	}
	
	static void dfs(int r, int c, int d) {
		if(r == n-1 && c == n-1) {
			answer++;
			return;
		}
		switch(d) {
		case 0:
			if(isCheck(r, c +1) && map[r][c+1] == 0) {
				dfs(r, c+1, 0);
			}
			break;
		case 1:
			 
			if(isCheck(r+1, c) && map[r+1][c] == 0) {
				dfs(r+1, c, 1);
			}
			break;
		case 2: // 대각선이면 가로세로 다 가능
			if(isCheck(r+1, c) && map[r+1][c] == 0) {
				dfs(r+1, c, 1);
			}
			
			if(isCheck(r, c +1) && map[r][c+1] == 0) {
				dfs(r, c+1, 0);
			}
			break;
		}	
		//대각은 언제나 가능하니 따로 빼주기
		if(isCheck(r+1, c) && isCheck(r, c+1) && isCheck(r+1, c+1)) {
			if(map[r+1][c] == 0 && map[r][c+1] == 0 && map[r+1][c+1] == 0) {
				dfs(r+1, c+1, 2);
			}
		}
		
		
	}
	
	static boolean isCheck(int r, int c) {
		if(r < 0 || r >= n || c <0 || c >= n) {
			return false;
		}
		
		return true;
	}
}
