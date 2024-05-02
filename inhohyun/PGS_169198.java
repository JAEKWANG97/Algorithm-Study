public class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];

        Ball ball = new Ball(startX, startY);
        ball.setWidth(m);
        ball.setHeight(n);
        for (int i = 0; i < balls.length; i++) {
            int targetX = balls[i][0];
            int targetY = balls[i][1];
            ball.setTargetX(targetX);
            ball.setTargetY(targetY);
            answer[i] = ball.getMin();
        }
        return answer;
    }
}

class Ball {
    private int startX;
    private int startY;
    private int targetX;
    private int targetY;

    private int width;
    private int height;
    public Ball(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
    }

    public int getMin() {
        // target 위치를 기준으로 점대칭
        int up = pow(startX - targetX) + pow(2 * height - startY - targetY);
        int down = pow(startX - targetX) + pow(startY + targetY);
        int left = pow(startX + targetX) + pow(startY - targetY);
        int right = pow(startY - targetY) + pow(2 * width - startX - targetX);

        // 상 점대칭 불가
        if (startX == targetX && targetY > startY) {
            return min(down, left, right);
        }
        // 하 점대칭 불가
        else if (startX == targetX && targetY < startY) {
            return min(up, left, right);
        }
        // 좌 점대칭 불가
        else if (startY == targetY && targetX > startX) {
            return min(up, down, left);
        }
        // 우 점대칭 불가
        else if (startY == targetY && targetX < startX) {
            return min(up, down, right);
        } else {
            return min(up, down, left, right);
        }
    }

    public int pow(int num) {
        return (int) Math.pow(num, 2);
    }

    public int min(int... num) {
        int min = Integer.MAX_VALUE;
        for (int n : num) {
            min = Math.min(min, n);
        }
        return min;
    }

    public void setTargetX(int targetX) {
        this.targetX = targetX;
    }

    public void setTargetY(int targetY) {
        this.targetY = targetY;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
