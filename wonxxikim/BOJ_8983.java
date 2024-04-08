import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] sade;
	static int M,N,L;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); //사대의 수
		N = Integer.parseInt(st.nextToken()); //동물의 수
		L = Integer.parseInt(st.nextToken()); //사정거리
		// 사대의 위치 x좌표
		sade = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i<M ; i++) { 
			int x = Integer.parseInt(st.nextToken());
			sade[i] = x;
		}
		// 동물이 사는 위치 (x,y)
		int[][] animals = new int[N][2];
		for(int i = 0 ; i<N ; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			animals[i] = new int[] {x,y};
		}
		Arrays.sort(sade);
		int result = 0;
		for(int i = 0 ; i<N ; i++) {
			if(isHunt(animals[i])) result++;
		}
		System.out.println(result);


	}
  
	public static boolean isHunt(int[] animal) {
		int start = 0;
		int end = M;
		while(start<=end) { //start=end 까지 탐색해야함.
			int mid = (start+end)/2;
			if(mid>=M) return false; // 종료조건 추가
      
			if(len(sade[mid],animal[0],animal[1])>L&&animal[0]<=sade[mid]) end = mid-1;
			else if(len(sade[mid],animal[0],animal[1])>L&&animal[0]>=sade[mid]) start = mid+1;
			else if(len(sade[mid],animal[0],animal[1])<=L) return true;
		}
		return false;
		
	}
  
	public static int len(int x, int a , int b) {
		return(Math.abs(x-a)+b);
	}

}

