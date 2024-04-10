import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); 
		int[] grape = new int[N+1];
		long sum = 0;
		for (int i = 1; i <= N; i++) {
			grape[i] = Integer.parseInt(br.readLine());
			sum += grape[i];
		}
		if (N == 1 || N == 2)
			System.out.println(sum);
		else {
			long answer = 0;
			long dp[] = new long[N+1];
			dp[0] = 0;
			dp[1] = grape[1];
			dp[2] = dp[1] + grape[2];
			for (int i = 3; i <= N; i++) {
				dp[i] =  Math.max(dp[i-1],Math.max(dp[i - 2]+grape[i], dp[i - 3]+grape[i]+grape[i-1]));
				answer = Math.max(answer, dp[i]);
			}
			System.out.println(answer);
		}

	}

}
