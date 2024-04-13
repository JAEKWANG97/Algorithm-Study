import java.util.Scanner;

public class BOJ_2023 { //백준 2023 신기한 소수 - 50분
	static int[] one = {2, 3, 5, 7};
	static int[] twos = {1, 3, 5, 7, 9};
	static int n;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		n = sc.nextInt();
		
		for (int i = 0; i < one.length; i++) {
			dfs(0, one[i]);
		}
		System.out.println(sb.toString());
	}
	
	public static void dfs(int cnt, int val) {
		if(!isPrime(val)) { //소수가 아니면 종료
			return;
		}
		if (cnt==n-1) {
			sb.append(val).append("\n");
			return;
		}
		for (int i = 0; i < twos.length; i++) {
			dfs(cnt+1, val*10 + twos[i]);
		}
	}
	
	
	//소수 판별 함수. 소수 -> true / 소수x - > false
	private static boolean isPrime(int n) {
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n%i==0) return false;
		}
		return true;
	}
}
