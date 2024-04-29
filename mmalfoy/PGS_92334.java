// 각 유저는 한명의 유저 신고 가능
    // 한 유저에 대한 동일한 유저 신고횟수는 boolean
// K번 이상 신고된 유저는 이용 정지 
    // 정지된 유저를 신고한 모든 유저에게 메일 발송
    // 마지막에 한번에 발송

import java.util.*;
class PGS_92334 {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] result = new int[id_list.length];
        boolean[][] table = new boolean[id_list.length][id_list.length];
        HashMap<String, Integer>  map = new HashMap<>();
        for (int i = 0; i < id_list.length; i++) {
            map.put(id_list[i], i);
        }
        for (String rep : report) {
            String[] parsed = rep.split(" ");
            table[map.get(parsed[1])][map.get(parsed[0])] = true;
        }
        
        for (int row = 0; row < id_list.length; row ++) {
            int cnt = 0;
            boolean[] sendIdx = new boolean[id_list.length];
            for (int col = 0; col < id_list.length; col++) {
                if (table[row][col]){
                    cnt += 1;
                    sendIdx[col] = true;
                }
            }
            
            if (cnt >= k) {
                for (int idx = 0; idx < id_list.length; idx++) {
                    if (sendIdx[idx]){
                        result[idx] += 1;
                    }
                }
            }
        }
        
        return result;
    }
}

