import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static class Belt {
		int dura;
		boolean robot;

		public Belt(int dura, boolean robot) {
			this.dura = dura;
			this.robot = robot;
		}
	}

	private static int n, k;
	private static Belt[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		arr = new Belt[2 * n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * n; i++) {
			int dura = Integer.parseInt(st.nextToken());
			arr[i] = new Belt(dura, false);
		}

		System.out.println(solution());
	}

	private static int solution() {
		int answer = 0;
		while (checkDuraCnt() < k) {
			rotate();
			moveAllRobot();
			uploadBelt();
			answer++;
		}
		return answer;
	}

	private static void rotate() {
		Belt tmp = arr[2 * n - 1];
		for (int i = 2 * n - 1; i > 0; i--) {
			arr[i] = arr[i - 1];
		}
		arr[0] = tmp;

		getOffBelt();
	}

	private static void getOffBelt() {
		if (arr[n - 1].robot) {
			arr[n - 1].robot = false;
		}
	}

	private static void moveAllRobot() {
		for (int i = n - 2; i >= 0; i--) {
			if (!arr[i].robot) {
				continue;
			}

			if (canMove(arr[i + 1])) {
				moveRobot(i, i + 1);
			}
		}
		getOffBelt();
	}

	private static boolean canMove(Belt next) {
		return !next.robot && next.dura >= 1;
	}

	private static void moveRobot(int nowIdx, int nextIdx) {
		arr[nowIdx].robot = false;
		arr[nextIdx].robot = true;
		arr[nextIdx].dura--;
	}

	private static void uploadBelt() {
		if (arr[0].dura != 0) {
			arr[0].dura--;
			arr[0].robot = true;
		}
	}

	private static int checkDuraCnt() {
		int result = 0;

		for (int i = 0; i < 2 * n; i++) {
			if (arr[i].dura == 0) {
				result++;
			}
		}
		return result;
	}
}
