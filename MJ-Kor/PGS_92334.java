package algorithm_study.april.day_04_29;

import java.util.*;

class ReportUser {
    Map<String, Integer> reportedCnt = new LinkedHashMap<>();
    Map<String, List<String>> reportedUser = new LinkedHashMap<>();
    int k;
    int[] result;
    
    public ReportUser(String[] id_list, int k) {
        init(id_list, k);
    }    
    
    public void solve(String[] report) {
        for(String str : report) {
            String[] splited = str.split(" ");
            String reporter = splited[0];
            String reported = splited[1];
            if(reportedUser.get(reporter).contains(reported)) continue;
            List<String> li = reportedUser.get(reporter);
            li.add(reported);
            reportedUser.put(reporter, li);
            reportedCnt.put(reported, reportedCnt.get(reported) + 1);
        }
        
        int idx = 0;
        for(List<String> value : reportedUser.values()){
            int res = 0;
            for(String user : value) {
                if(reportedCnt.get(user) < this.k) continue;
                res++;
            }
            this.result[idx] = res;
            idx++;
        }
    }
    
    public void init(String[] id_list, int k) {
        this.k = k;
        for(String user : id_list) {
           this.reportedCnt.put(user, 0);
           this.reportedUser.put(user, new LinkedList<>());
           
        }
        this.result = new int[id_list.length];
    }
    
}


class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        ReportUser ru = new ReportUser(id_list, k);
        ru.solve(report);
        return ru.result;
    }
}