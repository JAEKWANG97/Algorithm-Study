
import java.io.*;
import java.util.*;

public class BOJ_5052 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		List<String>[] list;
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			boolean isConsistence = true;
			list = new ArrayList[11];
			for (int i = 0; i < 11; i++) {
				list[i] = new ArrayList<>();
			}
			for (int n = 0; n < N; n++) {
				String nums = br.readLine();
				list[nums.length()].add(nums);
			}
			// 길이별로 완탐
			for (int n = 1; n < 11; n++) {
				for (String cur : list[n]) {
					for (int m = n + 1; m < 11; m++) {
						for (String cmp : list[m]) {
							if(cmp.substring(0, n).equals(cur)) {
								isConsistence = false;
								break;
							}
						}
						if (!isConsistence) {
							break;
						}
					}
					if (!isConsistence) {
						break;
					}
				}
				if (!isConsistence) {
					break;
				}
			}
			System.out.println(isConsistence ? "YES" : "NO");
		}
		
	
	}
}
