package algorithm_study.april.day_04_17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class BOJ_13701 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BitSet set = new BitSet();
		
		StringBuilder sb = new StringBuilder();
		
		while(st.hasMoreTokens()) {
			int num = Integer.parseInt(st.nextToken());
			if(!set.get(num)) {
				set.set(num);
				sb.append(num).append(" ");
			}
		}
		System.out.println(sb.toString().trim());
	}

}
