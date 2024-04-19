public class Solution {

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        //끝 부터 움직임
        //끝에 도달했을 땐, 트럭은 텅 빈 상태 (배달 위치 기억)
        //끝 부터 수거 (수거 위치 기억)
        //각 배달 거리 = Math.max(배달위치, 수거위치)
        int del_idx = n - 1;
        int pick_idx = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (deliveries[i] > 0) {
                del_idx = i;
                break;
            }
        }
        
        for (int i = n - 1; i >= 0; i--) {
            if (pickups[i] > 0) {
                pick_idx = i;
                break;
            }
        }

        if(deliveries[del_idx] == 0 && pickups[pick_idx] == 0){
            return answer;
        }
        
        while (del_idx >= 0 && pick_idx >= 0) {
            long time = (Math.max(del_idx, pick_idx) + 1) * 2L;
            answer += time; //왕복

            del_idx = findNextIndex(cap, del_idx, deliveries);
            pick_idx = findNextIndex(cap, pick_idx, pickups);
        }

        while (del_idx >= 0) {
            long time = (del_idx + 1) * 2L;
            answer += time; //왕복

            del_idx = findNextIndex(cap, del_idx, deliveries);
        }

        while (pick_idx >= 0) {
            long time = (pick_idx + 1) * 2L;
            answer += time; //왕복

            pick_idx = findNextIndex(cap, pick_idx, pickups);
        }

        return answer;
    }

    private static int findNextIndex(int cap, int idx, int[] arr) {
        int weight = cap;

        while (weight >= 0 && idx >= 0) {
            int now = arr[idx];
            weight -= now;

            if (weight < 0) {
                arr[idx] = Math.abs(weight);
            } else {
                arr[idx] = 0;
                idx--;
            }
        }
        return idx;
    }
}
