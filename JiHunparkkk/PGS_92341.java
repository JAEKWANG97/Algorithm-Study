import java.util.*;

class Solution {
    
    private static class Parking implements Comparable<Parking>{
        int time, totalTime, num;
        int price;
        String status;
        
        public Parking(int num, int time, String status){
            this.num = num;
            this.time = time;
            this.status = status;
        }
        
        public void outParking(int minute){
            totalTime += minute - time;
        }
        
        public void calPrice(int[] fees){
            if(fees[0] >= totalTime){
                price = fees[1];
                return;
            }
            // double ceil = Math.ceil((totalTime - fees[0]) * 1.0 / fees[2]);
            price = fees[1] + (int)Math.ceil((totalTime - fees[0]) * 1.0 / fees[2]) * fees[3];
        }
        
        @Override
        public int compareTo(Parking o1){
            return num - o1.num;
        }
    }
    
    public int[] solution(int[] fees, String[] records) {
        
        Map<Integer, Parking> parking = new HashMap<>();
        for(int i = 0; i < records.length; i++){
            String[] info = records[i].split(" ");
            String[] time = info[0].split(":");
            int minute = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
            int num = Integer.parseInt(info[1]);
            String status = info[2];
            
            if(parking.get(num) == null){
                parking.put(num, new Parking(num, minute, "IN"));
            }
            Parking now = parking.get(num);
            now.status = status;

            if(status.equals("IN")){
                now.time = minute;
            }
            if(status.equals("OUT")){
                now.outParking(minute);
            }
        }
        
        //출차 기록이 없는 차량 처리
        //요금 계산
        List<Parking> result = new ArrayList<>();

        for(int key : parking.keySet()){
            Parking park = parking.get(key);
            
            if(park.status.equals("IN")){
                park.outParking(23*60 + 59);
            }
            park.calPrice(fees);

            result.add(park);
        }
        
        Collections.sort(result);
        int[] answer = new int[result.size()];
        for(int i = 0; i < result.size(); i++){
            answer[i] = result.get(i).price;
        }
        
        return answer;
    }
}
