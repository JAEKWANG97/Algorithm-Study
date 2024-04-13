
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2531 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //접시의 수
		int d = Integer.parseInt(st.nextToken()); //초밥 가짓수
		int k = Integer.parseInt(st.nextToken()); //연속해서 먹는 접시의 수
		int c = Integer.parseInt(st.nextToken()); //쿠폰 번호
		
		int[] belt = new int[N+k-1]; // 벨트 위의 초밥 (사이클이 있기 때문에 k-1개 더 넣어주기)
		for(int i = 0 ; i<N ; i++) {
			belt[i] = Integer.parseInt(br.readLine());
		}
		for(int i=0;i<k-1;i++) {
			belt[N+i] = belt[i];
		}
		
		int[] eat = new int[d+1]; // 내가먹은초밥
		eat[c] = 1; //쿠폰의 초밥은 무조건 1개 먹음
		
		int max = 0; //먹을 수 있는 초밥의 최대 가짓수
		//초기값
		for(int i = 0 ; i <k ; i++) {
			eat[belt[i]]++;
		}
		for(int i = 0 ; i<=d ; i++) {
			if(eat[i]>0) max++;
		}
		//max값 갱신
		int before = max;
		for(int i = 1 ; i<N ; i++) {
			if(eat[belt[i-1]]==1) before--;
			eat[belt[i-1]]--;
			if(eat[belt[i+k-1]]==0)before++;
			eat[belt[i+k-1]]++;
			if(before>max) max = before;
			
		}
		
		System.out.println(max);
		
	}

}
