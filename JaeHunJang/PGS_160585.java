
// 혼자서 하는 틱택토 / 90분
class PGS_160585 {
    public int solution(String[] board) {
        int ocnt = 0, xcnt = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i].charAt(j) == 'O') ocnt++;
                else if (board[i].charAt(j) == 'X') xcnt++;
            }
        }
        
        if (xcnt > ocnt || ocnt > xcnt+1) return 0;
        
        if (checkWin(board, 'O')) {
            if (ocnt == xcnt) return 0;
        } 
        
        if (checkWin(board, 'X')) {
            if (ocnt == xcnt + 1) return 0;
        }
        
        return 1;
    }
  
    
     boolean checkWin(String[] board, char player) {
        // 가로, 세로 검사
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == player && board[i].charAt(1) == player && board[i].charAt(2) == player) return true;
            if (board[0].charAt(i) == player && board[1].charAt(i) == player && board[2].charAt(i) == player) return true;
        }
        
        // 대각선 검사
        if (board[0].charAt(0) == player && board[1].charAt(1) == player && board[2].charAt(2) == player) return true;
        if (board[0].charAt(2) == player && board[1].charAt(1) == player && board[2].charAt(0) == player) return true;
        
        return false;
    }
}