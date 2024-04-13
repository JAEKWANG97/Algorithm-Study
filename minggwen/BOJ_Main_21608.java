import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_Main_21608 {

	static int N;
	static boolean visited[];
	static int[][] delta = {{1,0},{-1,0},{0,1},{0,-1}};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N= Integer.parseInt(br.readLine());
		int[][] likes = new int[N*N+1][4];
		int map[][] = new int[N][N]; 
		int[] arr = new int[N*N];
		for(int n=0;n<N*N;n++) {
			st= new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			arr[n] = num;
			for(int c=0;c<4;c++) {
				likes[num][c] = Integer.parseInt(st.nextToken());
			}
		}
		//학생의 만족도의 총 합 구하기
		for(int student : arr) {
			int max_likes=-1;
			int max_r = 0;
			int max_c = 0;
			int max_empty = -1;
			
			for(int r=0;r<N;r++) {
				for(int c=0;c<N;c++) {
					int empty = 0;
					int like = 0;
					if(map[r][c]!=0) continue;
					for(int d=0;d<4;d++) {
						int nr = r+delta[d][0];
						int nc = c+delta[d][1];
						if(!isIn(nr,nc))continue;
						if(map[nr][nc]==0)empty++;
						//좋아하는 사람 있는지 체크
						for(int idx : likes[student]) {
							if(map[nr][nc]==idx)like++;
						}
					}
					if(max_likes<like) {
						max_likes = like;
						max_r = r;
						max_c = c;
						max_empty = empty;
					}else if(max_likes==like&&empty>max_empty) {
						max_r = r;
						max_c = c;
						max_empty = empty;
					}
				}
			}
			map[max_r][max_c] = student;
		}
		int result = 0;
		for(int r=0;r<N;r++) {
			for(int c=0;c<N;c++) {
				int student = map[r][c];
				int satisfy = 0;
				for(int d=0;d<4;d++) {
					int nr = r+delta[d][0];
					int nc = c+delta[d][1];
					if(!isIn(nr,nc))continue;
					for(int like:likes[student]) {
						if(map[nr][nc]==like)satisfy++;
					}
				}
				result+=Math.pow(10, satisfy-1);
			}
		}
		
		System.out.println(result);
	}
	private static boolean isIn(int row,int col) {
		return 0<=row&&row<N&&0<=col&&col<N?true:false;
	}

}
