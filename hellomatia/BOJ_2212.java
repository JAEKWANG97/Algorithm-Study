package hellomatia;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2212 {

    public static void main(String[] args) throws IOException {
        new BOJ_2212().solution();
    }

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int sensorCount = Integer.parseInt(bf.readLine());
        int collectionCount = Integer.parseInt(bf.readLine());
        int[] sensorCoordinate = new int[sensorCount];

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < sensorCount; i++) {
            sensorCoordinate[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(sensorCoordinate);

        int[] distanceBetweenSensors = new int[sensorCount - 1];

        for (int i = 0; i < sensorCount - 1; i++) {
            distanceBetweenSensors[i] = sensorCoordinate[i + 1] - sensorCoordinate[i];
        }


        Arrays.sort(distanceBetweenSensors);

        int minDistance = 0;
        int count = 0;

        while (count < (sensorCount - 1) - (collectionCount - 1)) {
            minDistance += distanceBetweenSensors[count++];
        }

        bw.write(String.valueOf(minDistance));

        bw.flush();
        bw.close();
    }
}