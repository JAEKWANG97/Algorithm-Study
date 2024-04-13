import java.io.*;
import java.util.*;
 
public class BOJ_20057 {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static double[] per = {1,1,2,2,7,7,10,10,5,0};
    static int[][] sdx = {
            {-1,1,-2,2,-1,1,-1,1,0,0}, // 좌 
            {-1,-1,0,0,0,0,1,1,2,1}, // 하 
            {-1,1,-2,2,-1,1,-1,1,0,0}, // 우
            {1,1,0,0,0,0,-1,-1,-2,-1}, // 상 
    };
    static int[][] sdy = {
            {1,1,0,0,0,0,-1,-1,-2,-1}, // 좌 
            {-1,1,-2,2,-1,1,-1,1,0,0}, // 하 
            {-1,-1,0,0,0,0,1,1,2,1}, // 우
            {-1,1,-2,2,-1,1,-1,1,0,0}, // 상 
    };
    
    static int[][] map;
    static int N;
    static int ans = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        Move(N/2, N/2);
        System.out.println(ans);
    }
    
    public static void Move(int x, int y) {
        int[] changeOfDir = {1, 1, 2, 2};
        double ySand;
        
        while (true) {
            for (int d=0; d<4; d++) {
                for (int i=0; i<changeOfDir[d]; i++) {
                    x += dx[d];
                    y += dy[d];
                    
                    ySand = map[x][y]; 
                
                    if (ySand > 0) {
                        spread(x, y, d, ySand);
                    }
                    
                    if (x == 0 && y == 0) {
                        return;
                    }
                }
            }
            
            for (int k=0; k<4; k++) {
                changeOfDir[k] += 2;
            }
        }
    }
    
    public static void spread(int x, int y, int d, double ySand) {
        int nx, ny;
        double spreadSands = 0; // 이동한 모래 양 
        
        // 남은 모래는 a 위치로 이동하기때문에 0 
        map[x][y] = 0;
        
        for(int i=0; i<10; i++) {
            nx = x + sdx[d][i];
            ny = y + sdy[d][i];
            
            int sand = (int) Math.floor(ySand / 100 * per[i]);
            
            if (i == 9) {
               
                double aSand = ySand - spreadSands; // 이동하고 남은 모래 양
                
                if (!RangeCheck(nx,ny)) {
                    ans += aSand;
                } else {
                    map[nx][ny] += aSand;
                }
            } else {
                if (!RangeCheck(nx,ny)) {
                    ans += sand;
                } else {
                    map[nx][ny] += sand; 
                }
                spreadSands += sand;
            }
            
        }
    }
    
    public static boolean RangeCheck(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N ? true : false;
    }
}
