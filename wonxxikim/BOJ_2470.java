import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2470 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] liquid= new int[N];
		for(int i = 0 ; i<N ; i++) {
			liquid[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(liquid);
		
		int left = 0;
		int right = N-1;
		int result = Integer.MAX_VALUE;
		int answer1 = liquid[left];
		int answer2 = liquid[right];
		while(left<right) {
			int num = Math.abs(liquid[left]+liquid[right]);
			if(num<result) {
				result = num;
				answer1 = liquid[left];
				answer2 = liquid[right];
			}
			if(liquid[left]+liquid[right]>0) {
				right--;
			}else {
				left++;
			}
		}
		System.out.println(answer1+" "+answer2);
	}

}
