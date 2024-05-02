class Solution {

    private static int fstCnt, sndCnt;
    
    public int solution(String[] board) {
        int answer = 0;
        
        //'O'의 갯수 == 'X'의 갯수
        //'O'의 갯수 == 'X'의 갯수 + 1
        //꽉 찾을 경우 무조건 'O'의 승리 or 무승부 여야 함 ('X'의 승리 시 꽉 찰 수 없다)
        //둘 모두 승리할 수 없다

        char[][] copyBoard = init(board);
        if(validCount() && checkWinner(copyBoard)) {
            answer = 1;
        }
        
        return answer;
    }
    
    private static char[][] init(String[] board) {
        char[][] copy = new char[3][3];
        
        for(int i = 0; i < 3; i++) {
            String now = board[i];
            
            for(int j = 0; j < 3; j++) {
                copy[i][j] = now.charAt(j);
                
                if(copy[i][j] == 'O') {
                    fstCnt++;
                } else if(copy[i][j] == 'X') {
                    sndCnt++;
                }
            }
        }
        return copy;
    }
    
    private static boolean validCount() {        
        if(fstCnt == sndCnt || fstCnt == sndCnt + 1) {
            return true;
        }
        return false;
    }
    
    private static boolean checkWinner(char[][] board) {
        int fstWinCnt = 0;
        int sndWinCnt = 0;
        
        //가로 확인
        for(int i = 0; i < 3; i++) {
            if(board[i][0] != '.' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                if(board[i][0] == 'O') { 
                    fstWinCnt++;
                } else {
                    sndWinCnt++;
                }
            }
        }
        
        //세로 확인
        for(int i = 0; i < 3; i++) {
            if(board[0][i] != '.' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                if(board[0][i] == 'O') { 
                    fstWinCnt++;
                } else {
                    sndWinCnt++;
                }
            }
        }
        
        //대각선 확인
        if(board[1][1] != '.' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if(board[1][1] == 'O') { 
                fstWinCnt++;
            } else {
                sndWinCnt++;
            }
        }
        
        if(board[1][1] != '.' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if(board[1][1] == 'O') { 
                fstWinCnt++;
            } else {
                sndWinCnt++;
            }
        }
        
        //로직 확인
        if(fstWinCnt > 0 && sndWinCnt > 0) {
            return false;
        }
        
        if(fstCnt + sndCnt == 9 && sndWinCnt > 0) {
            return false;
        }
        
        if(fstWinCnt > 0 && fstCnt != sndCnt + 1) {
            return false;
        }
        
        if(sndWinCnt > 0 && fstCnt != sndCnt) {
            return false;
        }
        
        return true;
    }
    

}
