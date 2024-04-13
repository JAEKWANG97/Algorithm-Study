import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_2212 { //백준 2212 센서 - 60분
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		ArrayList<Integer> dist = new ArrayList<Integer>();
		for (int i = 1; i < n; i++) {
            dist.add(arr[i] - arr[i - 1]);
        }
		Collections.sort(dist, Collections.reverseOrder());
		int result = 0;
		for (int i = k-1; i < dist.size(); i++) {
			result += dist.get(i);
		}
		System.out.println(result);
	}//main
}//class
