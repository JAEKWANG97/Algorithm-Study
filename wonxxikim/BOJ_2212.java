

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); //센서의 수
		int K = Integer.parseInt(br.readLine()); //집중국의 수
		int[] sensor = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i<N ; i++) {
			sensor[i] = (Integer.parseInt(st.nextToken()));
			}
		Arrays.sort(sensor);
		int[] len = new int[N-1];
		for(int i = 0 ; i<N-1; i++) {
			len[i] = (sensor[i+1]-sensor[i]);
		}
		Arrays.sort(len);
		int cnt = 0;
		int result = 0;
		for(int i = N-1-K ; i>=0; i--) {
			result+=len[i];
		}
		System.out.println(result);
	

	}

}
