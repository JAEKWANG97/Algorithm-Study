package ex0417;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.StringTokenizer;

public class BOJ_13701 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        BitSet set = new BitSet(333554432);

        while(st.hasMoreTokens()){
            int n = Integer.parseInt(st.nextToken());

            if(!set.get(n)){
                set.set(n);
                sb.append(n + " ");
            }
        }
        System.out.print(sb);
    }//main end
}//class end
