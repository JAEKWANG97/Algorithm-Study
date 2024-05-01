import java.time.*;
import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        
        Map<String,Integer> map = new HashMap<>();
        Map<String,Integer> in = new HashMap<>();
        for(String r : records){
            String time = r.split(" ")[0];
            String name = r.split(" ")[1];
            if(r.split(" ")[2].equals("IN")){
                in.put(name,toInt(time));
            }else{
                int seconds = toInt(time)-in.get(name);
                System.out.println(name+" : "+seconds);
                if(map.containsKey(name)){
                    map.put(name,map.get(name)+seconds);
                }else{
                    map.put(name,seconds);
                }
                in.remove(name);
            }
        }
        List<String> arr = new ArrayList<>(map.keySet());
        for(String i:in.keySet()){
            int second = toInt("23:59")-in.get(i);
            if(!arr.contains(i)){
                arr.add(i);
                map.put(i,second);
            }else{
                map.put(i,map.get(i)+second);
            }
        }
        Collections.sort(arr);
        System.out.println(map.toString());
        for(String str:arr){
            int time = map.get(str);
            if(time<=fees[0]){
                map.put(str,fees[1]);
            }else{
                int fee = fees[1];
                time -= fees[0];
                fee+=(time/fees[2])*fees[3];
                if(time%fees[2]!=0) fee+=fees[3];
                map.put(str,fee);
            }
        }
        
        int answer[] = new int[arr.size()];
        for(int idx=0;idx<arr.size();idx++){
            answer[idx]=map.get(arr.get(idx));
        }
        return answer;
    }
    public int toInt(String time){
        int h = Integer.parseInt(time.split(":")[0]);
        int m = Integer.parseInt(time.split(":")[1]);
        return h*60+m;
    }
    public String toString(int time){
        int h = time/60;
        int m = time%60;
        String hour = h<10?"0"+Integer.toString(h):Integer.toString(h);
        String min = m<10?"0"+Integer.toString(m):Integer.toString(m);
        return hour+":"+min;
    }
}
