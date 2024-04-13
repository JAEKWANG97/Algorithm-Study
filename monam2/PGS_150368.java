class PGS_150368 { //프로그래머스 150368 이모티콘 할인행사 - 80분
    
    // static int 
    
    static int discount[] = {10,20,30,40};
    static int emtPlusUserResult, emtIncomeResult;
    
    public int[] solution(int[][] users, int[] emoticons) {
        
        int emtLen = emoticons.length;
        emtPlusUserResult = 0;
        emtIncomeResult = 0;
        
        dfs(0, emtLen, new int[emoticons.length], users, emoticons);
        int[] answer = {emtPlusUserResult, emtIncomeResult};
        return answer;
    }//solution
    
    private static void dfs(int idx, int emtLen, int[] dcEmtPrice, int[][] users, int[] emoticons) {
        if (idx == emtLen) { //기저
            calculate(emtLen, dcEmtPrice, users, emoticons);
            return;
        }
        
        for(int i=0; i<4; i++) { //중복 순열
            dcEmtPrice[idx] = discount[i];
            dfs(idx+1, emtLen, dcEmtPrice, users, emoticons);
        }
    }//dfs
    
    private static void calculate(int emtLen, int[] dcEmtPrice, int[][] users, int[] emoticons) {
        int emtPlusUser = 0; //플러스 서비스 구독자 수
        int emtIncome = 0; //이모티콘 판매액
        
        //유저 한 명당 모든 이모티콘을 보면서 살지 말지 결정해야 함
        for(int[] user : users) {
            int userDcRate = user[0]; //사용자의 할인율 기준(이거보다 높으면 전부 구매)
            int userCostLimit = user[1]; //사용자의 기준 가격
            int sum = 0;
            
            for(int i=0; i<emtLen; i++) {
                //유저가 설정한 할인 기준보다 할인율이 높으면 -> 전부 구매
                if (dcEmtPrice[i] >= userDcRate) {
                    sum += emoticons[i] * (100-dcEmtPrice[i]) / 100;
                }
            }
            
            //총 가격이 사용자의 기준 가격을 넘으면 -> 구독++
            // 아니면 -> 이모티콘 판매액 증가
            if (sum >= userCostLimit) {
                emtPlusUser++;
            } else {
                emtIncome += sum;
            }
        }//forEach
        
        //결과 갱신 로직
        //1순위 - 구독자 / 2순위 - 판매액
        if (emtPlusUser > emtPlusUserResult) {
            emtPlusUserResult = emtPlusUser;
            emtIncomeResult = emtIncome;
        } else if (emtPlusUser == emtPlusUserResult) { //1순위 같으면 2순위
            emtIncomeResult = Math.max(emtIncomeResult, emtIncome);
        }
    }//calculate
}//class

//할인율...이모티콘 -> 백트래킹
