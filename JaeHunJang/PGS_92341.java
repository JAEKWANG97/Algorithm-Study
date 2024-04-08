import java.util.*;

class PGS_92341 {
	// 주차 요금 계산
	public int[] solution(int[] fees, String[] records) {
		int[] answer = {};
		Map<Integer, Integer> fee = new HashMap<>(); // 누적시간을 담을 맵
		Map<Integer, String> time = new HashMap<>(); // 입차, 출차 시간을 담을 맵

		for(String rec : records) { // 누적 시간 계산
			String[] temp = rec.split(" ");
			int num = Integer.parseInt(temp[1]); // 차량 번호

			if (temp[2].equals("IN")) {
				time.put(num, temp[0]);
			} else {
				fee.put(num, fee.getOrDefault(num, 0)
						+ calcTime(time.get(num).split(":"), temp[0].split(":"))
				);
				time.put(num, "");
			}
		}


		List<Integer> keys = new ArrayList<>(time.keySet()); // 낮은 차번호로 정렬
		Collections.sort(keys);
		answer = new int[keys.size()];
		int i = 0;
		String[] out = {"23", "59"};
		for (int key : keys) {
			if (!time.getOrDefault(key, "").equals("")) { // 출차되지 않은 차 시간 계산
				fee.put(key, fee.getOrDefault(key, 0) + calcTime(time.get(key).split(":"), out));
			}
			int totalTime = fee.get(key);
			answer[i] = fees[1];
			if (totalTime > fees[0]) { // 기본시간보다 크면 추가 계산
				answer[i] += (totalTime-fees[0]) / fees[2] * fees[3];
				if ((totalTime-fees[0]) % fees[2] > 0) { // 나머지가 있으면 단위요금 1번 추가
					answer[i] += fees[3];
				}
			}
			i++;
		}


		return answer;
	}

	public int calcTime(String[] start, String[] end) {
		int sh = Integer.parseInt(start[0]);
		int sm = Integer.parseInt(start[1]);
		int eh = Integer.parseInt(end[0]);
		int em = Integer.parseInt(end[1]);
		int totalTime = 0;
		if (em < sm) {
			eh--;
			totalTime = (60-sm)+em;
		} else {
			totalTime = em - sm;
		}
		totalTime += (eh - sh)*60;
		return totalTime;
	}
}