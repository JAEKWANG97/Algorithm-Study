package algorithm_study.day_03_29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_3020_김민주 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int bottom[] = new int[H + 1];
		int top[] = new int[H + 1];
		int bottomSum[] = new int[H + 1];
		int topSum[] = new int[H + 1];
		int min = N;
		int cnt = 0;
		
		
		for (int n = 0; n < N / 2; n++) {
			bottom[Integer.parseInt(br.readLine())]++;
			top[Integer.parseInt(br.readLine())]++;
		}
		
		for (int i = 1; i < H + 1; i++) {
			topSum[i] = topSum[i - 1] + top[i];
			bottomSum[i] = bottomSum[i - 1] + bottom[i];
		}
		
		for (int i = 1; i < H + 1; i++) {
			int hit = 0;
			
			hit += bottomSum[H] - bottomSum[i - 1];
			hit += topSum[H] - topSum[H - i];
			
			if(min > hit) {
				min = hit;
				cnt = 1;
			} else if (min == hit) {
				cnt++;
			}
		}
		
		System.out.println(min + " " + cnt);
		
	}

}
