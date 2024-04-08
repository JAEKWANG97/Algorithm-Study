import java.io.*;
import java.util.*;
public class BOJ_4179 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int R, C;
    static class Point {
        int y;
        int x;
        int time;

        Point(int y, int x){
            this.y = y;
            this.x = x;
        }

        Point(int y, int x, int time){
            this.y = y;
            this.x = x;
            this.time = time;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return y == point.y && x == point.x;
        }

        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }
    }

    static boolean inRange(int y, int x){
        return 0 <= y && y < R && 0 <= x && x < C;
    }

    public static void main(String[] args) throws IOException{
        int answer = (int) 1e9;

        int[] dys = {1, 0, -1, 0};
        int[] dxs = {0, 1, 0, -1};

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        int[][] board = new int[R][C];

        Queue<Point> pointQueue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];

        List<Point> fires = new ArrayList<>();

        for (int i = 0; i < R; i++){
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < C; j++){
                if (line[j] == '#'){
                    board[i][j] = -1;
                }else if (line[j] == 'J'){
                    board[i][j] = (int) 1e9;
                    pointQueue.add(new Point(i, j, 1));
                    visited[i][j] = true;
                }else if (line[j] == '.'){
                    board[i][j] = (int) 1e9;
                }else if (line[j] == 'F'){
                    board[i][j] = 1;
                    fires.add(new Point(i, j));
                }
            }
        }

        for (Point fire: fires){
            Queue<Point> fireQueue = new LinkedList<>();
            boolean[][] fireVisited = new boolean[R][C];

            fireQueue.add(new Point(fire.y, fire.x));
            fireVisited[fire.y][fire.x] = true;

            while (!fireQueue.isEmpty()){
                Point current = fireQueue.poll();
                for (int k = 0; k < 4; k++) {
                    int ny = current.y + dys[k];
                    int nx = current.x + dxs[k];

                    if (inRange(ny, nx) && board[ny][nx] > board[current.y][current.x] + 1 && !fireVisited[ny][nx]) {
                        fireQueue.add(new Point(ny, nx));
                        board[ny][nx] = board[current.y][current.x] + 1;
                        fireVisited[ny][nx] = true;
                    }
                }
            }

        }

//        for (int i = 0; i < R; i++){
//            for (int j = 0; j < C; j++){
//                System.out.print(board[i][j] + " ");
//            }
//            System.out.println();
//        }

        while (!pointQueue.isEmpty()){
            Point current = pointQueue.poll();

            if (current.y == 0 || current.y == R - 1 || current.x == 0 || current.x == C - 1){
                answer = current.time < answer ? current.time : answer;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int ny = current.y + dys[i];
                int nx = current.x + dxs[i];
                int nt = current.time + 1;

                if (inRange(ny, nx) && board[ny][nx] > nt && !visited[ny][nx]) {
                    pointQueue.add(new Point(ny, nx, nt));
                    visited[ny][nx] = true;
                }
            }
        }

        System.out.println(answer < (int) 1e9 ? answer : "IMPOSSIBLE");
    }
}
