import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    private static Map<String, List<Integer>> map = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        //조합으로 가능한 경우의 수를 찾음
        for (int i = 0; i < info.length; i++) {
            DFS(info[i].split(" "), "", 0);
        }

        //점수 정렬
        for (List<Integer> list : map.values()) {
            Collections.sort(list);
        }

        //이분탐색으로 인원수 찾기
        for (int i = 0; i < query.length; i++) {
            answer[i] = binarySearch(query[i]);
        }

        return answer;
    }

    private static void DFS(String[] split, String str, int depth) {
        if (depth == 4) {
            int score = Integer.parseInt(split[depth]);
            if (map.containsKey(str)) {
                map.get(str).add(score);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(score);
                map.put(str, list);
            }
            return;
        }

        DFS(split, str + "-", depth + 1);
        DFS(split, str + split[depth], depth + 1);
    }

    private static int binarySearch(String query) {
        //쿼리문 정리
        String[] arr = query.split(" and ");
        int score = Integer.parseInt(arr[3].split(" ")[1]);

        query = arr[0] + arr[1] + arr[2] + arr[3].split(" ")[0];
        if (!map.containsKey(query)) {
            return 0;
        }

        //이분탐색 시작
        List<Integer> list = map.get(query);

        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = (left + right) / 2;

            if (list.get(mid) >= score) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return list.size() - left;
    }
}
