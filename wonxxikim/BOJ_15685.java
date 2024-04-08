

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int[][] delta = {{0,1},{-1,0},{0,-1},{1,0}};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[101][101];
		for(int i = 0 ; i<N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			ArrayList<Integer> list = new ArrayList<>();
			map[y][x] = 1;
			y +=delta[d][0];
			x +=delta[d][1];
			map[y][x] = 1;
			list.add(d);
			for(int j = 1 ; j<=g ; j++) { //세대 수 만큼 반복
				int size = list.size();
				for(int k = size-1 ; k>=0 ; k--) {
					int cur = (list.get(k)+1)%4;
					y += delta[cur][0];
					x += delta[cur][1];
					map[y][x] = 1;
					list.add(cur);
				}
			}
		}

		//사각형의 개수 세기
		int result = 0;
		for(int i = 0 ; i<100 ; i++) {
			for(int j =0 ; j<100 ; j++) {
				if(map[i][j] == 1 && map[i+1][j] == 1 && map[i+1][j+1] == 1 && map[i][j+1]==1)
					result++;
			}
		}
		System.out.println(result);


	}

}
