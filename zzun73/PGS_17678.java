import java.util.*;

class PGS_17678 {
    public String solution(int n, int t, int m, String[] timetable) {
        int answer = 0;
        int startTime = 9 * 60;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        List<Integer>[] list = new ArrayList[n];

        for (int i = 0; i < timetable.length; i++) {
            int hh = Integer.parseInt(timetable[i].split(":")[0]);
            int mm = Integer.parseInt(timetable[i].split(":")[1]);
            int time = hh * 60 + mm;

            pq.add(time);
        }

        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();

            while (!pq.isEmpty()) {
                int arrivedTime = pq.poll();
                if (arrivedTime <= startTime && list[i].size() < m) {
                    list[i].add(arrivedTime);
                } else {
                    pq.add(arrivedTime);
                    break;
                }
                answer = arrivedTime - 1;
            }
            startTime += t;

        }
        if (list[n - 1].size() < m) {
            answer = startTime - t;
        }
        String hh = String.format("%02d", answer / 60);
        String mm = String.format("%02d", answer % 60);
        return hh + ":" + mm;
    }
}