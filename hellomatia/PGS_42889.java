package hellomatia;

import java.util.*;

class PGS_42889 {

    class Stage {
        double rate;
        int num;

        Stage(double rate, int num) {
            this.rate = rate;
            this.num = num;
        }
    }

    public int[] solution(int N, int[] stages) {

        PriorityQueue<Stage> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.rate == o2.rate) {
                return o1.num - o2.num;
            }
            return Double.compare(o2.rate, o1.rate);
        });

        int userNum = stages.length;

        for (int i = 1; i <= N; i++) {
            int stageUserNum = 0;
            for (int j = 0; j < stages.length; j++) {
                if (stages[j] == i) {
                    stageUserNum++;
                }
            }
            if (userNum == 0) {
                pq.offer(new Stage( 0, i));
            } else {
                pq.offer(new Stage((double) stageUserNum / userNum, i));
            }
            userNum -= stageUserNum;
        }

        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            answer[i] = pq.poll().num;
        }

        return answer;
    }
}