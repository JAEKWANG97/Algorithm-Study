import java.util.Map;
import java.util.TreeMap;

class PGS_92341 {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> map = new TreeMap<>();

        for (String record : records) {
            String[] splitRecord = record.split(" ");
            String[] splitTime = splitRecord[0].split(":");
            int time = (splitRecord[2].equals("IN") ? -1 : 1) * ((Integer.parseInt(splitTime[0]) * 60) + Integer.parseInt(splitTime[1]));
            String carNumber = splitRecord[1];

            map.put(carNumber, map.getOrDefault(carNumber, 0) + time);
        }

        int index = 0, lastTime = (23 * 60) + 59;
        int[] answer = new int[map.size()];
        for (String key : map.keySet()) {
            int time = map.get(key);
            time = time <= 0 ? time + lastTime : time;
            double baseTime = time - fees[0] < 0 ? 0 : time - fees[0];
            int price = fees[1] + (int) Math.ceil(baseTime / fees[2]) * fees[3];
            answer[index++] = price;
        }

        return answer;
    }
}