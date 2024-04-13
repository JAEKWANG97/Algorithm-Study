import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class BOJ_2531 { //백준 2531 회전 초밥 - 30분
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); //초밥 갯수
		int d = Integer.parseInt(st.nextToken()); //초밥 종류
		int k = Integer.parseInt(st.nextToken()); //연속으로 먹기
		int c = Integer.parseInt(st.nextToken()); //쿠폰 초밥 번호
		
		int[] sushi = new int[n];
		int[] visited = new int[d+1];
		
		for (int i = 0; i < n; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		
		int max = 1; //쿠폰 적용 & 벨트위 초밥과 다른 경우를 미리 가정 -> 최대
		visited[c]++;
		
		//윈도우 초기값 설정
		for (int i = 0; i < k; i++) {
			if (visited[sushi[i]] == 0) {
				max++;
			}
			visited[sushi[i]]++;
		}
		
		//윈도우 내 초밥 가짓수
		int cnt = max;
		
		for (int i = 1; i < n; i++) {
			
			//윈도우 가장 왼쪽 초밥 제거
			//i-1인 이유 -> 현재 보는 윈도우의 왼쪽 값임(제거할 값)
			visited[sushi[i-1]]--;
			if (visited[sushi[i-1]] == 0) {
				//초밥[i]의 개수가 0으로 바뀜 -> 윈도우 안에 그 초밥 x
				cnt--;
			}
			
			//윈도우 밖의 초밥을 윈도우에 넣기
			// -> 벨트위 초밥은 계속 돌아가므로 초밥을 다 보면 앞으로 돌아가야함
			if (visited[sushi[(i-1+k)%n]]==0) {
				cnt++;
			}
			visited[sushi[(i-1+k)%n]]++;
			
			max = Math.max(max, cnt);
		}
		
		System.out.println(max);
	}//main
}//class
