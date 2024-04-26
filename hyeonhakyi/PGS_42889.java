import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] person = new int[N+2];
        int[] number = new int[N+2];
        double[] fail = new double[N];

        for(int i : stages){
            person[i]++;
        }

        int total = stages.length;

        for(int i = 1; i <= N; i++){
            if(total == 0){
                fail[i-1] = 0;
            }else{
                fail[i-1] = (double) person[i] / total;
                total -= person[i];
            }
            number[i+1] = person[i] + number[i];
        }

        int[] answer = new int[N];
        for(int i = 0; i < N; i++){
            int max = 0;
            for(int j = 1; j < N; j++){
                if(fail[j] > fail[max]){
                    max = j;
                }
            }
            answer[i] = max + 1;
            fail[max] = -1;
        }
        return answer;
    }
}