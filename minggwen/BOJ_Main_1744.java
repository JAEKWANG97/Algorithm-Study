import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class BOJ_Main_1744 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<Integer> que = new PriorityQueue<>(Collections.reverseOrder());
		Queue<Integer> m_que = new PriorityQueue<>();
		int N=Integer.parseInt(br.readLine());
		for(int n=0;n<N;n++) {
			int num = Integer.parseInt(br.readLine());
			if(num>=1) que.add(num);
			else m_que.add(num);
		}
		int sum=0;
		while(!que.isEmpty()) {
			if(que.size()==1) {
				sum+=que.poll();
				break;
			}
			if(que.peek()==1) {
				sum+=que.poll();
				continue;
			}
			int a = que.poll();
			int b = que.poll();
			if(a!=1&&b!=1) {
				sum+=a*b;
			}else {
				sum+=a+b;
			}
			
		}
		while(!m_que.isEmpty()) {
			if(m_que.size()==1) {
				sum+=m_que.poll();
				break;
			}
			sum+=m_que.poll()*m_que.poll();
		}
		System.out.println(sum);
	}

}
