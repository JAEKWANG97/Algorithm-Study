import java.util.*;
class Solution {
    static int[] discount = {10,20,30,40};
    static int[] price;
    static int[] answer;
    
    public int[] solution(int[][] users, int[] emoticons) {
        answer = new int[2];
        int m = 0;
        for(int i : emoticons){
            m++;
        }
        price = new int[m];
        combination(0, m, users, emoticons);
        return answer;
    }
    public static void combination(int cnt, int maxcnt, int[][] users, int[] emoticons){
        if(cnt ==maxcnt){
            int resultuser = 0;
            int resultprice = 0;
            for(int[] user : users){
                int thisprice = 0;
                for(int i = 0 ; i<maxcnt; i++){
                    if(user[0]<=price[i]) thisprice+=emoticons[i]*(100-price[i])/100;
                }
                if(thisprice >= user[1]) resultuser++;
                else resultprice+=thisprice;
            }
            if(answer[0]<resultuser){
                answer[0] = resultuser;
                answer[1] = resultprice;
            }
            else if(answer[0]==resultuser && answer[1]<resultprice){
                answer[1] = resultprice;
            }
            return;
        }
        for(int d = 0 ; d<4 ; d++){
            price[cnt] = discount[d];
            combination(cnt+1,maxcnt,users,emoticons);
        }
    }
}
