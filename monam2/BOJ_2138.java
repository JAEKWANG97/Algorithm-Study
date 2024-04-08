import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2138 { //백준 2138 전구와 스위치 - 60분
	static int n;
	static boolean[] nowArr, goalArr, onFirst, offFirst;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		
		nowArr = new boolean[n];
		goalArr = new boolean[n];
		onFirst = new boolean[n]; //첫번째 켜져야함
		offFirst = new boolean[n]; //첫번째 꺼져야함
		
		String now = br.readLine();
		String goal = br.readLine();
		
		change(now, nowArr);//char형 배열을 boolean 배열로 만들기
		change(goal, goalArr);
		
		int onFirstCnt = 1;
		int offFirstCnt = 0;
		
		for (int i = 0; i < n; i++) {
			if(i<=1) {
				onFirst[i] = !nowArr[i];
				offFirst[i] = nowArr[i];
			} else {
				onFirst[i] = nowArr[i];
				offFirst[i] = nowArr[i];
			}
		}
//		
//		if (!onFirst[0]) onFirst[0] = !onFirst[0];
//		if (offFirst[0]) offFirst[0] = !offFirst[0];
		
		for (int i = 1; i < n; i++) {
			if (onFirst[i-1] != goalArr[i-1]) { //첫번째 킨게 목표랑 같지 않은 경우
				turnSwitch(i, onFirst);
				onFirstCnt++;
			}
			if (offFirst[i-1] != goalArr[i-1]){ //첫번째 끈게 목표랑 같지 않은 경우
				turnSwitch(i, offFirst);
				offFirstCnt++;
			}
			
			//각 전구 배열이 목표랑 같은지 확인
			if (Arrays.equals(onFirst, goalArr)) {
				if (Arrays.equals(onFirst, offFirst)) {
					System.out.println(Math.min(onFirstCnt, offFirstCnt));
					System.exit(0);
				}
				System.out.println(onFirstCnt);
				System.exit(0);
			} else if (Arrays.equals(offFirst, goalArr)) {
				System.out.println(offFirstCnt);
				System.exit(0);
			}
		}
		System.out.println(-1);
	}//end main
	
	private static void turnSwitch(int i, boolean[] switchArr) {
		switchArr[i-1] = !switchArr[i-1]; //바꿔주기
		switchArr[i] = !switchArr[i];
		//n-1이면 n+1 전구는 못봄
		if (i<n-1) {
			switchArr[i+1] = !switchArr[i+1];
		}
	}
	
	//char형 배열을 boolean 배열로 만들기
	private static void change(String now, boolean[] arr) {
		for (int i = 0; i < n; i++) {
			char num = now.charAt(i);
			if (num=='0') {
				arr[i] = false;
			} else {
				arr[i] = true;
			}
		}
	}
}//end class
