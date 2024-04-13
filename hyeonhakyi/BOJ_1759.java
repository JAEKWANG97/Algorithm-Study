package ex0226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1759 {
    static int L,C;
    static char[] arr;
    static boolean[] check;
    static String[] ans;
    static String str = "aeiou";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[C];
        check = new boolean[C];
        ans = new String[L];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < C; i++){
            arr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(arr);
        combi(0,0);
    }

    static void combi(int count,int idx){
        if(count == L){
            int mo = 0;
            int ja = 0;
            for(int i = 0; i < L; i++){
                if(str.contains(ans[i])){
                    mo++;
                }else{
                    ja++;
                }
            }
            if(mo >= 1 && ja >= 2){
                for(String i : ans){
                    System.out.print(i);
                }
                System.out.println();
            }
            return;
        }

        for(int i = idx; i < C; i++){
            if(!check[i]){
                check[idx] = true;
                ans[count] = String.valueOf(arr[i]);
                combi(count+1,i+1);
                check[idx] = false;
            }
        }
    }
}
