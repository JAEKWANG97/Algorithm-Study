import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_Main_5052 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		Queue<String> que = new PriorityQueue<>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				for(int idx = 0; idx<Math.min(o1.length(),o2.length());idx++) {
					if(o1.charAt(idx)==o2.charAt(idx))continue;
					return o1.charAt(idx)-o2.charAt(idx);
				}
				return o1.length()-o2.length();
			}
		});
		for(int t=0;t<T;t++) {
			int N= Integer.parseInt(br.readLine());
			boolean flag = true;
			que.clear();
			for(int n=0;n<N;n++) {
				String str = br.readLine();
				que.offer(str);
			}
			while(!que.isEmpty()) {
				
				String tmp = que.poll();
				if(que.peek()==null) break;
				if(que.peek().indexOf(tmp)==0) {
					flag = false;
					break;
				}
			}
			System.out.println(flag?"YES":"NO");
		}
	}
}
