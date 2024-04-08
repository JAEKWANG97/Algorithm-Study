package hellomatia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ_2110 {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private int N, C;
    private TreeSet<Integer> houses;

    public static void main(String[] args) throws IOException {
        new BOJ_2110().solution();
    }

    private void solution() throws IOException {
        init();
        printResult(calcResult());
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        houses = new TreeSet<>();
        for (int i = 0; i < N; i++) {
            houses.add(Integer.parseInt(bf.readLine()));
        }
    }

    private int calcResult() {

        int minDist = 0;
        int maxDist = houses.last() - houses.first();

        while (minDist <= maxDist) {

            int midDist = (maxDist + minDist) / 2;

            int routerCount = 0;
            Integer now = houses.first();
            while (now != null) {
                now = houses.higher(now + midDist);
                routerCount++;
            }

            if (routerCount < C) {
                maxDist = midDist - 1;
            } else {
                minDist = midDist + 1;
            }
        }

        return minDist;
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
        bw.flush();
    }
}
