package hellomatia;

class PGS_42981 {

    public int solution(int[] food_times, long k) {

        long start = 0;
        long end = k;
        long totalTime = 0;

        while (start <= end) {

            long mid = start + (end - start) / 2;

            totalTime = 0;

            for (int foodTime : food_times) {
                totalTime += Math.min(foodTime, mid);
            }

            if (totalTime < k) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        int foodCount = food_times.length;

        for (int i = 0; i < food_times.length; i++) {
            if (food_times[i] <= end) {
                k -= food_times[i];
                food_times[i] = 0;
                foodCount--;
            } else {
                food_times[i] -= end;
                k -= end;
            }
        }

        while (k >= 0 && foodCount > 0) {
            for (int i = 0; i < food_times.length; i++) {
                if (food_times[i] == 0) continue;
                food_times[i]--;
                if (food_times[i] == 0) foodCount--;
                k--;
                if (k < 0) return i + 1;
            }
        }

        return -1;
    }
}