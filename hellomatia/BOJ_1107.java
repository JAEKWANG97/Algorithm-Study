package hellomatia;

import java.io.*;
import java.util.*;

public class BOJ_1107 {
    int targetChannel;
    int targetChannelDigitCount;
    int[] button;
    int minCount = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        new BOJ_1107().solution();
    }

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = bf.readLine();
        targetChannelDigitCount = str.length();
        targetChannel = Integer.parseInt(str);
        int brokenButtonCount = Integer.parseInt(bf.readLine());

        boolean[] brokenButton = new boolean[10];

        if (brokenButtonCount == 0) {
            minCount = Math.min(Math.abs(100 - targetChannel), targetChannelDigitCount);
            bw.write(minCount + "\n");
            bw.flush();
            bw.close();
            return;

        } else if (brokenButtonCount == 10) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            for (int i = 0; i < brokenButtonCount; i++) {
                str = st.nextToken();
            }

            minCount = Math.abs(100 - targetChannel);
            bw.write(minCount + "\n");
            bw.flush();
            bw.close();
        }

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < brokenButtonCount; i++) {
            brokenButton[Integer.parseInt(st.nextToken())] = true;
        }

        button = new int[10 - brokenButtonCount];

        int index = 0;
        for (int i = 0; i < 10; i++) {
            if (!brokenButton[i]) {
                button[index++] = i;
            }
        }

        if (targetChannel == 100) {
            minCount = 0;
        } else {
            countButtonPress(0, 0);
            minCount = Math.min(Math.abs(100 - targetChannel), minCount);
        }


        bw.write(minCount + "\n");

        bw.flush();
        bw.close();
    }

    public void countButtonPress(int number, int count) {

        if (count > targetChannelDigitCount) {
            return;
        }

        for (int i = 0; i < button.length; i++) {
            int chanel = number * 10;
            chanel += button[i];
            minCount = Math.min(Math.abs((targetChannel - chanel)) + count + 1, minCount);
            countButtonPress(chanel, count + 1);
        }

    }
}