import java.util.*;
import java.io.*;

public class BOJ_14499 {
    
    static int n, m, x, y, k, command, map[][], dice[];
    static int dy[] = {0, 0, 0, -1, 1};
    static int dx[] = {0, 1, -1, 0, 0};
    
    static void roll(int command) {
    	int tmp = 0;
    	switch (command) {
    	case 1: //동
    		tmp = dice[0];
    		dice[0] = dice[3];
    		dice[3] = dice[5];
    		dice[5] = dice[2];
    		dice[2] = tmp;
    		break;
    	case 2: //서
    		tmp = dice[0];
    		dice[0] = dice[2];
    		dice[2] = dice[5];
    		dice[5] = dice[3];
    		dice[3] = tmp;
    		break;
    	case 3: //북
			tmp = dice[0];  
			dice[0] = dice[4]; 
			dice[4] = dice[5]; 
			dice[5] = dice[1]; 
			dice[1] = tmp; 
    		break;
    	case 4: //남
    		tmp = dice[0];
    		dice[0] = dice[1];
    		dice[1] = dice[5];
    		dice[5] = dice[4];
    		dice[4] = tmp;
    		break;
    	}
    }
    
    static boolean isRange(int ty, int tx) {
    	if (ty >= 0 && tx >= 0 && ty < n && tx < m) return true;
    	else return false;
    }
    
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        //init
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m]; //지도
        dice = new int[6]; //주사위
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < m; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
        	command = Integer.parseInt(st.nextToken());
        	if (!isRange(y + dy[command], x + dx[command])) continue; //커맨드 불가면 다음으로
        	//좌표 이동하기
        	y += dy[command];
        	x += dx[command];
        	//커맨드에 따라 주사위 굴리기
        	roll(command);
        	//아래 칸 비교하기(위_idx : 0, 아래_idx 5)
        	if (map[y][x] == 0) { //이동한 칸의 수가 0이면 지도에 주사위 아래 넣기
        		map[y][x] = dice[5];
        	} else { //이동한 칸의 수가 0이 아니면 주사위 아래에 넣기 && 칸 수 없애기
        		dice[5] = map[y][x];
        		map[y][x] = 0;
        	}
        	System.out.println(dice[0]); //윗 칸 출력
        }
        
        
    }
    
}