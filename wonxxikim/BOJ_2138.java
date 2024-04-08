import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static char[] target, present, onebon;
	static int N, cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		present = br.readLine().toCharArray();
		target = br.readLine().toCharArray();
		onebon = present.clone();

		System.out.println(getresult());

	}

	public static int getresult() {
		cnt = 0;
		return light();
	}

	public static void swap(int num) {
		if (num > N - 1 || num < 0)
			return;
		if (present[num] == '1')
			present[num] = '0';
		else
			present[num] = '1';
	}

	public static int light() {
		for (int i = 0; i < N - 1; i++) {
			if (target[i] != present[i]) {
				swap(i);
				swap(i + 1);
				swap(i + 2);
				cnt++;
			}
		}
		if (target[N - 1] != present[N - 1]) {
			present = onebon.clone();
			cnt = 1;
			swap(0);
			swap(1);
			for (int i = 0; i < N - 1; i++) {
				if (target[i] != present[i]) {
					swap(i);
					swap(i + 1);
					swap(i + 2);
					cnt++;
				}
			}
		}
		if(Arrays.equals(target,present)) return cnt;
		else return -1;

	}

}
