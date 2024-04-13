package ex0325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20057 {
    private static int n;
    private static int[][] map;
    private static int[] dx = {0,1,0,-1};
    private static int[] dy = {-1,0,1,0};
    private static int[] dc = {1,1,2,2};
    private static int[][] dsx = {{-1,1,-2,-1,1,2,-1,1,0},{-1,-1,0,0,0,0,1,1,2},{1,-1,2,1,-1,-2,1,-1,0},{1,1,0,0,0,0,-1,-1,-2}};
    private static int[][] dsy = {{1,1,0,0,0,0,-1,-1,-2},{-1,1,-2,-1,1,2,-1,1,0},{-1,-1,0,0,0,0,1,1,2},{1,-1,2,1,-1,-2,1,-1,0}};
    private static int[] sandRotate= {1,1,2,7,7,2,10,10,5};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(calculate(n/2,n/2));
    }//main end

    private static int calculate(int x,int y){
        int total = 0;

        int currentX = x;
        int currentY = y;

        while (true){
            for(int d = 0; d < 4; d++){
                for(int mv = 0; mv < dc[d]; mv++){
                    int nx = currentX + dx[d];
                    int ny = currentY + dy[d];

                    if(nx < 0 || ny < 0 || nx >= n || ny >= n){
                        return total;
                    }

                    int sand = map[nx][ny];
                    map[nx][ny] = 0;
                    int spreadTotal = 0;

                    for(int spread = 0; spread < 9; spread++){
                        int sandX = nx + dsx[d][spread];
                        int sandY = ny + dsy[d][spread];
                        int spreadAmount = (sand*sandRotate[spread])/100;

                        if(sandX < 0 || sandY < 0 || sandX >= n || sandY >= n){
                            total += spreadAmount;
                        }else{
                            map[sandX][sandY] += spreadAmount;
                        }
                        spreadTotal += spreadAmount;
                    }

                    int alpX = nx + dx[d];
                    int alpY = ny + dy[d];
                    int alpAmount = sand - spreadTotal;
                    if(alpX < 0 || alpY < 0 || alpX >= n || alpY >= n){
                        total += alpAmount;
                    }else{
                        map[alpX][alpY] += alpAmount;
                    }
                    currentX = nx;
                    currentY = ny;
                }
            }
            for(int index = 0; index < 4; index++){
                dc[index] += 2;
            }
        }
    }//calculate end
}//class end
