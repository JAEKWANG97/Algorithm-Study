import java.io.*;
import java.util.*;

public class BOJ_7662 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		HashMap<Integer, Integer> cnt;
		PriorityQueue<Integer> maxq, minq; 
		int N = Integer.parseInt(br.readLine().trim());
		for (int n = 0; n < N; n++) {
			cnt = new HashMap<Integer, Integer>();
			int K = Integer.parseInt(br.readLine().trim());
			maxq = new PriorityQueue<Integer>(Collections.reverseOrder());
			minq = new PriorityQueue<Integer>();
			
			for (int k = 0, num, tmp; k < K; k++) {
				st = new StringTokenizer(br.readLine(), " ");
				char ch = st.nextToken().charAt(0);
				num = Integer.parseInt(st.nextToken());
				if (ch == 'I') {
					maxq.offer(num);
					minq.offer(num);
					cnt.put(num, cnt.getOrDefault(num, 0) + 1);
				} else {
					if (num > 0) {
						while(!maxq.isEmpty()) {
							tmp = maxq.poll();
							if (cnt.get(tmp) != 0) {
								cnt.put(tmp, cnt.get(tmp) - 1);
								break;
							}
						}
					} else {
						while(!minq.isEmpty()) {
							tmp = minq.poll();
							if (cnt.get(tmp) != 0) {
								cnt.put(tmp, cnt.get(tmp) - 1);
								break;
							}
						}
					}
				}
			}

			long max = Long.MIN_VALUE, min = Long.MAX_VALUE;
			int tmp;
			while(!maxq.isEmpty()) {
				tmp = maxq.poll();
				if (cnt.get(tmp) != 0) {
					max = tmp;
					break;
				}
			}
			
			while(!minq.isEmpty()) {
				tmp = minq.poll();
				if (cnt.get(tmp) != 0) {
					min = tmp;
					break;
				}
			}
			
			if (max == Long.MIN_VALUE || min == Long.MAX_VALUE) {
				System.out.println("EMPTY");
			} else {
				System.out.println(max + " " +  min);
			}
		}
	}
}
