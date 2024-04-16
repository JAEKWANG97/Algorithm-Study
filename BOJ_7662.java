package algorithm_study.april.day_04_16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ_7662 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		TreeSet<Integer> ts = new TreeSet<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				
				return o1 > o2 ? 1 : -1;
			}
			
		});
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			int k = Integer.parseInt(br.readLine());
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				char command = st.nextToken().charAt(0);
				int value = Integer.parseInt(st.nextToken());
				if(command == 'I') {
					ts.add(value);
				} else if(command == 'D') {
					if(value == 1) {
						ts.pollLast();
					} else {
						ts.pollFirst();
					}
				}
			}
			if(ts.size() == 0) {
				System.out.println("EMPTY");
			} else if(ts.size() == 1) {
				int result = ts.first();
				System.out.println(result + " " + result);
			} else {
				System.out.println(ts.pollLast() + " " + ts.pollFirst());
			}
			ts.clear();
		}
	}

}
