import java.util.*;

class Solution {
    static class Stage {
        int num;
        int success;
        int fail;

        public Stage(int num, int success, int fail) {
            this.num = num;
            this.success = success;
            this.fail = fail;
        }
    }

    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        Stage[] list = new Stage[N + 1];
 
        for(int n=0;n<=N;n++)list[n] = new Stage(n,0,0);
        for(int stage:stages){
            if(stage!=N+1)list[stage].fail++;
            for(int i=1;i<stage;i++)list[i].success++;
        }

        Arrays.sort(list, 1, N + 1, new Comparator<Stage>() {
            @Override
            public int compare(Stage s1, Stage s2) {
                double failRate1 = (s1.success + s1.fail == 0) ? 0 : (double) s1.fail / (s1.success + s1.fail);
                double failRate2 = (s2.success + s2.fail == 0) ? 0 : (double) s2.fail / (s2.success + s2.fail);

                if (failRate1 == failRate2) {
                    return s1.num - s2.num;
                } else {
                    return Double.compare(failRate2, failRate1); 
                }
            }
        });

        for (int i = 0; i < N; i++) {
            answer[i] = list[i + 1].num;
        }

        return answer;
    }
}
