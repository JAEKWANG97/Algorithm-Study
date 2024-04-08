import java.util.Scanner;

public class Main {
	
	private static int n;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();	
		
		solution(n,0);
		
		System.out.println(sb);
	}
	
	private static void solution(int n, int num) {
		if(n<=0) {
			sb.append(num).append("\n");
			return;
		}
		
		for(int i=1;i<10;i++) {
			int tmp = num*10 + i;
			if(isPrime(tmp)) {
				solution(n-1,tmp);
			}
		}
	}
	
	private static boolean isPrime(int num) {
		if(num<2) return false;
		
		for(int i=2;i*i<=num;i++) {
			if(num%i==0) {
				return false;
			}
		}
		return true;
	}
}

