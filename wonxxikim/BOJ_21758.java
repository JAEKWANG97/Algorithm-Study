import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] honey = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i<N ; i++) {
			honey[i] = Integer.parseInt(st.nextToken());
		}
		
		//누적합
		long[] nusum = new long[N];
		nusum[0]= honey[0];
		for(int i = 1 ; i<N ; i++) {
			nusum[i] = nusum[i-1]+honey[i];
		}
		
		//reverse 누적합
		long[] reverse_nusum = new long[N];
		reverse_nusum[N-1]= honey[N-1];
		for(int i = N-2 ; i>=0 ; i--) {
			reverse_nusum[i] = reverse_nusum[i+1]+honey[i];
		}
    
		long result = 0;
		// case 1. 벌통 오른쪽 고정 & 벌1 왼쪽 고정
		for(int i =1 ; i<N-1 ; i++) {
			result = Math.max(result, (nusum[N-1]-honey[0]-honey[i])+(nusum[N-1]-nusum[i]));
		}
		
		//case 2. 벌통 왼쪽 고정 & 벌2 오른쪽 고정
		for(int i =N-2 ; i>0 ; i--) {
			result = Math.max(result, (reverse_nusum[0]-honey[N-1]-honey[i])+(reverse_nusum[0]-reverse_nusum[i]));
		}
		
		//case 3. 벌1 왼쪽 고정 & 벌2 오른쪽 고정
		for(int i = 1 ; i<N-1 ; i++) {
			result = Math.max(result, (nusum[i]-honey[0])+(reverse_nusum[i]-honey[N-1]));
		}
		System.out.println(result);

	}

}
