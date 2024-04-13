import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static int n,answer;
	private static int[] people;	//인구 수
	private static final List<List<Integer>> areas = new ArrayList<>();	//지역 관계
	private static boolean[] choiced;	//팀 나누기
	private static boolean[] visited;	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		people = new int[n+1];
		
		st = new StringTokenizer(br.readLine());
		areas.add(new ArrayList<>());	//0번 인덱스 ~ n번 인덱스까지 초기화
		for(int i=1;i<=n;i++) {
			people[i] = Integer.parseInt(st.nextToken());
			areas.add(new ArrayList<>());
		}
		
	
		for(int i=1;i<=n;i++) {
			st = new StringTokenizer(br.readLine());
			int areaCnt = Integer.parseInt(st.nextToken());
			for(int j=0;j<areaCnt;j++) {
				int num = Integer.parseInt(st.nextToken());
				areas.get(i).add(num);
			}
		}
		
		answer = Integer.MAX_VALUE;
		choiced = new boolean[n+1];
		choiceArea(1);
		
		if(answer==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(answer);			
		}
	}

	private static void choiceArea(int index) {
		//로직 검사
		visited = new boolean[n+1];
		solution();
		
		if(index == choiced.length) {
			return;
		}
		
		choiced[index] = true;
		choiceArea(index+1);
		choiced[index] = false;
		choiceArea(index+1);
	}
	
	private static void solution() {
		//반복문 돌리다가 true가 나오면 true 끼리 방문
		//false가 나오면 false끼리 방문
		int teamCnt=0;
		for(int i=1;i<choiced.length;i++) {
			if(choiced[i] && !visited[i]) {
				//i부터 방문해서 같은 choiced값으로 간다.
				visitArea(i);
				teamCnt++;
			}else if(!choiced[i] && !visited[i]) {
				visitArea(i);
				teamCnt++;
			}
		}
		
		//구역이 두 개인지 확인
		//가능한 방법이면 choiced배열을 이용해 각 팀별 인구차이를 계산
		if(teamCnt!=2) return;
		
		int teamA,teamB;
		teamA=teamB=0;
		for(int i=1;i<choiced.length;i++) {
			if(choiced[i]) teamA+=people[i];
			else teamB+=people[i];
		}
				
		answer = Math.min(answer, Math.abs(teamA-teamB));
	}
	
	private static void visitArea(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(start);	//매개변수는 구역번호
		visited[start] =true;
		
		while(!queue.isEmpty()) {
			int poll = queue.poll();
			
			for(int i=0;i<areas.get(poll).size();i++) {
				int area = areas.get(poll).get(i);
				if(!visited[area] && choiced[poll]==choiced[area]) {
					visited[area] = true;
					queue.offer(area);
				}
			}
		}
	}
}

