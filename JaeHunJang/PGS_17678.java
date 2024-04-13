import java.util.*;

class PGS_17678 {
	// 셔틀 버스
	PriorityQueue<Integer> pq = new PriorityQueue<>();
	String answer = "";

	public String solution(int n, int t, int m, String[] timetable) {

		for (String temp : timetable) { // 대기열 도착시간 정렬해서 저장
			String[] time = temp.split(":");
			pq.offer(Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]));
		}

		int start = 9 * 60;
		int last = 0;
		int cnt = 0;
		for (int i = 0; i < n; i++, start+=t) {
			int now = 0;
			while (!pq.isEmpty()) {
				if (cnt == m) break;
				now = pq.peek();
				if (now <= start) { // 셔틀 도착시간 전 대기열에 있던 사람
					pq.poll();
					cnt++;
				} else { // 서 있는 사람이 도착시간보다 뒤면 반복문 탈출
					break;
				}
				last = now-1; // 아직 자리가 다 안찼으면 1분일찍와서 탑승
			}
			if (cnt < m) { // 셔틀에 자리가 남아있으면 콘 탑승
				last = start;
			}

			cnt = 0;
		}

		answer = String.format("%02d", last / 60) + ":" + String.format("%02d", last % 60);
		return answer;
	}
}