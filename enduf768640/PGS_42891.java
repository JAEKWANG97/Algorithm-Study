import java.util.*;

class PGS_42891 {

    public int solution(int[] food_times, long k) {
        long food_sum = 0;
        for (int i = 0; i < food_times.length; i++) {
            food_sum += food_times[i];
        }

        if (food_sum <= k) return -1;

        PriorityQueue<Food> pq = new PriorityQueue<>(Comparator.comparingInt(Food::getTime));
        for (int i = 0; i < food_times.length; i++) {
            pq.offer(new Food(food_times[i], i + 1));
        }

        long total = 0;
        long previous = 0;
        long length = food_times.length;

        while (total + ((pq.peek().getTime() - previous) * length) <= k) {
            int now = pq.poll().getTime();
            total += (now - previous) * length;
            length -= 1;
            previous = now;
        }

        ArrayList<Food> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(pq.poll());
        }

        Collections.sort(result, Comparator.comparingInt(food -> food.getIdx()));

        return result.get((int) ((k - total) % length)).getIdx();
    }

    private static class Food {

        private int time;
        private int idx;

        public Food(int time, int idx) {
            this.time = time;
            this.idx = idx;
        }

        public int getTime() {
            return time;
        }

        public int getIdx() {
            return idx;
        }
    }
}