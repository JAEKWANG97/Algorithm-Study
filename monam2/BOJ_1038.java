import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BOJ_1038 { //백준 1038 감소하는 수 - 90분
	
	static int n;
	static ArrayList<Long> arr = new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		
		if (n<=10) {
			System.out.println(n);
			return;
		}
		
		for (int i = 0; i < 10; i++) {
			dfs(i, 1);
		}
		if (n >= arr.size()) {
			System.out.println(-1);
		} else {
			Collections.sort(arr);
			System.out.println(arr.get(n));
		}
		
	}//main
	
	private static void dfs(long num, int value) {
		if (value > 10) return;
		
		arr.add(num);
		
		for(int i=0; i < num%10; i++) {
			dfs((num*10)+i, value+1);
		}
	}//dfs
}//class
