package ex0228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[301];
        int[] ans = new int[301];

        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        ans[1] = arr[1];
        ans[2] = arr[2] + arr[1];
        ans[3] = Math.max(arr[2],arr[1]) + arr[3];

        for(int i = 4; i <= n; i++){
            ans[i] = Math.max(arr[i -1] + ans[i-3], ans[i-2]) + arr[i];
        }
        System.out.println(ans[n]);
    }//main end
}//class end
