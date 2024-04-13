import java.util.*;
import java.io.*;

public class BOJ_2110 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[] house = new int[N];
		
		for (int i = 0; i < N; i++) {
			house[i] = Integer.parseInt(br.readLine());			
		}

		
		Arrays.sort(house);
		if (C == 2) {
			System.out.println(house[N-1] - house[0]);
			return;
		} 
		
		int min = 1;
		int max = house[N-1] - house[0];
		
		while (min <= max) {
			int mid = (min + max) / 2;
			int before  = 0;
			int cnt = 1;
			for (int i = 1; i < N; i++) {
				if (house[i] - house[before] >= mid) {
					before = i;
					cnt += 1;
				}
			}
			
			if (cnt < C) {
				max = (mid-1);
				continue;
			}
			
			min = mid + 1;
		}
		
		System.out.println(min-1);
	}
}