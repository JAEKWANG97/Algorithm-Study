package eunsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4179 {
    // Queue에 넣을 쌍 만들기
    static class Pair{
        int X, Y;
        Pair(int X, int Y){
            this.X = X;
            this.Y = Y;
        }
    }
    static Character[][] map;
    static int[] dy = {0,1,0,-1};
    static int[] dx = {1,0,-1,0};
    static int r,c;
    static Queue<Pair> fireQ;
    static Queue<Pair> jhQ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken()); // 행의 개수
        c = Integer.parseInt(st.nextToken()); // 열의 개수
        map = new Character[r][c];

        fireQ = new LinkedList<>();
        jhQ = new LinkedList<>();

        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = s.charAt(j);
                if(map[i][j] == 'F'){
                    fireQ.offer(new Pair(i,j));
                }
                if(map[i][j] == 'J') {
                    jhQ.offer(new Pair(i,j));
                }
            }
        }
        bfs();

    }

    private static void bfs(){
        int time = 0;

        // 지훈이가 움직일 수 있는 동안
        while(!jhQ.isEmpty()){

            // 1초만큼 움직임
            int fireSize = fireQ.size();
            for (int i = 0; i < fireSize; i++) {
                Pair cur = fireQ.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int nx = cur.X + dx[dir];
                    int ny = cur.Y + dy[dir];
                    if(nx<0 || nx>= r || ny<0 || ny >=c) continue;
                    if(map[nx][ny] == '#' || map[nx][ny] == 'F') continue;

                    map[nx][ny] = 'F';
                    fireQ.offer(new Pair(nx,ny));
                }
            }

            // 1초만큼 움직임
            int jhSize = jhQ.size();
            for (int i = 0; i < jhSize; i++) {
                Pair jh = jhQ.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int nx = jh.X + dx[dir];
                    int ny = jh.Y + dy[dir];

                    // 지훈이 탈출하는 경우
                    if(nx<0 || nx>= r || ny<0 || ny >=c){
                        System.out.println(time+1);
                        return;
                    }

                    if(map[nx][ny] != '.' || map[nx][ny] == 'J') continue;
                    map[nx][ny] = 'J';
                    jhQ.offer(new Pair(nx,ny));
                }
            }
            time++;
        }
        // 탈출할 수 없는 경우
        System.out.println("IMPOSSIBLE");
    }
}
