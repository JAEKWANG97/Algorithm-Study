package ex0412;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2343 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        int start = 0;
        int end = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            if(start < arr[i]){
                start = arr[i];
            }
            end = end + arr[i];
        }

        while(start <= end){
            int mid = (start + end)/2;
            int sum = 0;
            int count = 0;
            for(int i = 0; i < n; i++){
                if(sum + arr[i] > mid){
                    count++;
                    sum = 0;
                }
                sum = sum + arr[i];
            }
            if(sum != 0) count++;
            if(count > m){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        System.out.println(start);
    }//main end
}//class end
