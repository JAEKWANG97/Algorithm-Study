import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SEA_4014 {

	static int N,X;
	static boolean visited[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			sb.append("#"+t+" ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][N];
			for(int r=0;r<N;r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0;c<N;c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			int cnt = 0;
			for(int r=0;r<N;r++) {
				if(checkArr(map[r]))cnt++;
			}
			for(int c=0;c<N;c++) {
				int[] tmpArr = new int[N];
				for(int r = 0; r<N;r++) {
					tmpArr[r] = map[r][c];
				}
				if(checkArr(tmpArr))cnt++;
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	//배열마다 활주로가 만들 수 있는지 확인 
	private static boolean checkArr(int arr[]) {
		visited = new boolean[N]; // 경사로가 만들어진 곳 
		for(int idx = 1; idx<N;idx++) {
			if(arr[idx]!=arr[idx-1]) {
				if(Math.abs(arr[idx]-arr[idx-1])!=1||!makeSlide(arr,idx,arr[idx-1]-arr[idx])) return false;
				else {
					if(arr[idx-1]-arr[idx]>0)idx+=X-1;
				}
			}
		}
		return true;
	}
	//경사로를 만들 수 있는지 확인하고,경사로를 만드는 함
	private static boolean makeSlide(int arr[],int idx,int d) {
		if(d>0) {
			for(int i=idx+1;i<idx+X;i++) {
				if(!isIn(i)||arr[idx]!=arr[i]||visited[i])return false; // 만약 이미 경사로가 만들어졌거나 ,범위 밖을  벗어나거나, 경사로의 길이만큼 만들지 못할 때 false 
			}
		}else {
			for(int i=idx-1;i>=idx-X;i--) {
				if(!isIn(i)||arr[idx-1]!=arr[i]||visited[i])return false;
			}
		}
		
		//경사로를 만들 수 있으면, 경사로를 만들고, 같은 자리에 경사로를 또 만들어지는 것을 대비하여 만들어진 경사로 위치 저
		if(d>0) {
			for(int i=idx;i<idx+X;i++) {
				visited[i] = true;
			}
		}else {
			for(int i=idx-1;i>=idx-X;i--) {
				visited[i]=true;
			}
		}
		return true;
	}
	private static boolean isIn(int idx) {
		return 0<=idx&&idx<N? true:false;
	}

}
