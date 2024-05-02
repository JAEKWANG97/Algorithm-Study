public class PGS_광고삽입 {

    public String solution(String play_time, String adv_time, String[] logs) {
        int playTime = toTime(play_time);
        int advTime = toTime(adv_time);
        int[] times = new int[360000];
        for (String log : logs) {
            String[] tmp = log.split("-");
            int startTime = toTime(tmp[0]);
            int endTime = toTime(tmp[1]);
            for (int i = startTime; i < endTime; i++) {
                times[i]++;
            }
        }

        int max = 0;
        long total = 0;
        for (int i = 0; i < advTime; i++) {
            total += times[i];
        }
        long mTotal = total;
        for (int i = advTime; i < playTime; i++) {
            total += times[i] - times[i - advTime];
            if (total > mTotal) {
                mTotal = total;
                max = i - advTime + 1;
            }
        }

        return toResult(max);
    }

    public int toTime(String time_str){
        //HH:MM:SS 형태로 들어오게됨
        String[] arr= time_str.split(":");
        int time= Integer.parseInt(arr[0])*3600
                +Integer.parseInt(arr[1])*60+Integer.parseInt(arr[2]);
        return time;
    }

    public String toResult(int time){
        String hh, mm, ss;
        hh= (time/3600)>9? (time/3600)+"":"0"+time/3600;
        time%=3600;
        mm= (time/60)>9? (time/60)+"":"0"+time/60;
        time%=60;
        ss= time>9? time+"":"0"+time;

        return hh+":"+mm+":"+ss;
    }
}