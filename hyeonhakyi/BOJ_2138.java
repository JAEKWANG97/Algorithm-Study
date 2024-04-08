package ex0304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ_2138 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c;

        String s1 = br.readLine();
        String s2 = br.readLine();

        for(int i = 0; i < s1.length(); i++){
            a[i] = s1.charAt(i) - '0';
        }

        for(int i = 0; i < s2.length(); i++){
            b[i] = s2.charAt(i) - '0';
        }

        c = Arrays.copyOf(a,n);
        c[0] = 1 - c[0];
        c[1] = 1 - c[1];

        int answer = result(n,a,b);
        int answer1 = result(n,c,b);
        if(answer1 != -1) answer1++;

        if(answer == -1){
            System.out.println(answer1);
        }else if(answer1 == -1){
            System.out.println(answer);
        }else{
            System.out.println(Math.min(answer,answer1));
        }
    }//main end

    private static int result(int n,int[] a,int[] b){
        int cnt = 0;
        for(int i = 0; i < n-1; i++){
            if(a[i] != b[i]){
                cnt++;
                a[i] = 1 - a[i];
                a[i+1] = 1 - a[i+1];
                if(i != n-2){
                    a[i+2] = 1 - a[i+2];
                }
            }
        }
        if(a[n-1] != b[n-1]){
            return -1;
        }else{
            return cnt;
        }
    }
}//class end
