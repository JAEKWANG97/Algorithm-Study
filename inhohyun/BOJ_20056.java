import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_20056 {
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

	static class Fireball {
		// y축
		int r;
		/// x측
		int c;
		// 질량
		int m;
		// 속력(한 번에 이동 거리)
		int s;
		// 방향
		int d;

	
		public Fireball(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

	static int N, M, K;
	static ArrayList<Fireball> map[][];
	static ArrayList<Fireball> fbList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new ArrayList[N + 1][N + 1];
		for (int r = 0; r < N+1; r++) {
			for (int c = 0; c < N+1; c++) {
				// 배열의 모든 위치에 파이어볼 객체 생성
				map[r][c] = new ArrayList<Fireball>();
			}
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[r][c].add(new Fireball(r,c,m,s,d));
			fbList.add(new Fireball(r,c,m,s,d));
		}
		// k번 명령 반복
		while(K-- > 0) {
			moveFireball();
			for(int i=1;i<N+1; i++) {
				for(int j=1;j<N+1;j++) {
					if(map[i][j].size() >= 2) {
						merge(i,j);
					}
				}
			}
			makeList();
		}
		// 결과 출력
		int answer = 0;
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < N+1; j++) {
				if(map[i][j].size() > 0) {
					for (Fireball f : map[i][j]) {
						answer += f.m;
					}
				}
				
			}
		}
		System.out.println(answer);
	}
	public static void makeList(){
 		fbList = new ArrayList<>();
 		for(int i=1; i<N+1; i++) {
 			for(int j=1; j<N+1; j++) {
 				if(map[i][j].size() > 0){
 					for(Fireball cur : map[i][j]) {
 						fbList.add(cur);
 					}
 				}
 			}
 		}
 	}

	// map 안에 있는 fireball객체를 찾고 객체 안에있는 좌표를 조건에 맞게 이동시키는 메서드
	private static void moveFireball() {
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				map[i][j] = new ArrayList<Fireball>();
			}
		}
		for (Fireball fire : fbList) {

			fire.r = fire.r + dr[fire.d] * (fire.s % N);
			fire.c = fire.c + dc[fire.d] * (fire.s % N);
			// 범위를 넘어갈때 조건에 맞춰 수행되어야함
			if (fire.r > N)
				fire.r %= N;
			if (fire.c > N)
				fire.c %= N;
			if (fire.r <= 0)
				fire.r = N - Math.abs(fire.r);
			if (fire.c <= 0)
				fire.c = N - Math.abs(fire.c);

			map[fire.r][fire.c].add(fire);
		}
	}

	// map에서 같은 좌표에 있는 모든 파이어볼들을 하나로 합치는 메서드
	public static void merge(int x, int y) {
		int sumM=0, sumS=0, sumCnt=0, sumD=0;
		boolean isEven = true;
		boolean isOdd = true;
		for(Fireball cur : map[x][y]) {
			sumM += cur.m;
			sumS += cur.s;
			sumCnt++;
			if(cur.d %2 ==0) {
				isOdd = false;
			}else {
				isEven = false;
			}
		}
		
		int M = sumM/5;
		int S = sumS/sumCnt;
		
		map[x][y] = new ArrayList<>();
		if(M <= 0) {
			return;
		}
		
		if(isEven || isOdd) {
			for(int i=0; i<4; i++) {
				map[x][y].add(new Fireball(x,y,M,S,i*2));
			}
		}else {
			for(int i=0; i<4; i++) {
				map[x][y].add(new Fireball(x,y,M,S,i*2+1));
			}
		}
		
	}


}
