import java.util.*;
import java.io.*;

public class BOJ_2110 {
	
	static int n, c, arr[], sol;
	
	static void bs(int low, int high) {//이진탐색
		int mid;
		
		if (low <= high) { //low idx가 high idx를 넘어가면 안됨
			mid = (low + high) / 2;//중간값
			
			if (solve(mid)) {//설치 성공 했으면 더 높은 값을 넣어보기
				bs(mid + 1, high);
			} else {//설치 실패했으면 줄이기
				bs(low, mid - 1);
			}
		}
	}
	
	static boolean solve(int mid) {
		int idx = 0;
		int cRemain = c - 1;//0에 설치 무조건 함
		for (int i = 1; i < n; i++) {
			if (cRemain == 0) break;
			if (arr[i] - arr[idx] >= mid) {//둘의 차가 mid와 같거나 그 이상이면
				cRemain--;
				idx = i;
			}
		}
		
		if (cRemain == 0) {//설치 다 했으면 true
			sol = Math.max(sol, mid); //가장 큰 값
			return true;
		} else { //설치 못했으면 false
			return false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		
		for (int i = 0; i  < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr); //좌표 정렬
		
		bs(1, arr[n - 1]); //탐색
		
		System.out.println(sol);
	}
	
}
