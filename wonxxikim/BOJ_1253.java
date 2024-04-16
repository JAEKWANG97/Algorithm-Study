
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);
		int cnt = 0;
		for(int i =0 ; i<N ; i++) {
			int left = 0;
			int right = N-1;
			if(left==i)left++;
			if(right==i)right--;
			while(left<right) {
				int cur = nums[left]+nums[right];
				if(nums[i]== cur) {
					cnt++;
					break;
				}
				if(nums[i]>cur) {
					if(++left==i) left++;
				}
				if(nums[i]<cur) {
					if(--right==i) right--;
				}
		
			}
		}
	
		System.out.println(cnt);

	}

}
