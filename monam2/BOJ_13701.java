import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_13701 { //백준 13701 중복 제거 - 30분

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Set<Integer> set = new HashSet<Integer>();
		StringBuilder sb = new StringBuilder();
		
		while (st.hasMoreTokens()) {
			int t = Integer.parseInt(st.nextToken());
			if (set.contains(t)) {
				continue;
			}
			
			set.add(t);
			sb.append(t).append(" ");
		}
		System.out.println(sb.toString());
	}//main
}//class
