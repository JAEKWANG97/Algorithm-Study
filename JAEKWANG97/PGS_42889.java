import java.util.Arrays;

public class Solution {
    public int[] solution(int N, int[] stages) {
        int[] stageReached = new int[N + 1];  // 각 스테이지에 도달한 사용자 수
        int[] stageNotCleared = new int[N + 1];  // 각 스테이지를 클리어하지 못한 사용자 수
        
        for (int stage : stages) {
            for (int i = 1; i <= stage && i <= N; i++) {
                stageReached[i]++;
            }
            if (stage <= N) {
                stageNotCleared[stage]++;
            }
        }
        
        StageFailureRate[] failureRates = new StageFailureRate[N];
        for (int i = 1; i <= N; i++) {
            double failureRate = (stageReached[i] > 0) ? (double) stageNotCleared[i] / stageReached[i] : 0;
            failureRates[i - 1] = new StageFailureRate(i, failureRate);
        }
        
        Arrays.sort(failureRates, (a, b) -> {
            if (b.failureRate != a.failureRate) {
                return Double.compare(b.failureRate, a.failureRate);
            }
            return Integer.compare(a.stage, b.stage);
        });
        
        int[] result = new int[N];
        for (int i = 0; i < N; i++) {
            result[i] = failureRates[i].stage;
        }
        
        return result;
    }

    class StageFailureRate {
        int stage;
        double failureRate;

        StageFailureRate(int stage, double failureRate) {
            this.stage = stage;
            this.failureRate = failureRate;
        }
    }
}
