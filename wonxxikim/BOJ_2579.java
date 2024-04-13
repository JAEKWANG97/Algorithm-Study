import java.io.*;
import java.util.*;

public class BOJ_2579{

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] stairs = new int[T+1];
        int[] sum =new int[T+1];

        for (int t = 1 ; t<(T+1);t++) {
        	stairs[t] = Integer.parseInt(br.readLine());
        }
        int result = 0 ;
        if(T<3) {
        	for(int t = 0 ; t<T+1;t++) {
        		result+=stairs[t];
        	}
        	System.out.println(result);
        	return;
        }
        sum[0] = 0;
        sum[1] = stairs[1];
        sum[2] = stairs[2]+stairs[1];
        for(int t = 3; t<(T+1);t++) {
        	sum[t] = Math.max(stairs[t-1]+sum[t-3],sum[t-2])+stairs[t];
        }


        System.out.println(sum[T]);
        
    }
}