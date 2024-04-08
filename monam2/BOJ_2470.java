import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2470 { //백준 2470 두 용액 - 60분
	//이분탐색 풀이 이해를 못해서 어쩔 수 없이 투포인터로 해결
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        
        int left = 0;
        int right = n-1;
        int[] result = new int[3]; //차이, left값, right값
        result[0] = Integer.MAX_VALUE;
        
        while (left<right) {
        	
        	int sumPh = arr[left] + arr[right];
        	if (Math.abs(sumPh) <= result[0]) {
        		result[0] = Math.abs(sumPh);
        		result[1] = arr[left];
        		result[2] = arr[right];
        	}
        	
        	if (sumPh > 0) {
        		right--;
        	} else if (sumPh < 0) {
        		left++;
        	} else {
        		break;
        	}
        }
        System.out.println(result[1] + " " + result[2]);
    }//main
}//class
