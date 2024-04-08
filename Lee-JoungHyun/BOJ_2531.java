import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2531 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int plateCnt = Integer.parseInt(st.nextToken());
		int sushiCnt = Integer.parseInt(st.nextToken());
		int size = Integer.parseInt(st.nextToken());
		int coupon = Integer.parseInt(st.nextToken());
		
		int belt[] = new int[plateCnt];
		for (int i = 0; i < plateCnt; i++) {
			belt[i] = Integer.parseInt(br.readLine());
		}
		
		int eatSushi[] = new int[3001];
		int sushiTypeCnt = 0;
		for (int i = 0; i < size; i++) {
			if (eatSushi[belt[i]]++ == 0) {
				sushiTypeCnt++;
			}
		}
		if (eatSushi[coupon]++ == 0) sushiTypeCnt++;
		int answer = sushiTypeCnt;
		
		// i 는 뺄것, (i+size)%plateCnt 는 넣을 것
		for (int i = 0; i < plateCnt; i++) {
			if (--eatSushi[belt[i]] == 0) sushiTypeCnt--;
			if (eatSushi[belt[(i+size)%plateCnt]]++ == 0) {
				answer = Math.max(answer, ++sushiTypeCnt);
			}
		}
		System.out.println(answer);	
	}
}
