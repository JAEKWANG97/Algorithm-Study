package ex0428;
import java.util.*;

public class PGS_1836 {
    class Solution {
        private static int[][] arr;
        private static int m,n;
        private Point[] start = new Point[26];
        private static int[] dx = {0,1,0,-1};
        private static int[] dy = {1,0,-1,0};

        private class Point{
            int x;
            int y;
            int d;
            int k;

            public Point(int x,int y,int d,int k){
                this.x = x;
                this.y = y;
                this.d = d;
                this.k = k;
            }
        }

        public String solution(int m, int n, String[] board) {
            int count = 0;
            arr = new int[m][n];
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    char card = board[i].charAt(j);
                    if(card >= 'A' && card <= 'Z'){
                        if(start[card - 'A'] == null){
                            count++;
                            start[card - 'A'] = new Point(i,j,0,0);
                        }
                        arr[i][j] = card - 'A';
                    }else if(card == '.'){
                        arr[i][j] = -1;
                    }else{
                        arr[i][j] = -2;
                    }
                }
            }
            Queue<Point> q = new ArrayDeque<>();
            StringBuilder sb = new StringBuilder();
            while(true){
                boolean isCheck = true;
                for(int c = 0; c < 26; c++){
                    if(!isCheck)break;
                    if(start[c] == null)continue;
                    q.clear();
                    boolean[][][] chk = new boolean[m][n][4];

                    for(int d = 0; d < 4; d++){
                        int x = start[c].x + dx[d];
                        int y = start[c].y + dy[d];
                        if (x < 0 || y < 0 || x >= m || y >= n || (arr[x][y] != -1 && arr[x][y] != c)) continue;
                        chk[x][y][d] = true;
                        q.offer(new Point(x,y,d,0));
                    }

                    while(!q.isEmpty()){
                        Point now = q.poll();

                        if(arr[now.x][now.y] == c){
                            arr[start[c].x][start[c].y] = -1;
                            arr[now.x][now.y] = -1;
                            start[c] = null;
                            sb.append((char)(c+'A'));
                            isCheck = false;
                            break;
                        }

                        for(int dd = -1; dd <= 1; dd++){
                            if (now.k == 1 && (dd == -1 || dd == 1))continue;
                            int D = dir(now.d,dd);
                            int x = now.x + dx[D];
                            int y = now.y + dy[D];
                            if (x < 0 || y < 0 || x >= m || y >= n || (arr[x][y] != -1 && arr[x][y] != c)) continue;
                            if(chk[x][y][D])continue;
                            chk[x][y][D] = true;
                            if(now.k == 1){
                                q.offer(new Point(x,y,D,1));
                            }else{
                                q.offer(new Point(x,y,D,now.d == D ? 0 : 1));
                            }
                        }
                    }
                }
                if(isCheck) break;
            }
            if(sb.length() != count){
                return "IMPOSSIBLE";
            }else{
                return sb.toString();
            }
        }

        private int dir(int d,int diff){
            int res = d + diff;
            if(res == 4){
                return 0;
            }else if(res == -1){
                return 3;
            }else{
                return res;
            }
        }

        private static boolean check(int x,int y){
            return x >= 0 && x < m && y >= 0 && y < n;
        }
    }
}
