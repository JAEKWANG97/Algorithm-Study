package algorithm_study.april.day_04_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * A~F를 0~1이라고 하면
 * 주사위는 0~5번째 면을 가지게 된다
 * 이를 클래스로 만들어 준다.
 * 
 * 구하고자하는 정육면체에서 보이는 면의 주사위의 상태는 총 3가지
 * 1. 한면만 보이는 경우
 * 2. 두면이 보이는 경우
 * 3. 세면이 보이는 경우
 * 
 * 구하고자하는 정육면체의 상태
 * 1. N == 1일 때
 *    - 바닥면이 제일 큰 경우 
 * 2. N == 2일 때
 * 	  - 두면이 보이는 경우와 세면이 보이는 경우
 * 3. N >= 3일 때
 * 	  - 한면, 두면, 세면이 모두 보이는 경우
 * 
 * 주사위의 최소 값은 그리디로 구할 수 있다.
 * 1. 한면
 * 	  - 제일 작은 값
 * 2. 두면
 * 	  - 마주보지 않는 면으로 이루어진 두면 중 최소 값
 * 	  - 이때 마주보지 않다는 것은 주사위의 두면의 값이 합이 5가되지 않는 경우
 * 3. 세면
 *    - 마주보지 않는 면으로 이루어진 세면 중 최소 값
 *    - 이때 마주보지 않다는 것은 주사위의 세면 중 두면을 골랐을 때 모든 경우가 합이 5가되지 않는 경우
 */
public class BOJ_1041 {
	
	private static class Face implements Comparable<Face>{
		int key, value;

		public Face(int key, int value) {
			super();
			this.key = key;
			this.value = value;
		}

		@Override
		public String toString() {
			return "Face [key=" + key + ", value=" + value + "]";
		}

		@Override
		public int compareTo(Face o) {
			if(this.value == o.value) {
				return this.key - o.key;
			}
			return this.value - o.value;
		}
	}
	
	private static Face[] dice = new Face[6];
		
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		long N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int sum = 0;

		for (int i = 0; i < 6; i++) {
			dice[i] = new Face(i, Integer.parseInt(st.nextToken()));
			sum += dice[i].value;
		}
		Arrays.sort(dice);

		int oneFaceMin = dice[0].value;
		int twoFaceMin = getTwoFaceMin();
		int threeFaceMin = getThreeFaceMin();

		if(N == 1) System.out.println(sum - dice[5].value);
		else if(N == 2) {
			System.out.println(threeFaceMin * 4 + twoFaceMin * 4);
		} else {
			long threeMin = threeFaceMin * 4;
			long twoMin = twoFaceMin * (4 * (2 * N - 3));
			long oneMin = oneFaceMin * ((N - 2) * (5 * N - 6));
			long result = oneMin + twoMin + threeMin;
			System.out.println(result);
		}
	}

	private static int getTwoFaceMin() {
		int min = dice[0].value;
		for (int i = 1; i < 6; i++) {
			if(dice[0].key + dice[i].key != 5) {
				min += dice[i].value;
				break;
			}
		}
		return min;
	}

	private static int getThreeFaceMin() {
		int min = dice[0].value;
		int firstFace = dice[0].key;
		int secondFace = 0;
		for (int i = 1; i < 6; i++) {
			if(dice[0].key + dice[i].key != 5) {
				min += dice[i].value;
				secondFace = dice[i].key;
				break;
			}
		}
		for (int i = 1; i < 6; i++) {
			if(firstFace == dice[i].key || secondFace == dice[i].key) continue;
			
			if(firstFace + dice[i].key != 5 && secondFace + dice[i].key != 5) {
				min += dice[i].value;
				break;
			}
		}
		return min;
	}
}
