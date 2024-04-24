import java.util.Arrays;
import java.util.Comparator;

class Solution {
    String[] sharp = { "C#", "D#", "E#", "F#", "G#", "A#" };	//#이 들어간 경우
	String[] lowercase = { "c", "d", "e", "f", "g", "a" };	//소문자로 표현
    public String solution(String m, String[] musicinfos) {
		String melody = refine(m);
		String[][] infos = refine(musicinfos);

		Arrays.sort(infos, new Comparator<String[]>() {	//runningTime을 기준으로 정렬
			@Override
			public int compare(String[] music1, String[] music2) {
				int runningTimeOfMusic1 = Integer.parseInt(music1[0]);
				int runningTimeOfMusic2 = Integer.parseInt(music2[0]);

				return -(runningTimeOfMusic1 - runningTimeOfMusic2);
			}
		});

		for (int i = 0; i < infos.length; ++i) {	//배열에 멜로디가 있는지 확인
			if (infos[i][2].contains(melody)) {
				return infos[i][1];
			}
		}

		return "(None)";
    }
    
	private String refine(String m) {	//기억한 멜로디에서도 #이 들어간 부분은 소문자로
		String result = m;

		for (int i = 0; i < sharp.length; ++i) {
			result = result.replaceAll(sharp[i], lowercase[i]);
		}

		return result;
	}

	private String[][] refine(String[] musicinfos) {	//입력된 문자를 새롭게 변환
		String[][] infos = new String[musicinfos.length][3];

		for (int i = 0; i < musicinfos.length; ++i) {
			String[] info = musicinfos[i].split(",");

			String[] start = info[0].split(":");
			String[] end = info[1].split(":");
			String title = info[2];
			String code = info[3];
			String music = "";

			for (int j = 0; j < sharp.length; ++j) {	//#이 들어간 경우, 소문자로 변환
				code = code.replaceAll(sharp[j], lowercase[j]);
			}

			int musicLength = code.length();
			int runningTime = getRunningTime(start, end);
			int codeIdx = 0;

			for (int j = 0; j < runningTime; ++j) {
				music += code.charAt(codeIdx);
				codeIdx = (codeIdx + 1) % musicLength;
			}

			infos[i][0] = runningTime + "";
			infos[i][1] = title;
			infos[i][2] = music;
		}

		return infos;
	}

	private int getRunningTime(String[] start, String[] end) {	//runningTime 계산
		int runningTime = 0;

		int startHour = Integer.parseInt(start[0]);
		int startMinute = Integer.parseInt(start[1]);
		int endHour = Integer.parseInt(end[0]);
		int endMinute = Integer.parseInt(end[1]);

		runningTime = (endHour - startHour) * 60 + (endMinute - startMinute);

		return runningTime;
	}
}
