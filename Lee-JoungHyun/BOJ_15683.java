import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15683 {
    static int Y, X, map[][], arr[][], areaCnt, answer = -1;
    static int dy[] = {-1, 0, 1, 0};
    static int dx[] = {0, 1, 0, -1};
    static class CCTV {
        public int type, turnCnt, y, x;
        public boolean dir[];
        public CCTV(int type, int y, int x) {
            this.type = type;
            this.y = y;
            this.x = x;
            dir = new boolean[4]; // 위부터 회전
            // 회전 가능 횟수
            if (type == 1) {
                turnCnt = 4;
                dir[1] = true;
            } else if(type == 3) {
                turnCnt = 4;
                dir[0] = dir[1] = true;
            }
            else if(type == 4) {
                turnCnt = 4;
                dir[0] = dir[1] = dir[3] = true;
            }
            else if (type == 2) {
                turnCnt = 2;
                dir[1] = dir[3] = true;
            }
            else {
                turnCnt = 1;
                dir[0] = dir[1] = dir[2] = dir[3] = true;
            }
        }
        public void turn() {
            boolean tmp = dir[0];
            boolean tmp2;
            for (int i = 0; i < 3; i++) {
                tmp2 = dir[i+1];
                dir[i + 1] = tmp;
                tmp = tmp2;
            }
            dir[0] = tmp;
        }
    }
    static ArrayList<CCTV> cctvs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        cctvs = new ArrayList<>();
        map = new int[Y][X];
        for (int y = 0; y < Y; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < X; x++) {
                int tmp = Integer.parseInt(st.nextToken());
                map[y][x] = tmp;
                if (1 <= tmp && tmp <= 5)
                    cctvs.add(new CCTV(tmp, y, x));
                else if (tmp == 0)
                    areaCnt++;
            }
        }
        setCCTV(0);
        System.out.println(areaCnt - answer);
    }
    static void setCCTV(int cnt) {
        //System.out.println(cnt + ", " + cctvs.size());
        if (cnt == cctvs.size()) {
            arr = new int[Y][];
            for (int i = 0; i < Y; i++)
                arr[i] = Arrays.copyOf(map[i], X);
            answer = Math.max(answer, check(arr));
            return;
        }
        for (int i = 0; i < cctvs.get(cnt).turnCnt; i++) {
            setCCTV(cnt+1);
            cctvs.get(cnt).turn();
        }
    }
    static int check(int[][] arr) {
        int tmp = 0;
        for (CCTV ct : cctvs) {
            //System.out.println(ct);
            for (int i = 0; i < 4; i++) {
                if (ct.dir[i]) {
                    tmp += goToDir(arr, i, ct.x, ct.y);
                }
            }
        }
        return tmp;
    }
    static int goToDir(int[][] arr, int dir, int x, int y) {
        int erase = 0;
        int gap = 1;
        int nx;
        int ny;
        while (true) {
            nx = x + dx[dir] * gap;
            ny = y + dy[dir] * gap;
            if (!(0 <= nx && nx < X && 0 <= ny && ny < Y)) break;
            if (arr[ny][nx] == 0) {
                arr[ny][nx] = -1;
                erase++;
            }
            else if (arr[ny][nx] == 6)
                break;
            gap++;
        }
        return erase;
    }
}