package hellomatia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class BOJ_15685 {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N;
    private boolean[][] map;

    public static void main(String[] args) throws IOException {
        new BOJ_15685().solution();
    }

    public void solution() throws IOException {
        init();
        printResult(calcResult());
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        map = new boolean[100 + 1][100 + 1];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            draw(x, y, calcDir(d, g));
        }

    }

    private List<Integer> calcDir(int d, int g) {
        List<Integer> dirs = new ArrayList<>();
        dirs.add(d);

        while (g-- > 0) {
            for (int i = dirs.size() - 1; i >= 0; i--) {
                int nextDir = (dirs.get(i) + 1) % 4;
                dirs.add(nextDir);
            }
        }

        return dirs;
    }

    private void draw(int x, int y, List<Integer> dirs) {
        map[x][y] = true;

        for (int dir : dirs) {
            if (dir == 0) {
                map[++x][y] = true;
            } else if (dir == 1) {
                map[x][--y] = true;
            } else if (dir == 2) {
                map[--x][y] = true;
            } else if (dir == 3) {
                map[x][++y] = true;
            }
        }
    }

    private int calcResult() {
        int result = 0;
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                if (map[x][y] && map[x + 1][y] && map[x][y + 1] && map[x + 1][y + 1]) {
                    result++;
                }
            }
        }
        return result;
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
        bw.flush();
    }
}