import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static StringBuilder sb = new StringBuilder();
  
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		check(0,N);
		System.out.println(sb);
	}
  
	public static void check(int output,int num) {
		if(num == 0) {
			if(isPrime(output)) sb.append(output).append("\n");
			return;
		}
		for(int i = 0 ; i<10; i++) {
			int next = output*10+i;
			if(isPrime(next)) check(next,num-1);
		}
	}

	public static boolean isPrime(int num) {
		if(num<2) return false;
		for(int i = 2 ; i*i<=num ; i++) {
			if(num%i==0) return false;
		}
		return true;
	}

}
