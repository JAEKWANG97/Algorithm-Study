
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //동굴의 길이
		int H = Integer.parseInt(st.nextToken()); //동굴의 높이
		int[] bot = new int[H+2]; //석순
		int[] top = new int[H+2]; //종유석
		for(int i = 0 ; i<N/2 ; i++) {
			bot[Integer.parseInt(br.readLine())]++;
			top[H-Integer.parseInt(br.readLine())+1]++;
		}
		for(int i = 1 ;i<=H ; i++) {
			bot[i]+=bot[i-1];
		}
		for(int i = H ; i>=1 ; i--) {
			top[i] += top[i+1];
		}

		int min = Integer.MAX_VALUE;
		int count = 0;
		for(int i = 1 ; i<=H ; i++) {
			int obs = (bot[H]-bot[i-1])+(top[1]-top[i+1]);
			if(obs<min) {
				min = obs;
				count = 1;
			}else if(obs==min) count++;
		}

		System.out.println(min+" "+count);

	}

}
