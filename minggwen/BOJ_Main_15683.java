import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_Main_15683 {

	static int min = 0;
	static int N,M;
	static int cctvCnt = 0;
	static boolean[][] visited;
	static boolean[][] copyVisited;
	static int[] comb;
	static int[][] map;
	static int[][] cctv1Arr = {{-1,0},{1,0},{0,-1},{0,1}};
	static int[][] cctv2Arr = {{1,0},{0,1}};
	static int[][] cctv3Arr = {{1,1},{1,-1},{-1,-1},{-1,1}};
	static List<int[]> cctvLocation;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		copyVisited = new boolean[N][M];
		min = N*M;
		cctvLocation = new ArrayList<>();
		for(int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			for(int m=0;m<M;m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
				if(map[n][m]==6) {
					copyVisited[n][m]=true;
				}
				else if(1<=map[n][m]&&map[n][m]<=5) {
					int[] tmp = new int[3];
					tmp[0] = map[n][m];
					tmp[1]=n;
					tmp[2]=m;
					cctvLocation.add(tmp);
					cctvCnt++;
					copyVisited[n][m]=true;
				}
			}
		}
		comb = new int[cctvCnt];
		comb(0);
		System.out.println(min);
	}
	private static void comb(int idx) {
		if(idx==cctvCnt) {
			int tmp = getCnt(comb);
			min = tmp<min?tmp:min;
			return;
		}
		int cctvNum = cctvLocation.get(idx)[0];
		if(cctvNum==2) {
			for(int i=0;i<2;i++) {
				comb[idx]=i;
				comb(idx+1);
			}
		}else if(cctvNum==5){
			comb[idx]=0;
			comb(idx+1);
		}else {
			for(int i=0;i<4;i++) {
				comb[idx]=i;
				comb(idx+1);
			}
		}
		
		
	}
	private static int getCnt(int comb[]) {
		int cnt = 0;
		copyVisited();
		for(int idx=0; idx<cctvLocation.size();idx++) {
			if(cctvLocation.get(idx)[0]==1) {
				cctv1(cctvLocation.get(idx)[1],cctvLocation.get(idx)[2],comb[idx]);
			}else if(cctvLocation.get(idx)[0]==2) {
				cctv2(cctvLocation.get(idx)[1],cctvLocation.get(idx)[2],comb[idx]);
			}
			else if(cctvLocation.get(idx)[0]==3) {
				cctv3(cctvLocation.get(idx)[1],cctvLocation.get(idx)[2],comb[idx]);
			}else if(cctvLocation.get(idx)[0]==4) {
				cctv4(cctvLocation.get(idx)[1],cctvLocation.get(idx)[2],comb[idx]);
			}else if(cctvLocation.get(idx)[0]==5) {
				cctv5(cctvLocation.get(idx)[1],cctvLocation.get(idx)[2]);
			}
		}
		for(int n=0;n<N;n++) {
			for(int m=0;m<M;m++) {
				if(!visited[n][m])cnt++;
			}
		}
		return cnt;
	}
	private static void copyVisited() {
		for(int n = 0; n<N;n++) {
			for(int m=0;m<M;m++) {
				visited[n][m] = copyVisited[n][m];
			}
		}
	}
	private static void cctv1(int row,int col,int d) {
		count(row,col,cctv1Arr[d][0],cctv1Arr[d][1]);
	}
	private static void cctv2(int row, int col, int d) {
		count(row,col,cctv2Arr[d][0],cctv2Arr[d][1]);
		count(row,col,-cctv2Arr[d][0],-cctv2Arr[d][1]);
	}
	private static void cctv3(int row,int col,int d) {
		count(row,col,cctv3Arr[d][0],0);
		count(row,col,0,cctv3Arr[d][1]);
	}
	private static void cctv4(int row,int col,int d) {
		if(d==0) {
			count(row,col,1,0);
			count(row,col,0,-1);
			count(row,col,0,1);
		}
		else if(d==1) {
			count(row,col,-1,0);
			count(row,col,0,-1);
			count(row,col,0,1);
		}
		else if(d==2) {
			count(row,col,-1,0);
			count(row,col,1,0);
			count(row,col,0,-1);
		}
		else {
			count(row,col,-1,0);
			count(row,col,1,0);
			count(row,col,0,1);
		}
	}
	private static void cctv5(int row, int col) {
		count(row,col,1,0);
		count(row,col,-1,0);
		count(row,col,0,1);
		count(row,col,0,-1);
	}
	private static void count(int row, int col, int rowD,int colD) {
		while(true) {
			if(!isIn(row,col)||map[row][col]==6) {
				return;
			}
			if(!visited[row][col]) {
				visited[row][col] = true;
			}
			row+=rowD;
			col+=colD;
		}
	}
	private static boolean isIn(int row, int col) {
		return 0<=row&&row<N&&0<=col&&col<M?true:false;
	}
}
