import java.util.*;

class PGS_42891 {
    static class Food implements Comparable<Food> {
        int id, time;

        public Food(int id, int time) {
            this.id = id;
            this.time = time;
        }

        public int compareTo(Food f) {
            return this.time - f.time;
        }
    }

    public int solution(int[] food_times, long k) {
        int answer = 0;
        long sumTime = 0;
        PriorityQueue<Food> pq = new PriorityQueue<>();
        for (int i = 0; i < food_times.length; i++) {
            pq.add(new Food(i + 1, food_times[i]));
            sumTime += food_times[i];
        }
        if (sumTime <= k) {
            return -1;
        }

        sumTime = 0; // 먹기 위해 사용한 시간
        long previous = 0; // 직전에 다 먹은 음식 시간
        long length = food_times.length; // 남은 음식의 개수

        while (sumTime + ((pq.peek().time - previous) * length) <= k) {
            int curTime = pq.poll().time;
            sumTime += (curTime - previous) * length;
            previous = curTime;
            length--;
        }
        ArrayList<Food> result = new ArrayList<>(pq);
        Collections.sort(result, (o1, o2) -> o1.id - o2.id);
        return result.get((int) ((k - sumTime) % length)).id;
    }
}