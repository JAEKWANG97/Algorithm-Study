import java.util.*;

class Solution {
    final int START_TIME = 540;
    public String solution(int n, int t, int m, String[] timetable) {
        Arrays.sort(timetable);
        int[] mintable = new int[timetable.length];
        mintable = Arrays.stream(timetable).mapToInt(time -> convertTimeToMin(time)).toArray();
        int shuttle = START_TIME, index = 0, con = 0;
        for(int i=0; i<n; i++){
            int remain = m;
            for(int j=index; j<mintable.length; j++){
                if(mintable[j]<=shuttle){
                    index++;
                    remain--;
                    if(remain==0) break;
                }
            }
            if(i==n-1)	con = remain>0 ? shuttle : mintable[index-1]-1;
            shuttle += t;
        }
        return convertMinToTime(con);
    }
    int convertTimeToMin(String time){
        return Integer.parseInt(time.substring(0,2))*60+Integer.parseInt(time.substring(3));
    }
    String convertMinToTime(int min){
        return String.format("%02d:%02d",min/60,min%60);
    }
}
