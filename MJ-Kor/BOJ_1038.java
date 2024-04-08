package algorithm_study.day_03_13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ_1038 {    
    
    private static ArrayList<Long> list;
    
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        
        if(N <= 10) System.out.println(N);
        else if(N > 1022) System.out.println("-1");
        else {
            for(int i = 0; i < 10; i++) {
                calculate(i, 1);
            }
            Collections.sort(list);
            System.out.println(list.get(N));
        }
    }    
    
    public static void calculate(long num, int idx) {
        if(idx > 10) return;
        list.add(num);
        for(int i = 0; i < num % 10; i++) {
            calculate((num * 10) + i, idx + 1);
        }
    }
}
