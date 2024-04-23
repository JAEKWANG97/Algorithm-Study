import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    static class Music implements Comparable<Music> {
        String title;
        int playTime;
        String melody;
        String fullMelody;
        String start;
        String end;

        public Music(String title, int playTime, String melody, String start, String end, String fullMelody) {
            this.title = title;
            this.playTime = playTime;
            this.melody = melody;
            this.start = start;
            this.end = end;
            this.fullMelody = fullMelody;
        }

        @Override
        public int compareTo(Music o) {
            if (this.playTime != o.playTime) {
                return o.playTime - this.playTime;
            }
            return 0;
        }
    }

    static List<Music> musicList = new ArrayList<>();

    public String solution(String m, String[] musicinfos) {
        splitMusic(musicinfos);
        String result = getResult(adjustMelody(m));
        return result.equals("") ? "(None)" : result;
    }

    private void splitMusic(String[] musicinfos) {
        for (String musicinfo : musicinfos) {
            String[] music = musicinfo.split(",");
            String title = music[2];
            String melody = adjustMelody(music[3]);
            String start = music[0];
            String end = music[1];
            int playTime = getPlayTime(start, end);
            String fullMelody = getFullMelody(melody, playTime);
            musicList.add(new Music(title, playTime, melody, start, end, fullMelody));
        }
    }

    private static int getPlayTime(String start, String end) {
        String[] startTime = start.split(":");
        String[] endTime = end.split(":");
        int startMinute = Integer.parseInt(startTime[0]) * 60 + Integer.parseInt(startTime[1]);
        int endMinute = Integer.parseInt(endTime[0]) * 60 + Integer.parseInt(endTime[1]);
        return endMinute - startMinute;
    }

    private static String getFullMelody(String melody, int playTime) {
        StringBuilder sb = new StringBuilder();
        int melodyLength = melody.length();
        for (int i = 0; i < playTime; i++) {
            sb.append(melody.charAt(i % melodyLength));
        }
        return sb.toString();
    }

    private static String adjustMelody(String melody) {
        return melody.replaceAll("C#", "c")
                     .replaceAll("D#", "d")
                     .replaceAll("F#", "f")
                     .replaceAll("G#", "g")
                     .replaceAll("A#", "a")
                     .replaceAll("B#", "b");
    }


    private String getResult(String m) {
        Collections.sort(musicList);
        for (Music music : musicList) {
            if (music.fullMelody.contains(m)) {
                return music.title;
            }
        }
        return "";
    }
}
