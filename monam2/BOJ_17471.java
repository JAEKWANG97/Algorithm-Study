import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17471 { //백준 17471 게리멘더링 - 100분
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	
	static int n, result, people[];
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static ArrayList<Integer> area1;
	static ArrayList<Integer> area2;
	//statics
	
	public static void main(String[] args) throws IOException {
		n = Integer.parseInt(br.readLine());
		people = new int[n+1]; //각 구역의 인구 수
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		
		result = Integer.MAX_VALUE;
		
		graph.add(new ArrayList<Integer>());
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			
			graph.add(new ArrayList<Integer>());
			for (int j = 0; j < c; j++) {
				int k = Integer.parseInt(st.nextToken());
				graph.get(i).add(k);
			}
		}
		//입력완료
		
		boolean[] isSelected = new boolean[n+1]; //0번은 사용안함
		getPowerSet(1, isSelected);
		
//		System.out.println(result);
		if (result==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(result);
		
	}//end main
	
	//부분집합 구하기
	private static void getPowerSet(int idx, boolean[] isSelected) {
		if(idx==n+1) {
//			System.out.println(Arrays.toString(visited));
//			System.out.println(Arrays.toString(isSelected));
			//true인 도시와 false인 도시를 각각 묶어야 하는데
			//서로 연결 되어있는지 우선 확인해야 함
			area1 = new ArrayList<Integer>();
			area2 = new ArrayList<Integer>();
			
			//area1과 area2로 나누기
			for (int i = 1; i <= n; i++) {
				if (isSelected[i]) area1.add(i); //true인 구역 -> area1
				else area2.add(i); //false인 도시 -> area2
			}
//			System.out.println("area1 : " + area1);
//			System.out.println("area2 : " + area2);
			
			boolean[] visited = new boolean[n+1];
			for (int i : area2) {
				visited[i] = true;
			}
			boolean flag1 = bfs(area1, visited);
			
			visited = new boolean[n+1];
			for (int i : area1) {
				visited[i] = true;
			}
			boolean flag2 = bfs(area2, visited);
//			System.out.println(flag1 + " " + flag2);
			if (flag1 && flag2) {
				int sum1 = 0;
				int sum2 = 0;
				for (int i : area1) {
					sum1 += people[i];
				}
				for (int i : area2) {
					sum2 += people[i];
				}
//				System.out.println(sum1);
//				System.out.println(sum2);
				result = Math.min(result, Math.abs(sum1-sum2));
			}
			
			return;
		}
		isSelected[idx] = true;
		getPowerSet(idx+1, isSelected);
		isSelected[idx] = false;
		getPowerSet(idx+1, isSelected);
	}
	
	private static boolean bfs(ArrayList<Integer> area, boolean[] visited) {
		if (area.isEmpty()) return false; //한 구역이 다 먹을순 x
		if (area.size()==1) return true; //하나이면 무조건 가능
		
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.offer(area.get(0));
		visited[area.get(0)] = true;
		
		while(!q.isEmpty()) {
			int x = q.poll();
			for (int i : graph.get(x)) {
				if (visited[i]) continue;
				
				q.offer(i);
				visited[i] = true;
			}
		}
		
		return isAllCheck(visited);
	}
	
	private static boolean isAllCheck(boolean[] visited) {
		for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                return false;
            }
        }

        return true;

	}
}//end class
