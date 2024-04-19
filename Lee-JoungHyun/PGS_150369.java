import java.io.*;
import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        
        long answer = 0;
        int lastD = findLast(deliveries, n - 1);
        int lastP = findLast(pickups, n - 1);
        int tmpidx = n - 1;

        while (lastD != -1 || lastP != -1) {
            
            answer += 2 * (Math.max(lastD, lastP) + 1);
            // 1. 배달 부터 생각
            int boxCnt = 0;
            for (int i = lastD; i > -1; i--) {
                if (deliveries[i] + boxCnt > cap) {
                    int tmp = deliveries[i];
                    deliveries[i] -= cap - boxCnt;
                    break;
                } else {
                    boxCnt += deliveries[i];
                    deliveries[i] = 0;
                }
            }
            boxCnt = 0;
            // 2. 수거
            for (int i = lastP; i > -1; i--) {
                if (pickups[i] + boxCnt > cap) {
                    int tmp = pickups[i];
                    pickups[i] -= cap - boxCnt;
                    break;
                } else {
                    boxCnt += pickups[i];
                    pickups[i] = 0;
                }
            }
            lastD = findLast(deliveries, lastD);
            lastP = findLast(pickups, lastP);  
        }

        return answer;
    }
    
    
    public int findLast(int[] arr, int idx) {
        while (idx >= 0) {
            if (arr[idx] != 0)
                return idx;
            idx--;
        }
        return -1;
    }
}