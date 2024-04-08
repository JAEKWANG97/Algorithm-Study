package ex0318;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_15683 {

    private static class Point{
        int x;
        int y;
        int cctvNum;

        public Point(int x,int y,int cctvNum){
            this.x = x;
            this.y = y;
            this.cctvNum = cctvNum;
        }
    }
    private static int n,m,min;
    private static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        min = Integer.MAX_VALUE;
        ArrayList<Point> list = new ArrayList<>();


        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] != 0 && map[i][j] != 6){
                    list.add(new Point(i,j,map[i][j]));
                }
            }
        }

        dfs(map,0,list);
        System.out.println(min);
    }//main end

    private static void dfs(int[][] arr,int count, ArrayList<Point> list){
        if(count == list.size()){
            min = Math.min(min,Score(arr));
            return;
        }

        int cctvNum = list.get(count).cctvNum;
        int x = list.get(count).x;
        int y = list.get(count).y;
        int[][] tmp;
        if(cctvNum == 1){
            tmp = copyArr(arr);
            left(tmp,x,y);
            dfs(tmp,count+1,list);

            tmp = copyArr(arr);
            right(tmp,x,y);
            dfs(tmp,count+1,list);

            tmp = copyArr(arr);
            up(tmp,x,y);
            dfs(tmp,count+1,list);

            tmp = copyArr(arr);
            down(tmp,x,y);
            dfs(tmp,count+1,list);
        }else if(cctvNum == 2){
            tmp = copyArr(arr);
            left(tmp,x,y);
            right(tmp,x,y);
            dfs(tmp,count+1,list);

            tmp = copyArr(arr);
            up(tmp,x,y);
            down(tmp,x,y);
            dfs(tmp,count+1,list);
        }else if(cctvNum == 3){
            tmp = copyArr(arr);
            up(tmp,x,y);
            right(tmp,x,y);
            dfs(tmp,count+1,list);

            tmp = copyArr(arr);
            right(tmp,x,y);
            down(tmp,x,y);
            dfs(tmp,count+1,list);

            tmp = copyArr(arr);
            down(tmp,x,y);
            left(tmp,x,y);
            dfs(tmp,count+1,list);

            tmp = copyArr(arr);
            left(tmp,x,y);
            up(tmp,x,y);
            dfs(tmp,count+1,list);
        }else if(cctvNum == 4){
            tmp = copyArr(arr);
            left(tmp,x,y);
            up(tmp,x,y);
            right(tmp,x,y);
            dfs(tmp,count+1,list);

            tmp = copyArr(arr);
            up(tmp,x,y);
            right(tmp,x,y);
            down(tmp,x,y);
            dfs(tmp,count+1,list);

            tmp = copyArr(arr);
            right(tmp,x,y);
            down(tmp,x,y);
            left(tmp,x,y);
            dfs(tmp,count+1,list);

            tmp = copyArr(arr);
            down(tmp,x,y);
            left(tmp,x,y);
            up(tmp,x,y);
            dfs(tmp,count+1,list);
        }else{
            tmp = copyArr(arr);
            left(tmp,x,y);
            up(tmp,x,y);
            right(tmp,x,y);
            down(tmp,x,y);
            dfs(tmp,count+1,list);
        }
    }

    private static void left(int[][] arr,int x,int y){
        for(int i = y-1; i >= 0; i--){
            if(arr[x][i] == 6) return;
            if(arr[x][i] != 0) continue;
            arr[x][i] = -1;
        }
    }

    private static void right(int[][] arr,int x,int y){
        for(int i = y+1; i < m; i++){
            if(arr[x][i] == 6) return;
            if(arr[x][i] != 0) continue;
            arr[x][i] = -1;
        }
    }

    private static void up(int[][] arr,int x,int y){
        for(int i = x-1; i >= 0; i--){
            if(arr[i][y] == 6) return;
            if(arr[i][y] != 0) continue;
            arr[i][y] = -1;
        }
    }

    private static void down(int[][] arr,int x,int y){
        for(int i = x+1; i < n; i++){
            if(arr[i][y] == 6) return;
            if(arr[i][y] != 0) continue;
            arr[i][y] = -1;
        }
    }

    private static int Score(int[][] arr){
        int result = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(arr[i][j] == 0){
                    result++;
                }
            }
        }
        return result;
    }

    private static int[][] copyArr(int[][] arr){
        int[][] temp = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                temp[i][j] = arr[i][j];
            }
        }
        return temp;
    }
}//class end
