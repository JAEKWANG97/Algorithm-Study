class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        
        for (int i = 0; i < balls.length; i++) {
            answer[i] = calc(startX, startY, balls[i][0], balls[i][1], m, n);
        }
        
        return answer;
    }
    
    private int calc(int x1, int y1, int x2, int y2, int m, int n) {

        int distance = Integer.MAX_VALUE;
        //1. 왼벽
        if (!(y2 == y1 && x1 > x2))
            distance = Math.min(distance, pow(x2 + x1) + pow(Math.abs(y2 - y1)));
        
        //2. 오른벽
        if (!(y2 == y1 && x2 > x1))
            distance = Math.min(distance, pow(m * 2 - x2 - x1) + pow(Math.abs(y2 - y1)));
        
        //3. 윗벽
        if (!(x1 == x2 && y1 > y2))
            distance = Math.min(distance, pow(y1 + y2) + pow(Math.abs(x1 - x2)));
        
        //4. 아랫벽
        if (!(x1 == x2 && y2 > y1))
            distance = Math.min(distance, pow(n * 2 - y1 - y2) + pow(Math.abs(x1 - x2)));
        
        return distance;
    }
    
    private int pow(int x) {
        return (int) Math.pow(x, 2);
    }
    
}