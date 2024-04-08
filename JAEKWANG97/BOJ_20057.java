import java.util.*;
import java.io.*;

public class BOJ_20057 {
    static int[] dr = {0, 1, 0, -1}; //서, 남, 동, 북
    static int[] dc = {-1, 0, 1, 0};
    static int spreadR[][] = {
        {-1, 1, -1, 1, -1, 1, -2, 2, 0, 0},
        {0, 0, 1, 1, 2, 2, 1, 1, 3, 2},
        {-1, 1, -1, 1, -1, 1, -2, 2, 0, 0},
        {0, 0, -1, -1, -2, -2, -1, -1, -3, -2}
    };
    static int spreadC[][] = {
        {0, 0, -1, -1, -2, -2, -1, -1, -3, -2},
        {-1, 1, -1, 1, -1, 1, -2, 2, 0, 0},
        {0, 0, 1, 1, 2, 2, 1, 1, 3, 2},
        {-1, 1, -1, 1, -1, 1, -2, 2, 0, 0}
    };
    static int percent[] = { 1, 1, 7, 7, 10, 10, 2, 2, 5 };
    
    static int[][] sand;
    static int answer;
    static int n;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        sand = new int[n][n];
        
        for(int i=0; i<n; i++){
            StringTokenizer st= new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                sand[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int r, c;
        r = n/2;
        c = n/2;
        
        int moveCnt = 1;
        int dir = 0;

        while(true){
            for(int i=0; i<2; i++){ //moveCnt;
                for(int j=0; j<moveCnt; j++){
                    spreadSand(r, c, dir);
                    r += dr[dir];
                    c += dc[dir];
                }
                //방향 전환
                dir = (dir+1)%4;
            }

            moveCnt++;
    
            if(moveCnt==n){
                for(int i=0; i<moveCnt-1; i++){
                    spreadSand(r, c, dir);
                    r += dr[dir];
                    c += dc[dir];
                }
                break;
            }
        }

        System.out.println(answer);
    }
    
    private static void spreadSand(int r, int c, int dir){
        int nr = r + dr[dir];
        int nc = c + dc[dir]; 
        int curr = sand[nr][nc];
        
        for(int i=0; i<9; i++){
            int nnr = r + spreadR[dir][i];
            int nnc = c + spreadC[dir][i];
            
            int plus = curr*percent[i]/100;
            
            if(nnr<0 || nnc<0 || nnr>=n || nnc>=n){
                //격자 밖으로 나간 모래
                answer += plus;
            }
            else{
                sand[nnr][nnc] += plus;
                
            }
            sand[nr][nc] -= plus;
        }
        
        //a에 모래 이동
        int ar = r + spreadR[dir][9];
        int ac = c + spreadC[dir][9];
        if(ar<0 || ac<0 || ar>=n || ac>=n ){
            answer += sand[nr][nc];
        }
        else{
            sand[ar][ac] += sand[nr][nc];
        }
        sand[nr][nc] = 0;
        
    }
}
