import java.util.PriorityQueue;

class PGS_17683 {
    private class Music {
        int idx;
        int runtime;
        String title;
        String melody;

        public Music(int idx, int runtime, String title, String melody) {
            this.idx = idx;
            this.runtime = runtime;
            this.title = title;
            this.melody = melody;
        }
    }

    String changeOfSharp(String notes) {

        notes = notes.replaceAll("C#", "H")
                .replaceAll("D#", "I")
                .replaceAll("F#", "J")
                .replaceAll("G#", "K")
                .replaceAll("A#", "L");

        return notes;
    }

    public String solution(String m, String[] musicinfos) {

        if ("".equals(m)) return "(None)";

        PriorityQueue<Music> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.runtime > o2.runtime) {
                return -1;
            } else if (o1.runtime == o2.runtime) {
                if (o1.idx < o2.idx) {
                    return -1;
                } else {
                    return 1;
                }
            }
            return 1;
        });

        int idx = 0;
        m = changeOfSharp(m);
        for (String musicinfo : musicinfos) {
            String[] info = musicinfo.split(",");
            String[] time1 = info[0].split(":");
            String[] time2 = info[1].split(":");
            int runtime = (Integer.parseInt(time2[0]) * 60 + Integer.parseInt(time2[1])) - (Integer.parseInt(time1[0]) * 60 + Integer.parseInt(time1[1]));
            String melody = "";

            info[3] = changeOfSharp(info[3]);

            if (info[3].length() > runtime) {
                melody = info[3].substring(0, runtime);
            } else {
                int repeat = runtime / info[3].length();
                int remain = runtime % info[3].length();
                for (int i = 0; i < repeat; i++) {
                    melody += info[3];
                }

                melody += info[3].substring(0, remain);
            }

            if (melody.contains(m)) {
                Music music = new Music(idx, runtime, info[2], melody);
                pq.add(music);
            }

            idx++;
        }

        String answer;
        if (pq.isEmpty()) {
            answer = "(None)";
        } else {
            answer = pq.poll().title;
        }

        return answer;
    }
}