package hellomatia;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;


public class BOJ_1946 {

    public static void main(String[] args) throws IOException {
        new BOJ_1946().solution();
    }

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(bf.readLine());

        for (int i = 0; i < testCaseCount; i++) {

            int applicantCount = Integer.parseInt(bf.readLine());
            int[][] applicantScore = new int[applicantCount][2];

            for (int j = 0; j < applicantCount; j++) {
                StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
                applicantScore[j][0] = Integer.parseInt(st.nextToken());
                applicantScore[j][1] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(applicantScore, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });

            int numOfPassengers = 1;
            int minScore = applicantScore[0][1];

            for (int j = 1; j < applicantCount; j++) {


                if (applicantScore[j][1] < minScore) {
                    minScore = applicantScore[j][1];
                    numOfPassengers++;
                }

            }
            bw.write(String.valueOf(numOfPassengers));
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
}