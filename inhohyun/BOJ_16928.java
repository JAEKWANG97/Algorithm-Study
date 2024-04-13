package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16928 {
    static int n, m, board[];
    static int min;
    public static void main(String[] args) throws IOException {
        init();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[101];

        for (int i = 1; i < board.length; i++) {
            board[i] = i;
        }


        for(int i = 0; i < n+m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            board[start] = end;
        }

        int ans = bfs(1);
        System.out.println(ans);



    }
    static int bfs(int start) {
        int[] check = new int[101];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);

        check[start] = 0;

        while(!q.isEmpty()) {
            int visitN = q.poll();
            //주사위 굴리기
            for(int i = 1; i <= 6; i ++) {
                int newN = visitN + i;

                if(newN > 100) {
                    continue;
                }
                //아직 방문안한 노드면
                if(check[board[newN]] == 0) {
                    q.add(board[newN]);

                    //주사위를 1번 굴려서 다음 수 구하기
                    check[board[newN]] = check[visitN]+ 1;
                }
                if(board[newN] == 100) {
                    return check[100];
                }
            }



        }




        return check[100];
    }
}
