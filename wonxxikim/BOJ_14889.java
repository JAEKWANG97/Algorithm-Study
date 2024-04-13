import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14889 {
	static int team[][];
	static int N, result;
	static boolean teamA[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		team = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				team[i][j] = Integer.parseInt(st.nextToken());
			}

		}
		teamA = new boolean[N];
		result = Integer.MAX_VALUE;
		combination(0, 0);
		System.out.println(result);

	}

	static void combination(int start, int cnt) {
		if (cnt == N / 2) {
			int scoreA = 0;
			int scoreB = 0;
			for (int i = 0; i < N; i++) {
				if (teamA[i] == true) {
					for (int j = 0; j < N; j++) {
						if(teamA[j] == true) scoreA+=team[i][j];

					}
				}else {
					for(int j = 0 ; j<N;j++) {
						if(teamA[j] == false) scoreB+=team[i][j];
					}
				}
			}
			if(Math.abs(scoreA-scoreB)<result) result = Math.abs(scoreA-scoreB);
			return;

		}
		for (int i = start; i < N; i++) {
			teamA[i] = true;
			combination(i + 1, cnt + 1);
			teamA[i] = false;
		}
	}

}
