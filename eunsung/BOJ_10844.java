import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10844 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N+1][10];
		
		for (int i = 0; i < 10; i++) {
			map[0][i] = 1;
		}
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < 10; j++) {
				if(j==0) map[i][j] += map[i-1][j+1]%1000000000;
				else if(j==9) map[i][j] += map[i-1][j-1]%1000000000;
				else {
					map[i][j] += map[i-1][j-1]%1000000000;
					map[i][j] += map[i-1][j+1]%1000000000;
				}
			}
		}
		
		long result = 0;
		for (int i = 1; i < 10; i++) {
			result = (result + map[N-1][i])%1000000000;
		}
		System.out.println(result);
	}

}
