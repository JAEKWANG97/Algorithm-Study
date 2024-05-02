import java.util.*;

class Solution {
    static int N,M,sy,sx;
    
    public List<Integer> solution(int m, int n, int startX, int startY, int[][] balls) {
        List<Integer> answer = new ArrayList<>();
        N = n; M = m; sy = startY; sx = startX;
        for(int[] ball : balls){
            Ball curr = new Ball(ball[1],ball[0]);
            answer.add(curr.getMinDistance());
        }
        
        return answer;
    }
    
    static class Ball{
        int y;
        int x;
        
        Ball(int y, int x){
            this.y = y;
            this.x = x;
        }
        
        int getMinDistance(){
            int res = Integer.MAX_VALUE;
            List<Ball> balls = new ArrayList<>();
            // 위 방향 이동
            if(!(x==sx && y<sy)) balls.add(new Ball(y*-1,x));
            
            // 아래 방향 이동
            if(!(x==sx && y>sy)) balls.add(new Ball(2*N-y,x));
            
            // 왼쪽 방향 이동
            if(!(y==sy && x<sx)) balls.add(new Ball(y,x*-1));
            
            // 오른쪽 방향 이동
            if(!(y==sy && x>sx)) balls.add(new Ball(y,2*M-x));
            
            for(Ball ball : balls){
                int diffY = Math.abs(sy-ball.y);
                int diffX = Math.abs(sx-ball.x);
                int d = (int)Math.pow(diffY,2) + (int)Math.pow(diffX,2);
                res = Math.min(res,d);
            }
            
            return res;
        }
    }
}
