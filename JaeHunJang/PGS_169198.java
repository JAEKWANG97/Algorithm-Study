// 당구 연습 / 60분

class PGS_169198 {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];

        for (int i = 0; i < balls.length; i++) {
            int targetX = balls[i][0];
            int targetY = balls[i][1];

            int now = 0; 
            int result = Integer.MAX_VALUE;

            if (!(startY == targetY && startX >= targetX)) {
                now = distance(startX, startY, targetX * (-1), targetY);
                result = Math.min(now, result);
            }

            if (!(startY == targetY && startX <= targetX)) {
                now = distance(startX, startY, m + (m - targetX), targetY);
                result = Math.min(now, result);
            }

            // 상
            if (!(startX == targetX && startY <= targetY)) {
                now = distance(startX, startY, targetX, n + (n - targetY));
                result = Math.min(now, result);
            }

            // 하
            if (!(startX == targetX && startY >= targetY)) {
                now = distance(startX, startY, targetX, targetY * (-1));
                result = Math.min(now, result);
            }

            answer[i] = result;
        }
        return answer;
    }
    
    public int distance(int sx, int sy, int tx, int ty) {
        return (int) (Math.pow(sx - tx, 2) + Math.pow(sy - ty, 2));
    }
}