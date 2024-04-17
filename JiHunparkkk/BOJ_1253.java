import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	private static int n;
	private static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = 0;
		Arrays.sort(arr);
		for(int i = 0; i < n; i++) {
			int target = arr[i];
			
			int left = (i == 0 ? i + 1 : 0);
			int right = n - 1 == i ? n - 2 : n - 1;
						
			while(left < right) {
				if(arr[left] + arr[right] == target) {
					answer++;
					break;
				}
				
				if(arr[left] + arr[right] < target) {
					left++;
					if(left == i) {
						left++;
					}
				}else {
					right--;
					if(right == i) {
						right--;
					}
				}
			}
		}
		
		System.out.println(answer);
	}

}

