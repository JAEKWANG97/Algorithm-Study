package hellomatia;

import java.util.*;

class PGS_150368 {

    private int[][] users;
    private int[] emoticons;
    private List<int[]> results;

    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;

        results = new ArrayList<>();
        int[] ratio = new int[emoticons.length];

        calcResult(0, emoticons.length, ratio);
        Collections.sort(results, (o1, o2) -> {
            if (o2[0] == o1[0]) {
                return o2[1] - o1[1];
            }
            return o2[0] - o1[0];
        });

        return results.get(0);
    }

    public void calcResult(int idx, int N, int[] ratio) {
        if (idx == N) {
            calcScore(ratio);
            return;
        }

        for (int i = 1; i <= 4; i++) {
            ratio[idx] = i;
            calcResult(idx + 1, N, ratio);
        }
    }

    public void calcScore(int[] ratio) {
        int[] result = new int[2];
        for (int i = 0; i < users.length; i++) {
            int cost = 0;
            for (int j = 0; j < ratio.length; j++) {
                if (users[i][0] <= (ratio[j] * 10)) {
                    cost += (int) ((double) emoticons[j] * 0.1 * (10 - ratio[j]));
                }
            }
            if (users[i][1] <= cost) {
                result[0]++;
            } else {
                result[1] += cost;
            }
        }

        results.add(result);
    }
}