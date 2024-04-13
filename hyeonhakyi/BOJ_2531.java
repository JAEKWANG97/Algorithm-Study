package ex0301;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2531 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        int[] check = new int[d+1];


        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        int res = 1;
        check[c]++;
        for(int i = 0; i < k; i++){
            if(check[arr[i]] == 0){
                res++;
            }
            check[arr[i]]++;
        }

        int cnt = res;
        for(int i = 1; i < n; i++){
            check[arr[i-1]]--;
            if(check[arr[i-1]] == 0) cnt--;

            if(check[arr[(i+k-1)%n]] == 0) cnt++;
            check[arr[(i+k-1)%n]]++;

            res = Math.max(cnt,res);
        }
        System.out.println(res);
    }//main end
}//class end
