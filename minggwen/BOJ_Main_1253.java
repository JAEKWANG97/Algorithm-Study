import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_Main_1253 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int n=0;n<N;n++) {
			
			arr[n] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		int answer=0;
		for(int idx=0;idx<N;idx++) {
			int left = 0;
			int right = N-1;
			while(left<right) {
				if(left==idx) {
					left++;
					continue;
				}
				if(right==idx) {
					right--;
					continue;
				}
				int sum = arr[left]+arr[right];
				int num = arr[idx];
				if(sum==num) {
					answer++;
					break;
				}else if(sum>num) right--;
				else if(sum<num) left++;
			}
		}
		System.out.println(answer);
	}

}
