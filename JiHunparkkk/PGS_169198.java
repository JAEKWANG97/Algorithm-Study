class Solution {
    private static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];

        //입사각 == 반사각 이면, 대칭이동 가능
        for(int i = 0; i < balls.length; i++) {
            Point standard = new Point(startX, startY);
            Point target = new Point(balls[i][0], balls[i][1]);
            
            answer[i] = move(standard, target, m, n);
        }
        
        return answer;
    }
    
    private static int move(Point standard, Point target, int m, int n) {
        int answer = Integer.MAX_VALUE;
        int result = 0;
        
        //왼쪽 
        if(!(standard.y == target.y && standard.x >= target.x)) {
            result = calDistance(standard, new Point(-target.x, target.y));
            answer = Math.min(answer, result);
        }

    
        //오른쪽
        if(!(standard.y == target.y && standard.x <= target.x)) {
            result = calDistance(standard, new Point(m + m - target.x, target.y));  
            answer = Math.min(answer, result);
        }
        
        //위 
        if(!(standard.x == target.x && standard.y <= target.y)) {
            result = calDistance(standard, new Point(target.x, n + n - target.y));
            answer = Math.min(answer, result);
        }

        
        //아래       
        if(!(standard.x == target.x && standard.y >= target.y)) {
            result = calDistance(standard, new Point(target.x, -target.y));
            answer = Math.min(answer, result);
        }
        
        return answer;
    }
    
    private static int calDistance(Point standard, Point target) {
        return (int)(Math.pow(standard.x - target.x, 2) + Math.pow(standard.y - target.y, 2));
    }
}
