
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3055 {

    static int R, C;
    static Character map[][];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<xy> sq = new LinkedList<xy>();  // For hedgehog
    static Queue<xy> wq = new LinkedList<xy>();  // For water
    static int answer = Integer.MAX_VALUE;

    static class xy {
        int x, y;
        int time;

        xy(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // *: water / .: empty / X: rock
        map = new Character[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);

                if (map[i][j] == 'S')
                    sq.add(new xy(i, j, 0));
                else if (map[i][j] == '*')
                    wq.add(new xy(i, j, 0));
            }
        }

        BFS();

        System.out.println(answer == Integer.MAX_VALUE ? "KAKTUS" : answer);
    }

    public static void BFS() {

        while (!sq.isEmpty()) {
        	//믈 이동
            int wLen = wq.size();
            for (int i = 0; i < wLen; i++) {
                xy curW = wq.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = curW.x + dx[j];
                    int ny = curW.y + dy[j];

                    if (nx >= 0 && nx < R && ny >= 0 && ny < C && map[nx][ny] == '.') {
                        map[nx][ny] = '*';
                        wq.add(new xy(nx, ny, 0));
                    }
                }
            }

            //고슴도치 이동
            int sLen = sq.size();
            for (int i = 0; i < sLen; i++) {
                xy curS = sq.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = curS.x + dx[j];
                    int ny = curS.y + dy[j];

                    if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
                        if (map[nx][ny] == 'D') {
                            answer = Math.min(answer, curS.time + 1);
                            return;
                        } else if (map[nx][ny] == '.') {
                            map[nx][ny] = 'S';
                            sq.add(new xy(nx, ny, curS.time + 1));
                        }
                    }
                }
            }
        }
    }
}

