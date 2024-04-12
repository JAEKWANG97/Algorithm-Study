import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Main_2343 {

	static int N,M,arr[];
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int n=0;n<N;n++) {
			arr[n] = Integer.parseInt(st.nextToken());
		}
		int left = 0;
		int right = 0;
		for(int n=0;n<N;n++) {
			right+=arr[n];
		}
		while(left<=right) {
			int mid = (left+right)/2;
			int cnt = 0;
			int sum = 0;
			for(int idx=0;idx<N;idx++) {
				if(sum+arr[idx]>mid) {
					sum=0;
					cnt++;
				}
				sum+=arr[idx];
			}
			if(sum!=0)cnt+=1;
			
			if(cnt<=M) {
				right = mid-1;
			}else if(cnt>M) {
				left=mid+1;
			}
		}
		System.out.println(left);
	
	}

}
