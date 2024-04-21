import java.util.*;

class PGS_17684 {
    public static ArrayList<Integer> list;
    public static HashMap<String, Integer> map;

    public int[] solution(String msg) {

        list = new ArrayList<>();
        map = new HashMap<>();
        int idx = 1;
        while (idx <= 26) {
            map.put(String.valueOf((char) ('A' + idx - 1)), idx++);
        }

        helper(msg);
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }

    public void helper(String msg) {

        if (msg.isEmpty())
            return;

        String w = "";
        int max = Integer.MIN_VALUE;
        for (String key : map.keySet()) {
            if (msg.startsWith(key)) {
                max = Math.max(max, key.length());
                w = (max == key.length() ? key : w);
            }
        }
        list.add(map.get(w));

        msg = msg.replaceFirst(w, "");

        if (msg.length() > 0) {
            String c = msg.substring(0, 1);
            map.put(w + c, idx++);
        }

        helper(msg);
    }
}