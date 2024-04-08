import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_Main_2023 {
	static int n;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n= Integer.parseInt(br.readLine());
		checkPrime(0,0);
	}
	private static void checkPrime(int cnt,int result) {
		if(!isPrime(result)) return;
		if(cnt>1) {
			isPrime(result/(int)Math.pow(10, cnt-1));
		}

		if(cnt==n) {
			System.out.println(result);
			return;
		}
		for(int i = 1; i<10;i++) {
			checkPrime(cnt+1,result*10+i);
		}
		
	}
	private static boolean isPrime(int num) {
		if(0<num&&num<2) return false;
		for(int i=2; i*i<=num;i++) {
			if(num%i==0) {
				return false;
			}
		}
		return true;
	}

}