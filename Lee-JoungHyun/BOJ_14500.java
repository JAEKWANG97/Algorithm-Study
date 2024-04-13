import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int Y, X, map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        int answer = -1;
        map = new int[Y][X];
        for (int y = 0; y < Y; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < X; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }


        for (int y = 0; y < Y; y++) {
            for (int x = 0; x < X; x++) {
                answer = Math.max(answer, find_subAns(y, x));
            }
        }
        System.out.println(answer);
    }

    static int find_subAns(int y, int x) {
        int subans = -1;
        //1. 처음거
        if (x+3 < X) {
            subans = map[y][x] + map[y][x + 1] + map[y][x + 2] + map[y][x + 3];
        }
        if (y+3 < Y) {
            subans = Math.max(subans, map[y][x] + map[y + 1][x] + map[y + 2][x] + map[y + 3][x]);
        }


        // 2. 네모
        if (x+1 < X && y+1 < Y) {
            subans = Math.max(subans, map[y][x] + map[y][x + 1] + map[y + 1][x] + map[y + 1][x + 1]);
        }


        // 3. ㄴ자
        if (x+1 < X && y+2 < Y) {
            subans = Math.max(subans, map[y][x] + map[y + 1][x] + map[y + 2][x] + map[y + 2][x + 1]);
            subans = Math.max(subans, map[y][x + 1] + map[y + 1][x + 1] + map[y + 2][x + 1] + map[y + 2][x]);
            subans = Math.max(subans, map[y][x] + map[y + 1][x] + map[y + 2][x] + map[y][x + 1]);
            subans = Math.max(subans, map[y][x + 1] + map[y + 1][x + 1] + map[y + 2][x + 1] + map[y][x]);
        }
        if (x+2 < X && y+1 < Y) {
            subans = Math.max(subans, map[y][x] + map[y + 1][x] + map[y + 1][x + 1] + map[y + 1][x + 2]);
            subans = Math.max(subans, map[y][x] + map[y][x + 1] + map[y][x + 2] + map[y + 1][x]);
            subans = Math.max(subans, map[y + 1][x] + map[y + 1][x + 1] + map[y + 1][x + 2] + map[y][x + 2]);
            subans = Math.max(subans, map[y][x] + map[y][x + 1] + map[y][x + 2] + map[y + 1][x + 2]);
        }

        // 4. 번개모양
        if (x+1 < X && y + 2 < Y) {
            subans = Math.max(subans, map[y][x] + map[y + 1][x] + map[y + 1][x + 1] + map[y + 2][x + 1]);
            subans = Math.max(subans, map[y][x + 1] + map[y + 1][x + 1] + map[y + 1][x] + map[y + 2][x]);
        }
        if (x+2 < X && y+1 < Y) {
            subans = Math.max(subans, map[y + 1][x] + map[y + 1][x + 1] + map[y][x + 1] + map[y][x + 2]);
            subans = Math.max(subans, map[y][x] + map[y][x + 1] + map[y + 1][x + 1] + map[y + 1][x + 2]);
        }

        // 5. ㅜ모양
        if (x + 2 < X && y + 1 < Y) {
            subans = Math.max(subans, map[y][x] + map[y][x + 1] + map[y + 1][x + 1] + map[y][x + 2]);
            subans = Math.max(subans, map[y + 1][x] + map[y + 1][x + 1] + map[y][x + 1] + map[y + 1][x + 2]);
        }
        if (x+1 < X && y+2 < Y) {
            subans = Math.max(subans, map[y][x] + map[y + 1][x] + map[y + 1][x + 1] + map[y + 2][x]);
            subans = Math.max(subans, map[y][x + 1] + map[y + 1][x + 1] + map[y + 1][x] + map[y + 2][x + 1]);
        }

        //System.out.println(y + ", " + x + ": " + subans);
        return subans;
    }

}
