import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110 { //백준 2110 공유기 설치 - 60분
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		
		int result = 0;
		int left = 1;
		int right = arr[n-1] - arr[0]; //가장 먼 거리 부터 보기
		
		while (left <= right) {
			//mid 거리를 기준으로 왼쪽에서 부터 이 거리보다 커야 설치
			
			int mid = (left + right) / 2;
			int before = arr[0]; //갱신 : 이전에 공유기 설치한 곳
			int hubCnt = 1; //가장 왼쪽 집에 공유기 하나 미리 설치
			
			//이전 공유기 설치 집과 i번째 집 사이의 거리를 비교
			//거리가 mid거리보다 더 커지면 공유기 설치
			for (int i = 0; i < arr.length; i++) {
				int dist = arr[i] - before;
				if (dist >= mid) {
					hubCnt++;
					before = arr[i];
				}
			}
			
			//공유기가 더 많이 설치되면 간격 늘려서 공유기 수 줄이기
			if (hubCnt >= c) { 
				left = mid + 1;
				result = mid;
			} else { //공유기가 부족하면 간격을 좁혀서 수를 늘리기
				right = mid - 1;
			}
		}
		System.out.println(result);
	}//main

}//class
