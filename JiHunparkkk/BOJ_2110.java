import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n,c;
        n = sc.nextInt();
        c = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(Solution(n, c, arr));
    }

    public static int Solution(int n, int c, int[] arr) {
        int answer=0;
        int lt = 1;
        int rt = arr[n - 1];

        Arrays.sort(arr);
        
        while (lt <= rt) {
            int mid = (lt + rt) / 2;

            if (count(mid, arr) >= c) {
                lt = mid + 1;
                answer = mid;
            }else{
                rt = mid - 1;
            }
        }

        return answer;
    }

    public static int count(int mid, int[] arr){
        int cnt=1;
        int ep=0;

        for (int i = 0; i < arr.length; i++) {
            if(arr[i]-arr[ep]>=mid){
                cnt++;
                ep = i;
            }
        }

        return cnt;
    }
}

