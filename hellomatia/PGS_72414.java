package hellomatia;

import java.util.*;

class PGS_72414 {

    private List<Log> li = new ArrayList<>();
    private long[] totalTime;

    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";

        int playTime = retTime(play_time);
        int advTime = retTime(adv_time);
        totalTime = new long[playTime + 1];
        for (int i = 0; i < logs.length; i++) {
            String[] sArr = logs[i].split("-");
            li.add(new Log(retTime(sArr[0]), retTime(sArr[1])));
        }
        for (int i = 0; i < li.size(); i++) {
            totalTime[li.get(i).start]++;
            totalTime[li.get(i).end]--;
        }
        for (int i = 1; i < totalTime.length; i++) {
            totalTime[i] = totalTime[i - 1] + totalTime[i];
        }
        for (int i = 1; i < totalTime.length; i++) {
            totalTime[i] = totalTime[i - 1] + totalTime[i];
        }

        long res = totalTime[advTime - 1];
        int idx = 0;
        for (int i = 0; i < playTime - advTime; i++) {
            if (totalTime[i + advTime] - totalTime[i] > res) {
                res = totalTime[i + advTime] - totalTime[i];
                idx = i + 1;
            }
        }
        answer = retStringTime(idx);
        return answer;
    }

    public int retTime(String s) {
        int res = 0;
        String[] sArr = s.split(":");
        res += Integer.parseInt(sArr[0]) * 3600;
        res += Integer.parseInt(sArr[1]) * 60;
        res += Integer.parseInt(sArr[2]);

        return res;
    }

    public String retStringTime(int t) {
        int h = t / 3600;
        int m = (t - 3600 * h) / 60;
        int s = t - 3600 * h - 60 * m;
        return String.format("%02d:%02d:%02d", h, m, s);
    }


    public class Log {
        int start;
        int end;

        public Log(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}