package ex0313;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ_1038 {
    private static ArrayList<Long> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(br.readLine());


        if(x < 10){
            System.out.println(x);
            return;
        }else if(x >= 1023){
            System.out.println(-1);
            return;
        }
        for(int i = 0; i < 10; i++){
            bfs(i,1);
        }

        Collections.sort(list);
        System.out.println(list.get(x));
    }//main end

    private static void bfs(long num,int idx){
        if(idx > 10){
            return;
        }

        list.add(num);

        for(int i = 0; i < num%10; i++){
            bfs(num*10+i,idx+1);
        }
    }
}//class end
