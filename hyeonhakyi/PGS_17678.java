package ex0316;

import java.util.Arrays;

public class PGS_17678 {
    private int time_to_minute(String time){
        String[] time_split = time.split(":");
        int h = Integer.valueOf(time_split[0]);
        int m = Integer.valueOf(time_split[1]);
        return h * 60 + m;
    }

    private String minute_to_time(int minute){
        String h = String.format("%02d",minute/60);
        String m = String.format("%02d",minute%60);
        return h + ":" + m;
    }
    public String solution(int n, int t, int m, String[] timetable) {
        int[] minutetable = new int[timetable.length];
        for(int i = 0; i < minutetable.length; i++){
            minutetable[i] = time_to_minute(timetable[i]);
        }
        Arrays.sort(minutetable);

        int answer = 0;
        int index = 0;
        int busCnt = 0;
        int busTime = time_to_minute("09:00");

        while(n > 0){
            while(index + busCnt < minutetable.length && busCnt < m && minutetable[index + busCnt] <= busTime){
                busCnt++;
            }

            if(busCnt < m) {
                answer = busTime;
            }else{
                answer = minutetable[index + busCnt -1] - 1;
            }
            index += (busCnt);
            busCnt = 0;
            busTime += t;
            n--;
        }
        return minute_to_time(answer);
    }
}
