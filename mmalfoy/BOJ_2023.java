import java.io.*;

public class BOJ_2023 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		getPrimes(0,N);
        
        bw.flush();
        bw.close();
        br.close();
    }
	// [0,N) 범위의 소수 판별
    public static void getPrimes(int cmp, int n) throws IOException{
        // 종료 조건
    	if (n == 0) {
            if (isPrime(cmp)) {
            	bw.append(Integer.toString(cmp)).append('\n');
            }
            return;
        }
    	
        for (int i = 0; i < 10; i++) {
            int next = cmp * 10 + i;
            
            if (isPrime(next)) {
            	getPrimes(next, n-1);
            }
        }
    }
    
    public static boolean isPrime(int num) {
        if (num < 2) {
        	return false;
        }

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
            	return false;
            }
        }
        return true;
    }

}
