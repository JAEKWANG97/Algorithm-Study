package ex0415;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ_1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> positive = new PriorityQueue<>((o1,o2) -> {
           return o2 - o1;
        });

        PriorityQueue<Integer> negative = new PriorityQueue<>();

        int zero = 0;

        for(int i = 0; i < n; i++){
            int num = Integer.parseInt(br.readLine());
            if(num > 0) {
                positive.add(num);
            }else if(num < 0){
                negative.add(num);
            }else{
                zero++;
            }
        }

        int answer = 0;
        while(positive.size() > 1){
            int x = positive.poll();
            int y = positive.poll();
            if(x + y > x * y){
                answer += (x+y);
            }else{
                answer += (x*y);
            }
        }

        if(!positive.isEmpty()){
            answer += positive.poll();
        }

        while(negative.size() > 1){
            answer += (negative.poll()*negative.poll());
        }

        if(!negative.isEmpty()){
            if(zero > 0){
                answer += 0;
            }else{
                answer += negative.poll();
            }
        }

        System.out.println(answer);
    }//main end
}//class end
