import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 좋다 / 90분
public class BOJ_1253 {
	static StringBuilder sb = new StringBuilder();
	static int nums[], count;
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.println(sb.toString());
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		count = 0;
		nums = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);
		
		for (int i = 0; i < N; i++) {
			int left = 0;
			int right = N - 1;
			int sum;
			
			while(left <right) {
				if (left == i) left++;
				else if (right == i) right--;
				else {
					sum = nums[left] + nums[right];
					
					if (sum == nums[i]) {
						count++;
						break;
					}
					
					if (sum > nums[i]) right--;
					
					if (sum < nums[i]) left++;
				}
			}
		}
		
		sb.append(count);
	}
}
