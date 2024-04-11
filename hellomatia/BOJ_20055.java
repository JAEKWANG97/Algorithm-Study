package hellomatia;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_20055 {

    public static void main(String[] args) throws IOException {
        new BOJ_20055().solution();
    }

    int N, K; //길이가 N, 내구도가 0인 칸이 K개 일 경우
    int cur;
    int[][] conveyorBelt;
    int answer;

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        conveyorBelt = new int[2 * N][2];

        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < 2 * N; i++) {
            conveyorBelt[i][0] = Integer.parseInt(st.nextToken());
        }


        simulation(0);
        bw.write(answer + "\n");

        bw.flush();
        bw.close();
    }

    public void simulation(int count) {
        if (!check()) {
            answer = count;
            /*
            for(int i=0; i<2*N; i++) {
                System.out.print(conveyorBelt[i][0]+" ");
            }
             */

            return;
        }

        int start = (cur + (2 * N - 1)) % (2 * N);
        int end = (start + (N - 1)) % (2 * N);

        cur = start;

        conveyorBelt[end][1] = 0;

        robotMove();

        if (conveyorBelt[start][0] != 0) {
            conveyorBelt[start][0]--;
            conveyorBelt[start][1] = 1;
        }

        conveyorBelt[end][1] = 0;


        simulation(count + 1);


    }

    public void robotMove() {
        for (int i = N - 2; i >= 0; i--) {
            int idx1 = (cur + i) % (2 * N);
            int idx2 = (idx1 + 1) % (2 * N);

            if (conveyorBelt[idx1][1] == 1 && conveyorBelt[idx2][1] == 0 && conveyorBelt[idx2][0] != 0) {
                conveyorBelt[idx2][0]--;
                conveyorBelt[idx2][1]++;
                conveyorBelt[idx1][1]--;
            }

        }
    }

    public boolean check() {
        int count = 0;

        for (int i = 0; i < 2 * N; i++) {
            if (conveyorBelt[i][0] == 0) {
                count++;
            }
        }

        if (count < K) return true;
        else return false;
    }
}