package hellomatia;

import java.util.*;

class PGS_169198 {

    class Ball{
        int y;
        int x;

        Ball(int y, int x){
            this.y = y;
            this.x = x;
        }

        int getMinDistance(){
            int result = Integer.MAX_VALUE;
            List<Ball> balls = new ArrayList<>();

            if(!(x==sx && y<sy)) balls.add(new Ball(y*-1,x));

            if(!(x==sx && y>sy)) balls.add(new Ball(2*N-y,x));

            if(!(y==sy && x<sx)) balls.add(new Ball(y,x*-1));

            if(!(y==sy && x>sx)) balls.add(new Ball(y,2*M-x));

            for(Ball ball : balls){
                int diffY = Math.abs(sy-ball.y);
                int diffX = Math.abs(sx-ball.x);
                int dist = (int) Math.pow(diffY,2) + (int) Math.pow(diffX,2);
                result = Math.min(result, dist);
            }

            return result;
        }
    }

    private int N,M,sy,sx;

    public List<Integer> solution(int m, int n, int startX, int startY, int[][] balls) {

        N = n;
        M = m;
        sy = startY;
        sx = startX;

        List<Integer> answer = new ArrayList<>();

        for(int[] ball : balls){
            Ball curr = new Ball(ball[1],ball[0]);
            answer.add(curr.getMinDistance());
        }

        return answer;
    }
}