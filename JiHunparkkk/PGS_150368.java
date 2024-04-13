class Solution {
    
    private static int plusCnt, totalCost;
    
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
    
        setSalePercent(emoticons.length, new int[emoticons.length], 0, users, emoticons);
        answer[0] = plusCnt;
        answer[1] = totalCost;
        
        return answer;
    }
    
    private static void setSalePercent(int emotiCnt, int[] salePercent,int depth,int[][] users, int[] emoticons){
        if(depth == emotiCnt){
            calculate(users, emoticons, salePercent);
            return;
        }
        
        int[] percent = {10, 20, 30, 40};
        for(int i = 0; i < 4; i++){
            salePercent[depth] = percent[i];
            setSalePercent(emotiCnt, salePercent, depth + 1, users, emoticons);
        }
    }
    
    private static void calculate(int[][] users,int[] emoticons,int[] salePercent){
        //사용자 별로 비율을 확인 후 가격 계산 -> 플러스 여부 및 구매 비용 저장
        int cnt = 0, cost = 0;
        int[] emotiPrice = new int[emoticons.length];

        for(int i = 0; i < emoticons.length; i++){
            emotiPrice[i] = emoticons[i] - (int)(emoticons[i] * (salePercent[i] / 100.0));
        }
        
        for(int i = 0; i < users.length; i++){
            int userCost  = 0;

            //이모티콘 구매
            for(int j = 0; j < emoticons.length; j++){
                if(users[i][0] <= salePercent[j]){
                    userCost += emotiPrice[j];
                }
            }
            
            if(userCost >= users[i][1]){
                cnt++;
            }else{
                cost += userCost;
            }
        }
        
        if(plusCnt < cnt){
            plusCnt = cnt;
            totalCost = cost;
        }else if(plusCnt == cnt){
            if(totalCost < cost){
                totalCost = cost;
            }
        }
    }
}
