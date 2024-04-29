import java.util.*;

class PGS_92334 {
    public int[] solution(String[] id_list, String[] report, int k) {
        List<String> id_aryList = Arrays.asList(id_list);
        Map<Integer, Set<Integer>> map = new HashMap<>();

        for (String re : report) {
            String[] res = re.split(" ");
            int r1 = id_aryList.indexOf(res[0]);
            int r2 = id_aryList.indexOf(res[1]);

            Set<Integer> set = map.get(r2);
            if (set == null) {
                set = new HashSet<>();
            }
            set.add(r1);
            map.put(r2, set);
        }

        int[] answer = new int[id_list.length];
        for (Integer key : map.keySet()) {
            Set<Integer> set = map.get(key);
            if (set.size() >= k) {
                for (Integer val : set) {
                    answer[val]++;
                }
            }
        }

        return answer;
    }
}
