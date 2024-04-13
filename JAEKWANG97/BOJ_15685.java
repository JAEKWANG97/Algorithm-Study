import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_15685 {
    static boolean[][] map = new boolean[101][101];
    static int[] dx = {1, 0, -1, 0}; // 방향: →, ↑, ←, ↓
    static int[] dy = {0, -1, 0, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            int d = Integer.parseInt(input[2]);
            int g = Integer.parseInt(input[3]);
            drawDragonCurve(x, y, d, g);
        }
        
        System.out.println(countSquares());
    }
    
    private static void drawDragonCurve(int x, int y, int d, int g) {
        ArrayList<Integer> directions = new ArrayList<>();
        directions.add(d);
        
        for (int i = 0; i < g; i++) {
            for (int j = directions.size() - 1; j >= 0; j--) {
                directions.add((directions.get(j) + 1) % 4);
            }
        }
        
        map[y][x] = true;
        for (Integer direction : directions) {
            x += dx[direction];
            y += dy[direction];
            map[y][x] = true;
        }
    }
    
    private static int countSquares() {
        int count = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) {
                    count++;
                }
            }
        }
        return count;
    }
}
