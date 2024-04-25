import java.util.*;

public class PGS_실패율 {
    public int[] solution(int N, int[] stages) {
        Map<Integer, Double> map = new HashMap<>();
        int[] answer = new int[N];
        int[] fail = new int[N + 1];
        int[] success = new int[N + 1];
        for (int i = 0; i < stages.length; i++) {
            for (int j = 0; j < stages[i]; j++) {
                success[j] += 1;
            }
            fail[stages[i] - 1] += 1;
        }
        for (int i = 0; i < N; i++) {
            if (fail[i] == 0 || success[i] == 0) {
                map.put(i + 1, 0.0);
            } else {
                map.put(i + 1, (double) fail[i] / (double) success[i]);
            }
        }
        List<Integer> keyList = new ArrayList<>(map.keySet());
        keyList.sort((o1, o2) -> Double.compare(map.get(o2), map.get(o1)));
        for (int i = 0; i < N; i++) {
            answer[i] = keyList.get(i);
        }
        return answer;
    }
}
