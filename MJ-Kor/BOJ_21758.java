package algorithm_study.april.day_04_13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_21758 {
	
    public static long[] honey;
    public static int N;
    public static long result;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        honey = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            long a = Long.parseLong(st.nextToken());
            honey[i] = a;
        }

        result = 0;

        long fixedBee = Arrays.stream(honey).sum() - honey[0];
        long movingBee = fixedBee;

        for (int i = 1; i <= honey.length - 2 ; i++) {
            long sum = fixedBee - honey[i];
            movingBee = movingBee - honey[i];
            sum += movingBee;
            result = Math.max(sum, result);
        }

        fixedBee = fixedBee + honey[0] - honey[honey.length - 1];
        movingBee = fixedBee;
        for (int i = honey.length - 2; i >= 0 ; i--) {
            long sum = fixedBee - honey[i];
            movingBee = movingBee - honey[i];
            sum += movingBee;
            result = Math.max(sum, result);
        }


        for (int i = 1; i <= honey.length - 2; i++) {
            long sum = 0;
            for (int j = 1; j <= i; j++) {
                sum += honey[j];
            }
            for (int j = honey.length - 2; j >= i ; j--) {
                sum += honey[j];
            }
            result = Math.max(sum, result);
        }

        System.out.println(result);
    }
}
