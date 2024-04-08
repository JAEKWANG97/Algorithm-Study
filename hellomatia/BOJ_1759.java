package hellomatia;

import java.io.*;
import java.util.*;

public class BOJ_1759 {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int L, C;
    private List<Character> jChs;
    private List<Character> mChs;
    private PriorityQueue<String> pq;
    private char[] word;

    public static void main(String[] args) throws IOException {
        new BOJ_1759().solution();
    }

    private void solution() throws IOException {
        init();
        calcResult();
        printResult();
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        jChs = new ArrayList<>();
        mChs = new ArrayList<>();

        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < C; i++) {
            char ch = st.nextToken().charAt(0);
            if (isJ(ch)) jChs.add(ch);
            if (isM(ch)) mChs.add(ch);
        }
    }

    private void calcResult() {
        pq = new PriorityQueue<>();
        word = new char[L];
        pickJ(0, 0);
    }

    private void pickJ(int count, int start) {
        if (count == L) {
            return;
        }

        if (2 <= count) {
            pickM(count, 0);
        }

        for (int i = start; i < jChs.size(); i++) {
            word[count] = jChs.get(i);
            pickJ(count + 1, i + 1);
        }
    }

    private void pickM(int count, int start) {
        if (count == L) {
            char[] temp = word.clone();
            Arrays.sort(temp);
            pq.offer(String.valueOf(temp));
            return;
        }

        for (int i = start; i < mChs.size(); i++) {
            word[count] = mChs.get(i);
            pickM(count + 1, i + 1);
        }
    }

    private boolean isJ(char ch) {
        return !isM(ch);
    }

    private boolean isM(char ch) {
        return ch == 'a' || ch == 'e' ||ch == 'i' || ch == 'o' || ch == 'u';
    }

    private void printResult() throws IOException {
        while (!pq.isEmpty()) {
            bw.write(pq.poll() + "\n");
        }
        bw.flush();
    }
}
