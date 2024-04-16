import java.io.*;
import java.util.*;

public class BOJ_7662 {

	static int t, k, n;
	static TreeMap<Integer, Integer> map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		t = Integer.parseInt(br.readLine());
		
		while (t-- > 0) {
			k = Integer.parseInt(br.readLine());
			map = new TreeMap<>();
			while (k-- > 0) {
				st = new StringTokenizer(br.readLine());
				
				char c = st.nextToken().charAt(0);
				int value = Integer.parseInt(st.nextToken());
				
				if (c == 'I') {
					map.put(value, map.getOrDefault(value, 0) + 1);
				} else if (c == 'D') {
					if (map.isEmpty()) continue;
					
					int n = (value == 1) ? map.lastKey() : map.firstKey(); //max or min
					if (map.put(n, map.get(n) - 1) == 1) { //1이면 삭제
						map.remove(n);
					}
					
				}
				
				
				
			}
			
			if (map.isEmpty()) {
				System.out.println("EMPTY");
			} else {
				System.out.println(map.lastKey() + " " + map.firstKey());
			}
			
		}
		
	}
	
	
}
