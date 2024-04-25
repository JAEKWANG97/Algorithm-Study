import java.util.*;

public class Solution {
    public static int[] solution(int N, int[] stages) {
        List<double[]> answer = new ArrayList<>();
        int player = stages.length;

        for (int stage = 1; stage <= N; stage++) {
            int num = 0;
            for (int s : stages) {
                if (s == stage)
                    num++;
            }

            if (player <= 0)
                answer.add(new double[]{stage, 0});
            else
                answer.add(new double[]{stage, (double) num / player});

            player -= num;
        }

        Collections.sort(answer, new Comparator<double[]>() {
            @Override
            public int compare(double[] a, double[] b) {
                return Double.compare(b[1], a[1]);
            }
        });

        int[] result = new int[N];
        for (int i = 0; i < answer.size(); i++) {
            result[i] = (int) answer.get(i)[0];
        }

        return result;
    }