class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int maxTime = -1;

        m = m.replace("C#","c")
                .replace("D#","d")
                .replace("F#","f")
                .replace("G#","g")
                .replace("A#","a");

        for(int i = 0; i < musicinfos.length; i++){
            String[] temp = musicinfos[i].split(",");

            String[] time = temp[0].split(":");

            int start = Integer.valueOf(time[0]) * 60 + Integer.valueOf(time[1]);

            time = temp[1].split(":");

            int end = Integer.valueOf(time[0]) * 60 + Integer.valueOf(time[1]);

            int play = end - start;

            temp[3] = temp[3].replace("C#","c")
                    .replace("D#","d")
                    .replace("F#","f")
                    .replace("G#","g")
                    .replace("A#","a");

            String melody = temp[3];

            if(play <= temp[3].length()){
                melody = temp[3].substring(0,play);
            }else{
                for(int j = 0; j < play/temp[3].length(); j++){
                    melody += temp[3];
                }
                melody += temp[3].substring(0,play % temp[3].length());
            }


            if(melody.contains(m) && play > maxTime){
                maxTime = play;
                answer = temp[2];
            }
        }
        return answer;
    }
}