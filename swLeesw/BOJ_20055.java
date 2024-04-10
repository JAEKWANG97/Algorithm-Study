import java.util.*;
import java.io.*;

public class BOJ_20055 {

	static class Belt {
		int dur;
		boolean robot;
		public Belt(int dur, boolean robot) {
			this.dur = dur;
			this.robot = robot;
		}
		
		
	}

	static int n, k;
	static Belt[] belts;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int sol = 0;
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		belts = new Belt[2 * n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n * 2; i++) {
			belts[i] = new Belt(Integer.parseInt(st.nextToken()), false);
		}


		while (true) {
			sol++;
			// 1번째 단계: 벨트 이동
			moveBelt();
			// 2번째 단계: 로봇 이동
			moveRobot();
			// 3번째 단계: 로봇 올리기
			putRobot();
			// 4번째 단계: 과정 끝났는지 탐색
			if (k <= 0) {
				break;
			}
		}
		System.out.println(sol);
	}

	static void moveBelt() {
		Belt tmp = belts[2 * n - 1];
		for (int i = 2 * n - 1; i > 0; i--) {
			belts[i] = belts[i - 1];
		}
		belts[0] = tmp;
		if (belts[n - 1].robot) { //끝 로봇 삭제
			belts[n - 1].robot = false; //끝 로봇 삭제			
		}
	}
	
	
	static void moveRobot() {
		for (int i = n - 2; i >= 0; i--) { //n - 2번 로봇부터 이동 판별
			if (!belts[i].robot) continue;//로봇이 없으면 다음으로
			if (belts[i + 1].dur > 0 && !belts[i + 1].robot) { //내구도 1 이상 && 로봇 없으면
				belts[i + 1].dur--;
				if (belts[i + 1].dur == 0) k--;
				belts[i + 1].robot = true;
				belts[i].robot = false;
			}
		}
		if (belts[n - 1].robot) {
			belts[n - 1].robot = false; //끝 로봇 삭제
		}
	}
	
	static void putRobot() {
		if (belts[0].dur > 0) { //내구도 있으면
			belts[0].dur--;
			if (belts[0].dur == 0) k--;
			belts[0].robot = true;
		}
		
	}
	
}
