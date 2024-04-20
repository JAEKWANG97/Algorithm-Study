import java.util.*;

class Solution {
    
    private static final char DEL = '.';
    private static int answer;
    
    public int solution(int m, int n, String[] board) {
        char[][] map = init(m, n, board);
        
        while(bombSame(m, n, map)) {
            downBlock(m, n, map);
        }
        
        return answer;
    }
    
    private static char[][] init(int m, int n, String[] board) { 
        char[][] map = new char[m][n];
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                map[i][j] = board[i].charAt(j);
            }
        }
        return map;
    }
    
    private static boolean bombSame(int m, int n, char[][] map) {
        boolean isBomb = false;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                char now = Character.toUpperCase(map[i][j]);
                int nowCnt = 1;
                
                if(now == DEL) {
                    continue;
                }
                
                if(isIn(i, j + 1, m, n) && now == Character.toUpperCase(map[i][j + 1])) {
                    nowCnt++;                 
                }
                if(isIn(i + 1, j, m, n) && now == Character.toUpperCase(map[i + 1][j])) {
                    nowCnt++;                 
                }
                if(isIn(i + 1, j + 1, m, n) && now == Character.toUpperCase(map[i + 1][j + 1])) {
                    nowCnt++;                 
                }
                
                if(nowCnt == 4) { 
                    now = Character.toLowerCase(now);   //삭제 처리
                    map[i][j] = now;
                    map[i][j + 1] = now;
                    map[i + 1][j] = now;
                    map[i + 1][j + 1] = now;
                    isBomb = true;
                }
            }
        }
        return isBomb;
    }
    
    private static boolean isIn(int x, int y, int m, int n) {
        return x < m && y < n;
    }
    
    private static void downBlock(int m, int n, char[][] map) {
        Stack<Character> stack = new Stack<>();
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if('A' <= map[j][i] && map[j][i] <= 'Z') {
                    stack.push(map[j][i]);
                }
                
                if('a' <= map[j][i] && map[j][i] <= 'z') {
                    answer++;
                }
            }
            
            for(int j = m - 1; j >= 0; j--) {
                if(!stack.isEmpty()) {
                    map[j][i] = stack.pop();
                } else {
                    map[j][i] = DEL;
                }
            }
        }
        
        
    }
}
