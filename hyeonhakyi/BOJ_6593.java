package ex0314;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_6593 {
    private static class Person{
        int z;
        int x;
        int y;
        int count;
        public Person(int z,int x,int y,int count){
            this.z = z;
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    private static char[][][] map;
    private static boolean[][][] visited;
    private static int l,r,c,result;
    private static int[] dz = {-1,1,0,0,0,0};
    private static int[] dx = {0,0,-1,1,0,0};
    private static int[] dy = {0,0,0,0,-1,1};
    private static StringBuilder sb = new StringBuilder();
    private static Queue<Person> person = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
            st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if(l == 0 && r == 0 && c == 0){
                break;
            }
            map = new char[l][r][c];
            visited = new boolean[l][r][c];
            result = Integer.MAX_VALUE;

            for(int x = 0; x < l; x++){
                for(int i = 0; i < r; i++){
                    String s = br.readLine();
                    for(int j = 0; j < c; j++){
                        map[x][i][j] = s.charAt(j);

                        if(map[x][i][j] == 'S'){
                            person.offer(new Person(x,i,j,0));
                            visited[x][i][j] = true;
                        }
                    }
                }
                br.readLine();
            }

            bfs();
            if(result != Integer.MAX_VALUE){
                System.out.println("Escaped in " + result + " minute(s).");
            }else{
                System.out.println("Trapped!");
            }
        }
    }//main end
    private static void bfs(){
        while(!person.isEmpty()){
            Person now = person.poll();
            int nowZ = now.z;
            int nowX = now.x;
            int nowY = now.y;
            int count = now.count;

            if(map[nowZ][nowX][nowY] == 'E'){
                result = Math.min(result,count);
            }

            for(int d = 0; d < 6; d++){
                int nz = nowZ + dz[d];
                int nx = nowX + dx[d];
                int ny = nowY + dy[d];

                if(nz >= 0 && nx >= 0 && ny >= 0 && nx < r && ny < c && nz < l && !visited[nz][nx][ny] && map[nz][nx][ny] != '#'){
                    visited[nz][nx][ny] = true;
                    person.offer(new Person(nz,nx,ny,count+1));
                }
            }
        }
    }//bfs end
}//class end
