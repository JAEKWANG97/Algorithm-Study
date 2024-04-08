import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        int j=0;
        while (st.hasMoreTokens()) {
            arr[j++] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        solution(n, arr);
    }

    public static void solution(int n, int[] arr) {

        int lt = 0;
        int rt = n-1;
        int max = Integer.MAX_VALUE;
        int r_lt = 0, r_rt = 0;

        while (lt < rt) {
            int sum = arr[lt] + arr[rt];
            int abs = Math.abs(arr[lt] + arr[rt]);
            if (abs < max) {
                max = abs;
                r_lt = lt;
                r_rt = rt;
            }
            if(sum>0){
                rt--;
            }else{
                lt++;
            }
        }

        System.out.println(arr[r_lt] + " " + arr[r_rt]);
    }
}

