import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_20055 { // 백준 20055 컨베이어 벨트 위의 로봇 - 60분
	
	static int n, k, belt[], robots[], zeroCnt;
	
	public static void main(String[] args) throws IOException {
		init();
		int stage = 1;
		while (true) {
			moveBelt();
			robotMovement();
			dropDownRobot();
			checkDurability();
			if (zeroCnt >= k) {
				break;
			}
			stage++;
		}
		
		System.out.println(stage);
	}//main
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		belt = new int[n*2];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n*2; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		robots = new int[n];
		Arrays.fill(robots, 0);
		
	}//init
	
	private static void checkDurability() {
		zeroCnt = 0;
		for (int i = 0; i < 2*n; i++) {
			if (belt[i] == 0) {
				zeroCnt++;
			}
		}
	}//checkDurability
	
	private static void dropDownRobot() {
		if (belt[0] != 0) {
			belt[0]--;
			robots[0] = 1;
		}
	}//dropDownRobot
	
	private static void robotMovement() {
		//마지막 칸에 로봇 있으면 일단 내림
		if (robots[n-1] == 1) {
			robots[n-1] = 0;
		}
		
		//마지막-1 칸에서부터 로봇 이동시키기
		
		for (int i = n-2; i >= 0; i--) {
			if (robots[i]==0) continue;
			
			//앞 칸이 비어있고, 그 칸의 내구도가 1이상 남아있다면
			if (i + 1 != n && robots[i + 1] == 0 && belt[i + 1] >= 1) {
				robots[i+1] = robots[i];
				robots[i] = 0;
				belt[i+1]--;
			}
		}
	}//robotMovement
	
	private static void moveBelt() {
		
		int temp = belt[2*n-1];
		for (int i = 2*n-1; i >= 1; i--) {
			belt[i] = belt[i-1];
		}
		belt[0] = temp;
		for (int i = n-1; i >= 1; i--) {
			robots[i] = robots[i-1];
		}
		robots[0] = 0;
	}//moveBelt
}//class
