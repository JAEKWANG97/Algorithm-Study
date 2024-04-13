import java.util.*;

class Solution {
    public int solution(int[] food_times, long k) {
        long sum_value = 0;
        for (int i = 0; i < food_times.length; i++) {
            sum_value += food_times[i];
        }
        if(sum_value <= k) return -1;

        PriorityQueue<Foo> pq = new PriorityQueue<>();
        for (int i = 0; i < food_times.length; i++) {
            // (음식 시간, 음식 번호) 형태로 우선순위 큐에 삽입
            pq.offer(new Foo(food_times[i],i + 1));
        }
        sum_value = 0; // 먹기 위해 사용한 시간
        long prev_time = 0; // 직전에 다먹은 음식 시간
        long len = food_times.length; // 남은 음식의 개수

        // sum_value
        while(sum_value + (pq.peek().time - prev_time) * len <= k) {
            int now = pq.poll().time;
            sum_value += (now - prev_time) * len;
            len -= 1;
            prev_time = now;
        }

        ArrayList<Foo> list = new ArrayList<>();
        while(!pq.isEmpty()) {
            list.add(pq.poll());
        }
        // 음식의 번호 기준으로 정렬
        Collections.sort(list, new Comparator<Foo>() {
            @Override
            public int compare(Foo a, Foo b) {
                return Integer.compare(a.idx, b.idx);
            }
        });
        return list.get((int)((k-sum_value) % len)).idx;
    }
}
class Foo implements Comparable<Foo>{
    int time;
    int idx;

    Foo (int t, int i) {
        this.time = t;
        this.idx = i;
    }
    @Override
    public int compareTo(Foo o) {
        return this.time - o.time;
    }
}