import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        int playTime = toIntTime(play_time);
        int advTime = toIntTime(adv_time);
        int[] table = new int[playTime+1];
        for(String log:logs){
            int start = toIntTime(log.split("-")[0]);
            int end = toIntTime(log.split("-")[1]);
            for(int i=start;i<end;i++){
                table[i]++;
            }
        }
        
        
        long totalTime = 0L;
        for(int t=0;t<advTime;t++){
            totalTime = table[t];
        }
        long maxTime = totalTime;
        int maxStart = 0;
        for(int i=advTime;i<playTime;i++){
            totalTime += table[i]-table[i-advTime];
            if(totalTime>maxTime){
                maxTime=totalTime;
                maxStart = i-advTime+1;
            }
        }
        return toStringTime(maxStart);
    }
    private int toIntTime(String str){
        int hours = Integer.parseInt(str.split(":")[0]);
        int minutes = Integer.parseInt(str.split(":")[1]);
        int seconds = Integer.parseInt(str.split(":")[2]);
        return hours*3600+minutes*60+seconds;
    }
    private String toStringTime(int time){
        String str="";
        int hours = time/3600;
        time-=3600*hours;
        int min = time/60;
        time-=60*min;
        String h = hours<10? "0"+Integer.toString(hours):Integer.toString(hours);
        String m = min<10? "0"+Integer.toString(min):Integer.toString(min);
        String s = time<10? "0"+Integer.toString(time):Integer.toString(time);
        return h+":"+m+":"+s;
    }
}
