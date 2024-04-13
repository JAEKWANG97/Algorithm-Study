package ex0301;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_1644 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[4000001];
        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 2; i <= n; i++){
            arr[i] = i;
        }

        for(int i = 2; i < 4000001; i++){
            if(arr[i] != 0){
                for(int j = i+i; j <= n; j+=i){
                    arr[j] = 0;
                }
            }
        }

        for(int i = 2; i <= n; i++){
            if(arr[i] != 0){
                list.add(i);
            }
        }

        int left = 0;
        int right = 0;
        int ans = 0;
        int sum = 2;
        int size = list.size();

        while(left < size && right < size){
            if(sum == n){
                ans++;
                sum -= list.get(left);
                left++;
            }else if(sum > n){
                sum -= list.get(left);
                left++;
            }else{
                right++;
                if(right >= size)break;
                sum += list.get(right);
            }
        }
        System.out.println(ans);
    }//main end
}//class end
