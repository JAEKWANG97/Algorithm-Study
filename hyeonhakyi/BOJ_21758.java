package ex0413;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_21758 {
    private static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = leftHoney();
        int right = rightHoney();
        int mid = midHoney();

        System.out.println(Math.max(left,Math.max(right,mid)));
    }//main end

    private static int leftHoney(){
        int[] sum = new int[arr.length];

        for(int i = arr.length-2; i >= 1; i--){
            sum[i] = sum[i+1] + arr[i+1];
        }

        int endSum = sum[1];
        int max = 0;

        for(int i = 2; i < arr.length; i++){
            max = Math.max(max, endSum - arr[i] + sum[i]);
        }
        return max;
    }//left end

    private static int rightHoney(){
        int[] sum = new int[arr.length];

        for(int i = 2; i < arr.length; i++){
            sum[i] = sum[i-1] + arr[i -1];
        }

        int endSum = sum[arr.length-1];
        int max = 0;

        for(int i = 1; i < arr.length - 1; i++){
            max = Math.max(max, endSum - arr[i] + sum[i]);
        }
        return max;
    }//right end

    private static int midHoney(){
        int[] leftSum = new int[arr.length];
        int[] rightSum = new int[arr.length];

        for(int i = 2; i < leftSum.length; i++){
            leftSum[i] = leftSum[i - 1] + arr[i];
        }

        for(int i = rightSum.length - 2; i >= 1; i--){
            rightSum[i] = rightSum[i+1] + arr[i];
        }

        int max = 0;

        for(int i = 1; i < arr.length; i++){
            max = Math.max(max,leftSum[i] + rightSum[i]);
        }
        return max;
    }
}//class end
