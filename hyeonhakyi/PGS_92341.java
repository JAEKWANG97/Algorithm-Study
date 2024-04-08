package ex0316;

import java.io.IOException;
import java.util.*;

public class PGS_92341 {
    public int[] solution(int[] fees, String[] records){
        int[] answer = {};
        Map<String, Integer> map1 = new HashMap<String, Integer>();
        Map<String, Integer> map2 = new HashMap<String, Integer>();

        int baseTime = fees[0];
        int baseFee = fees[1];
        int partTime = fees[2];
        int partFee = fees[3];

        for(String record : records){
            String[] temp = record.split(" ");
            int time = getRealTime(temp[0]);
            String car = temp[1];
            String io = temp[2];

            if(io.equals("IN")){
                map1.put(car,time);
            }else{
                int carTime1 = map1.get(car);
                map1.remove(car);
                if(map2.containsKey(car)){
                    int carTime2 = map2.get(car);
                    map2.replace(car, carTime2 + time - carTime1);
                } else{
                    map2.put(car, time - carTime1);
                }
            }
        }

        int lastTime = 1439;
        for(String car : map1.keySet()){
            int carTime1 = map1.get(car);
            if(map2.containsKey(car)){
                int carTime2 = map2.get(car);
                map2.replace(car, carTime2 + lastTime - carTime1);
            }else {
                map2.put(car, lastTime - carTime1);
            }
        }

        Object[] sortKey = map2.keySet().toArray();
        Arrays.sort(sortKey);
        answer = new int[sortKey.length];

        for(int i = 0; i < answer.length; i++){
            int result = baseFee;
            String car = String.valueOf(sortKey[i]);

            int val = map2.get(car);
            if(val > baseTime){
                result = (int) (baseFee + Math.ceil((double)(val - baseTime)/partTime) * partFee);
            }
            answer[i] = result;
        }
        return answer;
    }//solution end

    private int getRealTime(String time){
        String[] tem = time.split(":");
        int hour = Integer.parseInt(tem[0]) * 60;
        int minute = Integer.parseInt(tem[1]);
        return hour + minute;
    }//getRealTime end
}//class end
