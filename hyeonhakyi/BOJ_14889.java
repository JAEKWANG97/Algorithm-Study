package ex0226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14889 {
    static int n,sum1,sum2,min;
    static int[][] arr;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        visited = new boolean[n];

        min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        combi(0,0);
        System.out.println(min);
    }

    static void combi(int count,int idx){
        if(count == n/2){
            diff();
            return;
        }

        for(int i = idx; i < n; i++){
            if(!visited[i]){
                visited[i] = true;
                combi(count+1,i+1);
                visited[i] = false;
            }
        }
    }


    static void diff(){
        sum1 = 0;
        sum2 = 0;
        for(int i = 0; i < n-1; i++){
            for(int j = i+1; j < n; j++){
                if(visited[i] && visited[j]){
                    sum1 += arr[i][j];
                    sum1 += arr[j][i];
                }else if(!visited[i] && !visited[j]){
                    sum2 += arr[i][j];
                    sum2 += arr[j][i];
                }
            }
        }
        int total = Math.abs(sum1 - sum2);

        if(total == 0){
            System.out.println(0);
            System.exit(0);
        }
        min = Math.min(total,min);
        return;
    }
}
