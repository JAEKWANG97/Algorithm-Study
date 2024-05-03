import java.util.*;

class Solution {
    public int solution(String[] board) {

        int answer = -1;

        int oCount = 0;

        int xCount = 0;

        boolean isWin = false;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                if (board[i].charAt(j) == 'O') {
                    oCount++;
                } else if (board[i].charAt(j) == 'X') {
                    xCount++;
                }
            }
        }

        int gap = oCount - xCount;

        if (gap > 1 || gap < 0) {
            return 0;
        }

        if (confirmWin(board, 'O') && gap == 0) {
            return 0;
        }

        if (confirmWin(board, 'X') && gap > 0) {
            return 0;
        }

        return 1;
    }

    private static boolean confirmWin(String[] board, char player) {

        for (int i = 0; i < board.length; i++) {
            if (board[i].charAt(0) == player && board[i].charAt(1) == player && board[i].charAt(2) == player) {
                return true;
            }
            if (board[0].charAt(i) == player && board[1].charAt(i) == player && board[2].charAt(i) == player) {
                return true;
            }
        }

        if (board[0].charAt(2) == player && board[1].charAt(1) == player && board[2].charAt(0) == player) {
            return true;
        }

        if (board[0].charAt(0) == player && board[1].charAt(1) == player && board[2].charAt(2) == player) {
            return true;
        }

        return false;
    }
}
