
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20055 {
	static int N, K, belt[];
	static boolean[] robot;
	public static void main(String[] args) throws IOException {
		init();
		
		
		printAnswer();
	}
	
	static void printAnswer() {
		int answer = solution();
		System.out.println(answer);
	}
	
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		belt = new int[N*2+1]; 
		robot = new boolean[N+1];
		// 1~2N으로 초기화
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= 2 * N; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		
	}
	
	static int solution() {
		int cnt = 1;
		while(true) {
			// 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
			
			// 벨트
			int tmp = belt[N*2];
			for(int i = N*2; i > 1; i--) {
				belt[i] = belt[i-1];
			}
			
			belt[1] = tmp;
			
			//로봇
			
			for(int i = N; i> 1; i--) {
				robot[i] = robot[i-1];
			}
			robot[1] = false;
			if(robot[N]) {
				robot[N] = false; // 내리는 위치의 로봇 내리기
			}
			
			//가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다
			for(int i = N-1; i > 0; i--) {
				if(robot[i] &&!robot[i+1] && belt[i+1] >= 1) { // 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
					belt[i+1]--;
					robot[i] = false;
					robot[i+1] = true;
				}
			}
			
			// 언제든지 로봇이 내리는 위치에 도달하면 그 즉시 내린다.
			if(robot[N]) {
				robot[N] = false;
			}
			
			// 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
			if(belt[1] > 0) {
				robot[1] = true;
				belt[1]--;
			}
			
			//내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
			int ZeroCnt = 0;
			for(int i = 1; i <= N*2; i++) {
				if(belt[i] == 0) {
					ZeroCnt++;
				}
			}
			if(ZeroCnt >= K) {
				break;
			}
			cnt++; // 단계 수 증가
			
		}
		
		return cnt;
	}
	
}
