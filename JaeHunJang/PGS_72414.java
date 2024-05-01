
// 광고 삽입 / 60분
class PGS_72414 {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        
        int[] times = new int [360000];
        int playTime = parseSec(play_time);
        int advTime = parseSec(adv_time);
        
        for (int i = 0; i < logs.length; i++) {
            String[] time = logs[i].split("-");
            int start = parseSec(time[0]);
            int end = parseSec(time[1]);
            
            for (; start < end; start++) {
                times[start]++;
            }
        }
        
        int maxIdx = 0;
        long maxTime = 0;
        long totalTime = 0;
        for (int i = 0; i < advTime; i++) {
            totalTime += times[i];
        }
        maxTime = totalTime;
        for (int i = advTime; i < playTime; i++) {
            totalTime += times[i];
            totalTime -= times[i-advTime];
            if (maxTime < totalTime) {
                maxIdx = i - advTime + 1;
                maxTime = totalTime;
            }
        }
        
        answer = String.format("%02d:%02d:%02d", (maxIdx / 3600), ((maxIdx % 3600) / 60) , ((maxIdx % 3600) % 60));
        
        return answer;
    }
    
    int parseSec(String t) {
        String[] time = t.split(":");
        
        int parseTime = Integer.parseInt(time[0]) * 3600 + Integer.parseInt(time[1]) * 60 + Integer.parseInt(time[2]);
        
        return parseTime;
    }
}