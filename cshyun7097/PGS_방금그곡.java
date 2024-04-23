public class PGS_방금그곡 {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int maxTime = -1;
        m = replaceMelody(m);

        for(int i = 0; i < musicinfos.length; i++) {
            String[] temp = musicinfos[i].split(",");
            String[] timeArr = temp[0].split(":");
            int time1 = Integer.valueOf(timeArr[0]) * 60 + Integer.valueOf(timeArr[1]);
            timeArr = temp[1].split(":");
            int time2 = Integer.valueOf(timeArr[0]) * 60 + Integer.valueOf(timeArr[1]);

            int time = time2 - time1;

            temp[3] = replaceMelody(temp[3]);

            String melody = temp[3];

            if(time <= temp[3].length()) {
                melody = temp[3].substring(0, time);
            }
            else {
                for(int j = 0; j < time / temp[3].length(); j++) {
                    melody += temp[3];
                }
                melody += temp[3].substring(0, time % temp[3].length());
            }

            if(melody.contains(m) && time > maxTime) {
                maxTime = time;
                answer = temp[2];
            }
        }

        return answer;
    }

    private static String replaceMelody(String temp) {
        return temp.replace("C#", "c")
                .replace("D#", "d")
                .replace("F#", "f")
                .replace("G#", "g")
                .replace("A#", "a");
    }
}
