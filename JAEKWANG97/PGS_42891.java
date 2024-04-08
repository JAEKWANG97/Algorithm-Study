import java.util.*;

class Solution {
    public int solution(int[] food_times, long k) {
        // 전체 음식을 먹는데 필요한 시간이 k보다 작거나 같으면 더 이상 먹을 음식이 없으므로 -1 반환
        long total = 0;
        for(int time : food_times) total += time;
        if(total <= k) return -1;

        // 음식 시간과 인덱스를 함께 저장할 리스트 생성 (원본 인덱스 유지를 위해)
        PriorityQueue<Food> pq = new PriorityQueue<>();
        for(int i = 0; i < food_times.length; i++) {
            pq.add(new Food(food_times[i], i + 1));
        }

        total = 0; // 총 걸린 시간
        long previous = 0; // 직전에 다 먹은 음식 시간
        long length = food_times.length; // 남은 음식 개수

        // 음식을 하나씩 제거하면서 k시간을 넘어가는지 확인
        while (total + ((pq.peek().time - previous) * length) <= k) {
            int now = pq.poll().time;
            total += (now - previous) * length;
            length -= 1; // 다 먹은 음식 제거
            previous = now; // 이전 음식 시간 업데이트
        }

        // 남은 음식 중에서 몇 번째 음식을 먹어야 하는지 계산
        List<Food> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(pq.poll());
        }

        // 원래 음식 순서대로 정렬
        Collections.sort(result, new Comparator<Food>() {
            public int compare(Food a, Food b) {
                return Integer.compare(a.index, b.index);
            }
        });

        // k초 후에 먹어야 할 음식의 인덱스를 반환
        return result.get((int) ((k - total) % length)).index;
    }

    class Food implements Comparable<Food> {
        int time;
        int index;

        Food(int time, int index) {
            this.time = time;
            this.index = index;
        }

        @Override
        public int compareTo(Food other) {
            return Integer.compare(this.time, other.time);
        }
    }
}
