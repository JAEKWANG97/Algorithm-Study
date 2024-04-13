package ex0302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SEA_4014 {
    private static int n,x,count;
    private static int[][] col = new int[20][20];;
    private static int[][] row = new int[20][20];;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= t; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            count = 0;

            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++){
                    col[i][j] = Integer.parseInt(st.nextToken());
                    row[j][i] = col[i][j];
                }
            }

            for(int i = 0; i < n; i++){
                if(check(col,i)){
                    count++;
                }
                if(check(row,i)){
                    count++;
                }
            }
            System.out.println("#" + tc + " " + count);
        }//test_case end
    }//main end

    private static boolean check(int[][] map, int idx){
        int cnt = 1;
        int height = map[idx][0];

        for(int i = 1; i < n; i++){
            if(height == map[idx][i]){  // 다음값이 같을 경우 카운트 증가
                cnt++;
            }else if(map[idx][i] - height == 1){ //오르막일 경우
                if(cnt < x){ // 카운트가 경사로의 길이보다 작을 경우 false
                    return false;
                }else{
                    cnt = 1;
                    height = map[idx][i];
                }
            }else if(height - map[idx][i] == 1){ //내리막일 경우
                if(n < x + i){ //경사로 + 남은 길이가 범위를 벗어나는경우
                    return false;
                }
                for(int j = 1; j < x; j++){
                    if(height - map[idx][++i] != 1){ // 경사로의 길이 만큼 설치를 못할 경우
                        return false;
                    }
                }
                height = map[idx][i];
                cnt = 0;
            }else{
                return false;
            }
        }
        return true;
    }//check end
}//class end
