package ex0409;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20055 {
    static int n,k;
    static int[] arr;
    static boolean[] robot;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[2*n];
        robot = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 2*n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(simulation(arr,robot,0));
    }

    private static int simulation(int[] arr,boolean[] robot,int count){
        while(check()) {
            int temp = arr[(2 * n) - 1];
            for (int i = (2 * n) - 1; i > 0; i--) {
                arr[i] = arr[i - 1];
            }
            arr[0] = temp;

            for(int i = n-1; i > 0; i--){
                robot[i] = robot[i-1];
            }
            robot[0] = false;
            robot[n-1] = false;

            for(int i = n-1; i > 0; i--){
                if(robot[i-1] && !robot[i] && arr[i] >= 1){
                    arr[i]--;
                    robot[i] = true;
                    robot[i - 1] = false;
                }
            }

            if(arr[0] > 0){
                robot[0] = true;
                arr[0]--;
            }
            count++;
        }
        return count;
    }

    private static boolean check(){
        int cnt = 0;
        for(int i = 0; i < 2*n; i++){
            if(arr[i] == 0){
                cnt++;
            }
        }
        if(cnt >= k){
            return false;
        }
        return true;
    }
}
