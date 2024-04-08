import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] value = new int[n];
		for(int i = 0 ; i<n ; i++) {
			value[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(value);
    
		int[] dp = new int[k+1];
		for(int i = 1 ; i<=k ; i++) {
			dp[i] = k+1;
		}
		for(int i = 0 ; i< n ; i++) {
			for(int j = 1 ; j<=k ; j++) {
				if(value[i]<=j) dp[j] = Math.min(dp[j], dp[j-value[i]]+1);
			}
		}
    
		if(dp[k]==k+1)System.out.println(-1);
		else System.out.println(dp[k]);

	}

}
