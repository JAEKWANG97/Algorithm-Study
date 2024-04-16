import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

// 수 묶기 / 45분
public class BOJ_1744 {
	static StringBuilder sb = new StringBuilder();
	static long nums[], sum;
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.println(sb.toString());
	}

	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		nums = new long[N];

		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(nums);

		Queue<Long> plus = new ArrayDeque<>();
		int zero = 0;
		Stack<Long> minus = new Stack<>();

		sum = 0;

		for (int i = N-1; i >= 0; i--) {
			if (nums[i] > 1) plus.offer(nums[i]);
			else if (nums[i] == 1) sum++;
			else if (nums[i] == 0) zero++;
			else minus.push(nums[i]);
		}

		int size = plus.size() / 2;
		for (int i = 0; i < size; i++) {
			sum += plus.poll() * plus.poll();
		}
		if (!plus.isEmpty()) sum += plus.poll();

		size = minus.size() / 2;
		for (int i = 0; i < size; i++) {
			sum += minus.pop() * minus.pop();
		}

		size = minus.size();
		for (int i = 0; i < size && zero > 0; i++, zero--) {
			minus.pop();
		}

		while(!minus.isEmpty()) {
			sum += minus.pop();
		}

		sb.append(sum);
	}

}