import java.util.*;

class PGS_42889 {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        double users = stages.length;
        List<double[]> failRate = new ArrayList<>();
        int count = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < stages.length; j++) {
                if (i == stages[j]) {
                    count++;
                }
            }
            if (count == 0) {
                failRate.add(new double[]{i, 0});
                continue;
            }
            failRate.add(new double[]{i, count / users});
            users -= count;
            count = 0;
        }

        failRate.sort((o1, o2) -> Double.compare(o2[1], o1[1]));

        for (int i = 0; i < failRate.size(); i++) {
            answer[i] = (int) failRate.get(i)[0];
        }

        return answer;
    }
}