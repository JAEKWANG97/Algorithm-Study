package hellomatia;

import java.io.*;
import java.util.*;

public class BOJ_1041 {

    public static void main(String[] args) throws IOException {
        new BOJ_1041().solution();
    }

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long diceCount = Integer.parseInt(bf.readLine());
        int[] dice = new int[6];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        long minNum = Integer.MAX_VALUE;

        for (int i = 0; i < 6; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
            if (dice[i] < minNum) {
                minNum = dice[i];
            }
        }

        if (diceCount == 1) {
            int sum = 0;
            Arrays.sort(dice);
            for (int i = 0; i < 5; i++) {
                sum += dice[i];
            }
            bw.write(String.valueOf(sum));
            bw.flush();
            bw.close();
            return;
        }

        //3면이 나와야 할때
        long treeMin = Integer.MAX_VALUE;
        int temp = dice[0] + dice[1] + dice[2];
        if (temp < treeMin) treeMin = temp;
        temp = dice[0] + dice[1] + dice[3];
        if (temp < treeMin) treeMin = temp;
        temp = dice[0] + dice[2] + dice[4];
        if (temp < treeMin) treeMin = temp;
        temp = dice[0] + dice[3] + dice[4];
        if (temp < treeMin) treeMin = temp;
        temp = dice[1] + dice[2] + dice[5];
        if (temp < treeMin) treeMin = temp;
        temp = dice[1] + dice[3] + dice[5];
        if (temp < treeMin) treeMin = temp;
        temp = dice[2] + dice[4] + dice[5];
        if (temp < treeMin) treeMin = temp;
        temp = dice[3] + dice[4] + dice[5];
        if (temp < treeMin) treeMin = temp;

        //2면이 나와야 할때
        long twoMin = Integer.MAX_VALUE;
        temp = dice[0] + dice[1];
        if (temp < twoMin) twoMin = temp;
        temp = dice[0] + dice[2];
        if (temp < twoMin) twoMin = temp;
        temp = dice[0] + dice[3];
        if (temp < twoMin) twoMin = temp;
        temp = dice[0] + dice[4];
        if (temp < twoMin) twoMin = temp;
        temp = dice[1] + dice[2];
        if (temp < twoMin) twoMin = temp;
        temp = dice[1] + dice[3];
        if (temp < twoMin) twoMin = temp;
        temp = dice[1] + dice[5];
        if (temp < twoMin) twoMin = temp;
        temp = dice[2] + dice[5];
        if (temp < twoMin) twoMin = temp;
        temp = dice[2] + dice[4];
        if (temp < twoMin) twoMin = temp;
        temp = dice[3] + dice[4];
        if (temp < twoMin) twoMin = temp;
        temp = dice[3] + dice[5];
        if (temp < twoMin) twoMin = temp;
        temp = dice[4] + dice[5];
        if (temp < twoMin) twoMin = temp;

        //3면이 나오는 횟수 계산
        long treeCount = 4;

        //2면이 나오는 횟수 계산
        long twoCount = ((diceCount - 1) * 4) + ((diceCount - 2) * 4);

        //1면이 나오는 횟수 계산
        long oneCount = ((diceCount - 2) * (diceCount - 1) * 4) + ((diceCount - 2) * (diceCount - 2));

        long ans = treeMin * treeCount + twoMin * twoCount + minNum * oneCount;

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }
}