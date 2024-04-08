import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * n은 짝수이고, 각 팀은 n/2명으로 이뤄진다 -> 조합(n C (n/2))
 * 조합 포함 여부는 boolean 배열로 관리 (선택/미선택)
 * 20 C 10이면 대략 20만 좀 안됨 -> 가능할듯
 * 
 * boolean 조합을 순회하며 idx1부터 t/f를 확인하고 map에서 값 읽으면 됨
 */

public class Main_14889 { //백준 14889 스타트와 링크 - 41분 02초

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n, min, map[][], arr[];
	static boolean visited[];
	
	public static void main(String[] args) throws IOException {
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//input
		
		min = Integer.MAX_VALUE;
		
		visited = new boolean[n];
		getComb(0,0);
		System.out.println(min);
		
	}//main
	
	private static void getComb(int cnt, int start) {
		if (cnt==n/2) {
			getAbilityByMap();
			return;
		}
		for (int i = start; i < n; i++) {
			visited[i] = true;
			getComb(cnt+1, i+1);
			visited[i] = false;
		}
	}//getComb
	
	private static void getAbilityByMap() {
		int startScore = 0;
		int linkScore = 0;		
		ArrayList<Integer> startTeam = new ArrayList<>();
		ArrayList<Integer> linkTeam = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			if(visited[i]) startTeam.add(i);
			else linkTeam.add(i);
		}
		
		for (int i = 0; i < (n/2)-1; i++) {
			for (int j = i; j < n/2; j++) {
				startScore += map[startTeam.get(i)][startTeam.get(j)] + map[startTeam.get(j)][startTeam.get(i)];
				linkScore += map[linkTeam.get(i)][linkTeam.get(j)] + map[linkTeam.get(j)][linkTeam.get(i)];
			}
		}
		int gap = Math.abs(startScore - linkScore);
		min = Math.min(min, gap);
	}//getAbilityByMap
}//class
