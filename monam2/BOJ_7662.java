import java.io.*;
import java.util.*;

public class BOJ_7662 { //이중 우선순위 큐 - 100분
	
	static Map<Integer, Integer> map;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			int input = Integer.parseInt(br.readLine());
			
			Queue<Integer> min = new PriorityQueue<>(); //최소 힙
			Queue<Integer> max = new PriorityQueue<>(Collections.reverseOrder()); //최대 힙 
			map = new HashMap<>();
			for(int i=0; i<input; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String operate = st.nextToken();
				
				if(operate.equals("I")) { //삽입
					
					int num = Integer.parseInt(st.nextToken());
					min.add(num);
					max.add(num);
					
					map.put(num, map.getOrDefault(num, 0)+1);
				}else { //삭제
					int what = Integer.parseInt(st.nextToken());
					
					if(map.size()==0) continue;
					if(what == 1) { //최댓값 삭제 
						delete(max);
					}else { // 최솟값 삭제
						delete(min);
					}
				}
			}
			
			if (map.size()==0) {
	            sb.append("EMPTY\n");
	        } else {
	        	int res = delete(max);
	        	sb.append(res+" ");
	        	if(map.size()>0) res = delete(min);
	        	sb.append(res+"\n");
	        }
		}
		System.out.println(sb.toString());
	}//main
	
	static int delete(Queue<Integer> q) {
		int res = 0;
		while(true) {
			res = q.poll();
			
			int cnt = map.getOrDefault(res, 0);
			if(cnt ==0) continue;
			
			if(cnt ==1) {
				map.remove(res);
			}
			else {
				map.put(res, cnt-1);
			}
			break;
		}
		
		return res;
	}//delete
}//class
