import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_3190{
	static class apple {
		int r, c;

		public apple(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static class xy {
		int x, y;

		public xy(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class manage implements Comparable<manage> {
		int t;
		String m;
		
		manage(int t, String m){
			this.t =t;
			this.m = m;
		}
		@Override
		public int compareTo(manage o) {
			return Integer.compare(t, o.t); // 방향전환이 시작되는 순서대로 정렬함
		}

	}

	static int n, k, d;
	static apple[] appleList;
	static Deque<xy> snake = new LinkedList<>(); // 뱀을 덱으로 관리
	static int[] dx = { 0, 1, 0, -1 }, dy = { 1, 0, -1, 0 }; // 오른쪽 부터 방향전환 담당
	
	static manage[] MList;

	public static void main(String[] args) throws IOException {
		init();
		
		int answer = playGame();
		
		System.out.println(answer);
		
	}

	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine()); // 보드 크기
		k = Integer.parseInt(br.readLine()); // 사과의 개수
		
		d = 0; // 현재 방향
		
		appleList = new apple[k]; // 사과의 좌표들
		
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			appleList[i] = new apple(r-1, c-1);

		}
		
		int manN = Integer.parseInt(br.readLine()); // 방향전환 명령어의 개수
		
		MList = new manage[manN];
		
		for(int i = 0; i < manN; i++) {
			st = new StringTokenizer(br.readLine());
			
			MList[i] = new manage(Integer.parseInt(st.nextToken()), st.nextToken()); // 명령어 만들기 
			
		}
		
		Arrays.sort(MList); // 명령어가 실행되는 순서대로 정렬
		

	}

	static int playGame() {
		int time = 0;
		int manCnt = 0; // 명령어의 개수
		
		snake.addLast(new xy(0, 0)); // 시작점 넣고 시작
		
		a: while (true) { 
			time++;
			xy cicj = snake.peekLast(); // 현재 좌표
			
			int nx = cicj.x + dx[d];
			int ny = cicj.y + dy[d];
			
			//벽을 만나는 경우
			if(nx == -1 || ny == -1) {
				break;
			}
			
			if(nx == n || ny == n) {
				break;
			}
			
//			if(snake.contains(nx)) // contains로 비교하면 주솟값으로 비교하는거 아닌가..?
			for(xy txty : snake) {
				if(nx == txty.x && ny == txty.y) { // snake에 다음 좌표와 같은 좌표가 있으면
					break a; //뭐야이게;; 충격
				}
				
			}
			
			
			snake.addLast(new xy(nx, ny)); // 이동
//			System.out.println("다음 좌표 : "+nx+" "+ny);
//			for(xy txty : snake) {
//				System.out.println(time+"뱀 좌표 : "+ txty.x+" "+txty.y);
//				
//			}
//			
			

			if (!appleCheck(nx, ny)) {// 이동한 부분에 사과가 없으면 꼬리를 하나 없앰
				snake.pollFirst();
			}
			
			
			
			//명령어가 주어진 만큼만 실행되도록
			if(manCnt < MList.length) {
				int manTime = MList[manCnt].t; // 현재 명령어가 실행되는 시간
				if(time == manTime) { //명령어가 실행될 시간이 되면 방향 전환
					switch (MList[manCnt].m) {
					case "L":
						//현재에서 왼쪽으로 방향 전환
						d -= 1;
						if(d == -1) {
							d = 3; // index 넘어가면 돌리기
						}
						break;
					case "D":
						//현재에서 오른쪽으로 방향 전환
						d += 1;
						if(d == 4) {
							d = 0;
						}
						break;
						
					}			
					manCnt++; //다음 명령어로 넘어가기
				}
			}
			

		}

		return time;
	}


	// 해당 좌표에 사과가 있는지 판별
	static boolean appleCheck(int x, int y) {
		for(int i = 0; i < k; i++) {
			
			apple target = appleList[i];
			if(target.r == x && target.c == y) {
				
				appleList[i] = new apple(-1,-1); // 사과 없애고 true 반환
				return true;
				
			}
		}
		return false;
	}
}


