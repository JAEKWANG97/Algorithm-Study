package ex0316;

public class PGS_92342 {
    private static int[] lion = {0,0,0,0,0,0,0,0,0,0,0};
    private static int maxValue = Integer.MIN_VALUE;
    private static int[] answer = {0,0,0,0,0,0,0,0,0,0,0};
    private static int[] peach;
    public int[] solution(int n, int[] info) {
        peach = info;
        backtracking(0,0,n);

        int count = 0;
        for(int i = 0; i < answer.length; i++){
            if(answer[i] == 0){
                count++;
            }
        }
        if(count == 11){
            answer = new int[]{-1};
        }

        return answer;
    }//solution end

    private static void backtracking(int start,int cnt,int size){
        if(cnt >= size){
            int pScore = 0;
            int lScore = 0;

            for(int i = 0; i < peach.length; i++){
                if(peach[i] == 0 && lion[i] == 0)continue;
                if(lion[i] > peach[i]){
                    lScore += 10-i;
                }else{
                    pScore += 10-i;
                }
            }

            int cntScore = lScore - pScore;

            if(lScore > pScore){
                if(cntScore > maxValue){
                    maxValue = cntScore;
                    answer = lion.clone();
                }else if(cntScore == maxValue){
                    for(int i = 10; i >= 0; i--){
                        if(lion[i] > answer[i]){
                            answer = lion.clone();
                        }else if(lion[i] < answer[i]){
                            return;
                        }
                    }
                }
            }
            return;
        }

        for(int i = start; i <= 10; i++){
            if(lion[i] > peach[i]) continue;
            lion[i]++;
            backtracking(i,cnt+1,size);
            lion[i]--;
        }
    }

}//class end
