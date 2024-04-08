import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        int defaultTime = fees[0];
        int defaultFee = fees[1];
        int rateTime = fees[2];
        int rateFee = fees[3];
        
        Arrays.sort(records, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String[] c1 = o1.split(" ");
                String[] c2 = o2.split(" ");
                if (Integer.parseInt(c1[1]) != Integer.parseInt(c2[1])) {
                    return Integer.parseInt(c1[1]) - Integer.parseInt(c2[1]);
                }
                return makeMinute(c1[0]) - makeMinute(c2[0]);
            }
        });


        String[][] tmp = new String[records.length][];
        for (int i = 0; i < records.length; i++) {
            tmp[i] = records[i].split(" ");
        }
        
        if (records.length == 1) {
            int tmp2 = makeMinute("23:59") - makeMinute(tmp[0][0]);
            int[] answer = new int[1];
            if (tmp2 <= defaultTime) answer[0] = defaultFee;
            else {
                int lastTime = tmp2 - defaultTime;

                if (lastTime % rateTime != 0)
                    answer[0] = defaultFee + (lastTime / rateTime + 1) * rateFee;
                else
                    answer[0] = defaultFee + (lastTime / rateTime) * rateFee;
            }
            
            return answer;
        }

        int[] ans = new int[1000];
        int carCnt = 0;
        // false = 전 상태에 들어옴, true = 전상태에 나감
        boolean flag = false;
        for (int i = 1; i < records.length; i++) {
            if (tmp[i][1].equals(tmp[i-1][1])) {
                if (flag) {
                    flag = false;
                }
                else {
                    ans[carCnt] += makeMinute(tmp[i][0]) - makeMinute(tmp[i - 1][0]);
                    flag = true;
                }
            }
            else {
                if (!flag) {
                    ans[carCnt] += makeMinute("23:59") - makeMinute(tmp[i - 1][0]);
                }
                flag = false;
                carCnt++;
            }
 
        }
        
        //System.out.println(ans[0] + " " + ans[1] + " " + ans[2]);
      
        int n = tmp.length - 1;
        if(tmp[n][2].equals("IN")) {
            if (tmp[n][1].equals(tmp[n-1][1]))
                ans[carCnt] += makeMinute("23:59") - makeMinute(tmp[tmp.length - 1][0]);
            else {
                ans[carCnt] += makeMinute("23:59") - makeMinute(tmp[tmp.length - 1][0]);
            }
        }
        
        int[] answer = new int[carCnt + 1];
        for (int i = 0; i < carCnt + 1; i++) {
            if (ans[i] <= defaultTime) answer[i] = defaultFee;
            else {
                int lastTime = ans[i] - defaultTime;

                if (lastTime % rateTime != 0)
                    answer[i] = defaultFee + (lastTime / rateTime + 1) * rateFee;
                else
                    answer[i] = defaultFee + (lastTime / rateTime) * rateFee;
            }
        }      
        
        
        return answer;
    }
    
    public static int makeMinute(String time) {
        String[] t1 = time.split(":");
        return Integer.parseInt(t1[0]) * 60 + Integer.parseInt(t1[1]);
    }

}