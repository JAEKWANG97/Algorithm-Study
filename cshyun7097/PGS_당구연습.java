public class PGS_당구연습 {
    static final int INF = Integer.MAX_VALUE;

    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];

        for (int i = 0; i < balls.length; i++) {
            int targetX = balls[i][0];
            int targetY = balls[i][1];

            int curlen = INF;
            int len = INF;

            if (!(startY == targetY && startX >= targetX)) {
                curlen = distance(startX, startY, targetX * (-1), targetY);
                len = curlen < len ? curlen : len;
            }

            if (!(startY == targetY && startX <= targetX)) {
                curlen = distance(startX, startY, m + (m - targetX), targetY);
                len = curlen < len ? curlen : len;
            }

            if (!(startX == targetX && startY <= targetY)) {
                curlen = distance(startX, startY, targetX, n + (n - targetY));
                len = curlen < len ? curlen : len;
            }

            if (!(startX == targetX && startY >= targetY)) {
                curlen = distance(startX, startY, targetX, targetY * (-1));
                len = curlen < len ? curlen : len;
            }

            answer[i] = len;
        }

        return answer;
    }

    public int distance(int startX, int startY, int targetX, int targetY) {
        return (int) (Math.pow(startX - targetX, 2) + Math.pow(startY - targetY, 2));
    }
}