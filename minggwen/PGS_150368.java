import java.util.*;
public class PGS_150368 {
    static int MAX_C  = 0;
    static int MAX_MONEY = 0;
    static int eLen;
    static int[] emot;
    static int[][] userArr;
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = {0,0};
        emot = emoticons;
        userArr = users;
        eLen = emoticons.length;
        int[] arr = new int[eLen];
        disc(0,arr);
        answer[0] = MAX_C;
        answer[1] = MAX_MONEY;
        
        return answer;
    }
    private static void disc(int cnt,int arr[]){
        if(cnt==eLen){
            getResult(arr);
            
            return;
        }
        for(int m = 10; m<=40; m+=10){
            arr[cnt] = m;
            disc(cnt+1,arr);
        }
    }
    private static void getResult(int arr[]){
        int[] discEmot = new int[eLen];
        int plusCnt = 0;
        int sell = 0;
        int emoCnt = 0;
        for(int e = 0;e <eLen;e++){
            discEmot[e] = emot[e]*(100-arr[e])/100;
        }
        for(int u = 0; u < userArr.length; u++){
            int sum = 0;
            for(int e = 0; e<eLen;e++){
                if(arr[e]>=userArr[u][0]) sum+=discEmot[e];
            }
            if(sum>=userArr[u][1]) plusCnt++;
            else sell+=sum;
        }
        if(MAX_C<plusCnt){
            MAX_C = plusCnt;
            MAX_MONEY = sell;
        }else if(MAX_C==plusCnt){
            if(sell>MAX_MONEY){
                MAX_MONEY = sell;
            }
        }
    }
}