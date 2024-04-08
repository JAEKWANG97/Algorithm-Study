package ex0320;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_21608 {

    private static class Shark implements Comparable<Shark>{
        int like;
        int clean;
        int x;
        int y;
        public Shark(int like,int clean,int x,int y){
            this.like = like;
            this.clean = clean;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Shark o) {
            if(this.like != o.like){
                return o.like - this.like;
            }
            if(this.clean != o.clean){
                return o.clean - this.clean;
            }
            if(this.x != o.x){
                return o.x - this.x;
            }
            return o.y - this.y;
        }
    }
    private static int n;
    private static int[][] arr;
    private static int[] num;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};
    private static ArrayList<Integer>[] list;
    private static PriorityQueue<Shark> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1][n+1];
        list = new ArrayList[n*n+1];
        num = new int[n*n+1];

        for(int i = 1; i < n*n+1; i++){
            list[i] = new ArrayList<>();
        }


        for(int i = 1; i < n*n+1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            num[i] = Integer.parseInt(st.nextToken());
            for(int j = 1; j <= 4; j++){
                list[num[i]].add(Integer.parseInt(st.nextToken()));
            }
        }

        for(int i = 1; i < n*n+1; i++){
            check(num[i]);
            pq.clear();
        }

        int total = 0;

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                total += result(i,j);
            }
        }

        System.out.println(total);
    }//main end

    private static int result(int x,int y){
        int num = arr[x][y];
        int count = 0;

        int nx,ny;

        for(int i : list[num]){
            for(int d = 0; d < 4; d++){
                nx = x + dx[d];
                ny = y + dy[d];

                if(nx >= 1 && ny >= 1 && nx <= n && ny <= n){
                    if(arr[nx][ny] == i){
                        count++;
                    }
                }
            }
        }

        if(count == 0){
            return 0;
        }else if(count == 1){
            return 1;
        }else if(count ==2){
            return 10;
        }else if(count == 3){
            return 100;
        } else {
            return 1000;
        }
    }

    private static void check(int num){
        int like, clean;
        int nx,ny;

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                like = 0;
                clean = 0;

                if(arr[i][j] != 0){
                    continue;
                }

                for(int d = 0; d < 4; d++){
                    nx = i + dx[d];
                    ny = j + dy[d];

                    if(nx >= 1 && ny >= 1 && nx <= n && ny <= n){
                        for(int k : list[num]){
                            if(arr[nx][ny] == k){
                                like++;
                            }
                        }
                        if(arr[nx][ny] == 0){
                            clean++;
                        }
                    }
                }
                pq.offer(new Shark(like,clean,i,j));
            }
        }

        Shark shark = pq.poll();
        arr[shark.x][shark.y] = num;
    }//check end
}//class end
