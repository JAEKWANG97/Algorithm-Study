package hellomatia;

import java.util.*;

class PGS_72411 {

    private boolean[] visited;
    private HashMap<String, Integer> countMap;

    public String[] solution(String[] orders, int[] course) {
        List<String> answerList = new ArrayList<String>();

        for (int c : course) {
            countMap = new HashMap<String, Integer>();
            for (String order : orders) {
                String[] charArr = order.split("");
                Arrays.sort(charArr);
                visited = new boolean[order.length()];
                combination(charArr, 0, c);
            }

            List<Map.Entry<String, Integer>> sortedEntries = new LinkedList<Map.Entry<String, Integer>>(countMap.entrySet());
            Collections.sort(sortedEntries, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o2.getValue() - o1.getValue();
                }
            });

            int max = 0;
            for (Map.Entry<String, Integer> entry : sortedEntries) {
                int count = entry.getValue();
                if (count > 1 && count >= max) {
                    max = count;
                    answerList.add(entry.getKey());
                }
                if (max != 0 && entry.getValue() < max) {
                    break;
                }
            }
        }

        String[] answer = answerList.toArray(new String[answerList.size()]);
        Arrays.sort(answer);

        return answer;
    }

    public void combination(String[] arr, int ptr, int r) {

        if (r == 0) {
            String combStr = "";
            for (int i = 0; i < arr.length; i++) {
                if (visited[i]) {
                    combStr += arr[i];
                }
            }

            if (countMap.containsKey(combStr)) {
                int count = countMap.get(combStr);
                countMap.replace(combStr, count + 1);
            } else {
                countMap.put(combStr, 1);
            }

        } else {

            for (int i = ptr; i < arr.length; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    combination(arr, i + 1, r - 1);
                    visited[i] = false;
                }
            }

        }
    }
}