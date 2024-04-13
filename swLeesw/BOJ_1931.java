import java.util.*;
import java.io.*;

public class BOJ_1931 {
	
	static int n, arr[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n][2];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		//끝나는 시간을 오름차순으로 정렬
		Arrays.sort(arr, (o1, o2) -> {
			if (o1[1] != o2[1]) {
				return o1[1] - o2[1];
			} 
			return o1[0] - o2[0];
		});
		
		int sol = 1;
		int e = arr[0][1];
		//이전의 끝 부분과 다음의 첫 부분을 비교
		for (int i = 1; i < n; i++) {
			if (arr[i][0] >= e) {
				e = arr[i][1];
				sol++;
			}
		}
		
		System.out.println(sol);
	}
	
}