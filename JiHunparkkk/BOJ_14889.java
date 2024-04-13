import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	private static int N,answer,result;
	private static int[][] board;
	private static boolean[] team;
	private static List<Integer> A;
	private static List<Integer> B;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		team = new boolean[N];
		answer = Integer.MAX_VALUE;
		choiceTeam(0,0);
		System.out.println(answer);
	}
	
	private static void choiceTeam(int start,int depth) {
		if(depth==N/2) {
			getAbility();
			answer = Math.min(answer, result);
			return;
		}
		
		for(int i=start;i<N;i++) {
			if(!team[i]) {
				team[i] = true;
				choiceTeam(i+1,depth+1);
				team[i] = false;
			}
		}
	}
	
	private static void getAbility() {
		int teamA,teamB;
		teamA = teamB = 0;

		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(i!=j && team[j] && team[i]) {
					teamA += board[i][j];
				}else if(i!=j && !team[j] && !team[i]){
					teamB += board[i][j];
				}
			}
		}
	
		result = Math.abs(teamA-teamB);
	}
}

