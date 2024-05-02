import java.util.*;
class Solution {
    static char[][] map;
    static String[] board;
    public int solution(String[] Board) {
        board = Board;
        int answer = -1;
        int cntO = 0;
        int cntX = 0;
        map = new char[board.length][board.length];
        int cnt=0;
        for(int r=0;r<board.length;r++){
            for(int c=0;c<board[r].length();c++){
                map[r][c] = board[r].charAt(c);
                if(board[r].charAt(c)=='O'){
                   
                    cntO++;
                    cnt++;
                }
                else if(board[r].charAt(c)=='X'){
                    cntX++;
                    cnt++;
                }
            }
        }
        if((cntX>cntO)||(cntO-cntX>1))return 0;
        if(checkWin('O')&&cntX==cntO)return 0;
        if(checkWin('X')&&cntO==cntX+1)return 0;
        
        return 1;
    }
    public static boolean checkWin(char ch){
        for(int r=0;r<board.length;r++){
            if(board[r].charAt(0)==ch&&board[r].charAt(1)==ch&&board[r].charAt(2)==ch)return true;
        }
        for(int c=0;c<board.length;c++){
            String str = "";
            for(int r=0;r<board.length;r++){
                str+=Character.toString(map[r][c]);
            }
            if(str.charAt(0)==ch&&str.charAt(1)==ch&&str.charAt(2)==ch)return true;
        }
        String str = "";
        for(int idx=0;idx<board.length;idx++){
            str+=map[idx][idx];
        }
        if(str.charAt(0)==ch&&str.charAt(1)==ch&&str.charAt(2)==ch)return true;
        
        str = "";
        for(int idx=0;idx<3;idx++){
            str+=map[idx][3-idx-1];
        }
        if(str.charAt(0)==ch&&str.charAt(1)==ch&&str.charAt(2)==ch)return true;
        return false;
    }
}
