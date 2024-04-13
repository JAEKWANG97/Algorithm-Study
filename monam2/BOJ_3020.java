import java.util.Arrays;
import java.util.Scanner;

public class BOJ_3020 { //백준 3202 개똥벌레 - 90분
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int h = sc.nextInt();
		
		int[] top = new int[h+2];
		int[] bottom = new int[h+2];
		for (int i = 1; i <= n/2; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			top[h-b+1]++;
			bottom[a]++;
		}
		
		for (int i = 1; i < h+1; i++) {
			bottom[i] += bottom[i-1];
		}
		
		for (int i = h; i > 0; i--) {
			top[i] += top[i+1];
		}
		
		int min = n;
		int cnt = 0;
		for (int i = 1; i < h+1; i++) {
			int gap = bottom[h] - bottom[i-1] + top[1] - top[i+1];
			if (gap < min) {
				min = gap;
				cnt=1;
			} else if (gap == min) {
				cnt++;
			}
		}
		System.out.println(min + " " + cnt);
	}//main
}//class
