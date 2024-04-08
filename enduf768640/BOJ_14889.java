import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BOJ_14889 {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N;
	private static int[][] synergy;
	
	private static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		init();
		makeTeam(new int[N / 2], 0, 0);
		printAnswer();
	}
	
	private static void makeTeam(int[] team, int start, int count) {
		if (count == N / 2) {
			answer = Math.min(answer, getSynergyDiff(team));
			return;
		}
		
		for (int i = start; i < N; i++) {
			team[count] = i;
			makeTeam(team, i + 1, count + 1);
		}
	}
	
	private static int getSynergyDiff(int[] team) {
		List<Integer> team1 = Arrays.stream(team)
				.boxed()
				.collect(Collectors.toList());
		
		List<Integer> team2 = IntStream.range(0, N)
				.filter(player -> !team1.contains(player))
				.boxed()
				.collect(Collectors.toList());
		
		return Math.abs(getSynergy(team1) - getSynergy(team2));
	}

	private static int getSynergy(List<Integer> team) {
		int sum = 0;
		
		for (int player1 : team) {
			for (int player2 : team) {
				if (player1 == player2) {
					continue;
				}
				
				sum += synergy[player1][player2];
			}
		}
		
		return sum;
	}

	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		
		synergy = new int[N][N];
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < N; x++) {
				synergy[y][x] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	private static void printAnswer() {
		System.out.println(answer);
	}
}
