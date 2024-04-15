package hellomatia;

import java.io.*;
import java.util.*;

public class BOJ_1744 {

    public static void main(String[] args) throws IOException {
        new BOJ_1744().solution();
    }

    public void solution() throws IOException{

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numCount = Integer.parseInt(bf.readLine());
        PriorityQueue<Integer> negativePq = new PriorityQueue<>(Comparator.naturalOrder());
        PriorityQueue<Integer> positivePq = new PriorityQueue<>(Comparator.reverseOrder());

        boolean hasZero = false;
        int oneCount = 0;

        for(int i=0; i<numCount; i++){
            int num = Integer.parseInt(bf.readLine());
            if (num>1) {
                positivePq.offer(num);
            }
            else if (num<0) {
                negativePq.offer(num);
            }
            else if (num==0) {
                hasZero = true;
            }
            else {
                oneCount++;
            }
        }

        int maxNum = 0;
        int positiveCount = positivePq.size();
        int negativeCount = negativePq.size();

        for(int i=0; i<positiveCount/2; i++) {
            maxNum += (positivePq.poll()*positivePq.poll());
        }
        for(int i=0; i<negativeCount/2; i++) {
            maxNum += (negativePq.poll()*negativePq.poll());
        }

        if(!hasZero&&!negativePq.isEmpty()){
            maxNum += negativePq.poll();
        }
        if(!positivePq.isEmpty()) {
            maxNum += positivePq.poll();
        }
        maxNum += oneCount;

        bw.write(String.valueOf(maxNum));
        bw.flush();
        bw.close();
    }
}