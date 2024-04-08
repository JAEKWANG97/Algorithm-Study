import java.util.*;

class Solution {
    
    static int[] discount = {10, 20, 30, 40};
    static int[] emoticonsDiscount;
    static int maxPlus = 0, maxCost = 0;
    
    public int[] solution(int[][] users, int[] emoticons) {
        
        emoticonsDiscount = new int[emoticons.length];
        makeDiscount(0, emoticons.length, users, emoticons);
        int[] answer = {maxPlus, maxCost};
                                     
        return answer;
    }
    
    private void makeDiscount(int length, int target, int[][] users, int[] emoticons) {
        if (length == target) {
            calc(users, emoticons);
           return;
        }
        for (int i = 0; i < 4; i++) {
            emoticonsDiscount[length] = discount[i];
            makeDiscount(length+1, target, users, emoticons);
        }               
    }
    private void calc(int[][] users, int[] emoticons) {
        int plus = 0;
        int[] userCost = new int[users.length];
        for (int i = 0; i < users.length; i++) {
            for (int j = 0; j < emoticons.length; j++) {
                if (users[i][0] <= emoticonsDiscount[j]) {
                    userCost[i] += emoticons[j] * ((100-emoticonsDiscount[j])/100.0);
                    if (userCost[i] >= users[i][1]) {
                        userCost[i] = 0;
                        plus++;
                        break;
                    }
                }
            }
            
        }
        int cost = Arrays.stream(userCost).sum();
        if (maxPlus < plus || (maxPlus == plus && maxCost < cost)) {
            maxPlus = plus;
            maxCost = cost;
        }
    }
    
}