import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 0; test_case < T; test_case++) {
			int n = Integer.parseInt(br.readLine());
			
			//이진트리 (작은건 왼쪽, 큰건 오른쪽)
			TreeMap<Integer, Integer> map = new TreeMap<>();
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				
				char op = st.nextToken().charAt(0);
				int num = Integer.parseInt(st.nextToken());
				
				if(op == 'I') {
					map.put(num, map.getOrDefault(num, 0) + 1);
				} else {
					if(map.isEmpty()) {
						continue;
					}
					if(num == -1) {
						int value = map.firstEntry().getValue() - 1;
						if(value <= 0) {
							map.remove(map.firstKey());
						}else {
							map.put(map.firstKey(), value);							
						}
					}else {
						int value = map.lastEntry().getValue() - 1;
						if(value <= 0) {
							map.remove(map.lastKey());
						}else {
							map.put(map.lastKey(), value);							
						}
					}
				}
			}
			
			if(map.isEmpty()) {
				System.out.println("EMPTY");
			} else {
				System.out.println(map.lastKey() +" " + map.firstKey());
			}
		}
	}
}

