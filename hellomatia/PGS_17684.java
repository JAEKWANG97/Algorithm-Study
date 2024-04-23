package hellomatia;
import java.util.*;

class PGS_17684 {

    public int[] solution(String msg) {

        TreeMap<String, Integer> wordIdxMap = new TreeMap<>();

        List<Integer> answer = new LinkedList<>();

        int idx = 0;
        for (int i = 0; i < 26; i++) {
            wordIdxMap.put(String.valueOf((char) ('A' + i)), ++idx);
        }

        for (int i = 0; i < msg.length(); i++) {
            for (int j = msg.length(); j >= i + 1; j--) {
                String subMsg = msg.substring(i, j);
                if (wordIdxMap.get(subMsg) != null) {
                    answer.add(wordIdxMap.get(subMsg));
                    if (j != msg.length()) {
                        wordIdxMap.put(msg.substring(i, j + 1), ++idx);
                    }
                    i = j - 1;
                    break;
                }
            }
        }

        int[] answerArr = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            answerArr[i] = answer.get(i);
        }

        return answerArr;
    }
}
