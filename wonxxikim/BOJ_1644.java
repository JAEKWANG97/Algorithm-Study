
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_1644 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); 
		
		int cnt =0; //경우의 수
		if(N == 1)System.out.println(cnt); //N이 1인 경우 -> 0
		
		else {
			//에라토스테네스의 체
			boolean check[] = new boolean[N+1];
			check[0]=check[1] = true;
			for(int i = 2 ; i*i<=N ; i++) {
				if(!check[i]) {
					for(int j = i*i ; j<=N ; j+=i) {
						check[j] = true;
					}
				}
			}
			
			ArrayList<Integer> primelist = new ArrayList<>();
			for(int i = 0 ; i<=N ;i++) {
				if(!check[i])primelist.add(i);
			}
			
			int minidx = 0;
			int maxidx = 0;
			int prime = primelist.get(0);
			while(minidx<=maxidx) {
				if(minidx==primelist.size()-1) {
					if(prime == N) cnt++;
					break;
				}
				if(prime==N) {
					cnt++;
					prime-=primelist.get(minidx);
					minidx++;
				}
				if(prime<N) {
					maxidx++;
					prime+=primelist.get(maxidx);
				}
				if(prime>N) {
					prime-=primelist.get(minidx);
					minidx++;
				}
				
			}
			System.out.println(cnt);
			
		}
		
	}

}
