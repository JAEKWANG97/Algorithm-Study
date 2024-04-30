import java.util.*;
import java.util.stream.*;

// 신고 결과 받기 / 40분
class PGS_92334 {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        int[] counts = new int[id_list.length];
        
        List<String> iList = Arrays.asList(id_list); // 검색을 용이하기 위한 list 변환
        List<String> rList = Arrays.stream(report).distinct().collect(Collectors.toList()); // 중복 제거
        List<String> list[] = new List[id_list.length]; // 신고자가 누군지 찾기
        for (int i = 0; i < list.length; i++) {
            list[i] = new ArrayList();
        }
        for (String r : rList) {
            String name1 = r.split(" ")[0];
            String name2 = r.split(" ")[1];
            
            list[iList.indexOf(name1)].add(name2);
            counts[iList.indexOf(name2)]++;
        }
        for (int i = 0; i < list.length; i++) {
            
            for (int j = 0; j < list[i].size(); j++) {
                if (counts[iList.indexOf(list[i].get(j))] >= k) {
                    answer[i]++;
                }
            }
        }
        
        return answer;
    }
}