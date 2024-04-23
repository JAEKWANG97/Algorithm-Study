package hellomatia;

import java.util.*;

class PGS_17683 {

    class Music {

        private String name;
        private String value;

        public Music(String name, String music, int time) {
            this.name = name;

            StringBuilder sb = new StringBuilder();

            int count = time / music.length();
            int sub = time % music.length();

            for (int i = 0; i < count; i++) {
                sb.append(music);
            }
            sb.append(music.substring(0, sub));

            value = sb.toString();
        }

        public boolean contains(String music) {
            return value.length() - value.replace(music, "").length() > 0;
        }
    }


    public String solution(String m, String[] musicinfos) {

        int maxTime = 0;
        String answer = "";

        Music[] musics = new Music[musicinfos.length];

        m = m.replace("C#", "H");
        m = m.replace("F#", "I");
        m = m.replace("G#", "J");
        m = m.replace("A#", "K");
        m = m.replace("D#", "L");
        m = m.replace("B#", "M");

        for (int i = 0; i < musicinfos.length; i++) {
            StringTokenizer st = new StringTokenizer(musicinfos[i], ",");

            StringTokenizer timeToken = new StringTokenizer(st.nextToken(), ":");
            int startTime = Integer.parseInt(timeToken.nextToken()) * 60 + Integer.parseInt(timeToken.nextToken());
            timeToken = new StringTokenizer(st.nextToken(), ":");
            int endTime = Integer.parseInt(timeToken.nextToken()) * 60 + Integer.parseInt(timeToken.nextToken());

            String name = st.nextToken();
            String music = st.nextToken();
            int time = endTime - startTime;

            music = music.replace("C#", "H");
            music = music.replace("F#", "I");
            music = music.replace("G#", "J");
            music = music.replace("A#", "K");
            music = music.replace("D#", "L");
            music = music.replace("B#", "M");

            musics[i] = new Music(name, music, time);

            if (musics[i].contains(m) && maxTime < time) {
                maxTime = time;
                answer = name;
            }
        }

        if (answer.equals("")) {
            return "(None)";
        }

        return answer;
    }
}