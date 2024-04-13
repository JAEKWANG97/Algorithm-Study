import java.util.*;

class Solution {
    private static int userCnt, sum;
    private static int[] percentage = new int[]{10, 20, 30, 40};
    public int[] solution(int[][] users, int[] emoticons) {
        
        int[] permutation = new int[emoticons.length];
        nPIn(0, permutation, users, emoticons);
        return new int[]{userCnt, sum};
    }
    
    private void nPIn (int depth, int[] permutation, int[][] users, int[] emoticons) {
        if (depth == permutation.length) {
            int[] cur = sell(permutation, users, emoticons);
            if (cur[0] >= userCnt) {
                if (cur[0] > userCnt) {
                    userCnt = cur[0];
                    sum = cur[1];
                } else if (cur[1] > sum) {
                    sum = cur[1];
                }
            }
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            permutation[depth] = i;
            nPIn(depth + 1, permutation, users, emoticons);
        }

    }
    
    private int[] sell (int[] permutation, int[][] users, int[] emoticons) {
        int totalSell = 0;
        int totalUser = 0;
        
        for (int[] user : users) {
            int userPercentage = user[0];
            int userCost = user[1];
            int cost = 0;
            
            for (int i = 0; i < permutation.length; i++) {
              if (percentage[permutation[i]] >= userPercentage) {
                  cost += emoticons[i] * (100 - percentage[permutation[i]]) * 0.01;
              }  
            }
            
            if (cost >= userCost) {            
                cost = 0;
                totalUser += 1;
            }
            totalSell += cost;
        }
        
        return new int[]{totalUser, totalSell};
    }
}