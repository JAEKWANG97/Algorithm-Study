import java.util.*;

class PGS_72412 {
    static HashMap<String, ArrayList<Integer>> map;

    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        map = new HashMap<>();
        for (String in : info) {
            String[] infoArr = in.split(" ");
            combi("", 0, infoArr);
        }

        int queryIdx = 0;
        for (String q : query) {
            String str = q.replace(" and ", "");
            String[] tmp = str.split(" ");
            Collections.sort(map.get(tmp[0]));
            answer[queryIdx++] = binarySearch(tmp[0], Integer.parseInt(tmp[1]));
        }
        return answer;
    }

    static void combi(String str, int depth, String[] arr) {
        if (depth == 4) {
            int score = Integer.parseInt(arr[4]);
            if (map.containsKey(str)) map.get(str).add(score);
            else {
                ArrayList<Integer> tmp = new ArrayList<>();
                tmp.add(score);
                map.put(str, tmp);
            }
            return;
        }

        combi(str + "-", depth + 1, arr);
        combi(str + arr[depth], depth + 1, arr);
    }

    static int binarySearch(String query, int score) {
        if (!map.containsKey(query)) return 0;
        ArrayList<Integer> tmpList = map.get(query);
        int start = 0, end = tmpList.size() - 1;
        while (start <= end) {
            int mid = (start + end) / 2;

            if (score > tmpList.get(mid)) start = mid + 1;
            else end = mid - 1;
        }
        return tmpList.size() - start;
    }
}