import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14499 {
    static class Dice {
        int top, bottom, left, right, front, back;
        int x, y;

        public Dice(int x, int y) {
            this.top = 0;
            this.bottom = 0;
            this.left = 0;
            this.right = 0;
            this.front = 0;
            this.back = 0;
            this.x = x;
            this.y = y;
        }

        void roll(int direction) {
            int temp;
            switch (direction) {
                case 1: // 동
                    temp = this.right;
                    this.right = this.top;
                    this.top = this.left;
                    this.left = this.bottom;
                    this.bottom = temp;
                    break;
                case 2: // 서
                    temp = this.left;
                    this.left = this.top;
                    this.top = this.right;
                    this.right = this.bottom;
                    this.bottom = temp;
                    break;
                case 3: // 북
                    temp = this.front;
                    this.front = this.top;
                    this.top = this.back;
                    this.back = this.bottom;
                    this.bottom = temp;
                    break;
                case 4: // 남
                    temp = this.back;
                    this.back = this.top;
                    this.top = this.front;
                    this.front = this.bottom;
                    this.bottom = temp;
                    break;
            }
        }
    }
    
    static int N, M, K;
    static int[][] map;
    static int[] command;
    static Dice dice;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dice = new Dice(X, Y);

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        command = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            command[i] = Integer.parseInt(st.nextToken());
        }

        simulate();
    }

    private static void simulate(){
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};
        for (int i = 0; i < K; i++) {
            int direction = command[i] - 1;
            int nx = dice.x + dx[direction];
            int ny = dice.y + dy[direction];

            if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                dice.roll(command[i]);
                if (map[nx][ny] == 0) {
                    map[nx][ny] = dice.bottom;
                } else {
                    dice.bottom = map[nx][ny];
                    map[nx][ny] = 0;
                }
                dice.x = nx;
                dice.y = ny;
                System.out.println(dice.top);
            }
        }
    }
}
