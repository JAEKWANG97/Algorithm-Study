package hellomatia;

import java.io.*;
import java.util.*;

public class CT_코드트리메신저 {

    private final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    private int N;
    private int Q;
    private int[] parent;
    private int[] authority;
    private int[][] nx;
    private int[] value;
    private boolean[] notice;

    public static void main(String[] args) throws IOException {
        new CT_코드트리메신저().solution();
    }

    public void solution() throws IOException {
        init();
        processQueries();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        authority = new int[N + 1];
        notice = new boolean[N + 1];

        st = new StringTokenizer(bf.readLine());
        st.nextToken();
        for (int i = 1; i <= N; i++) {
            parent[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= N; i++) {
            authority[i] = Math.min(Integer.parseInt(st.nextToken()), 20);
        }

        initNx();
    }

    private void initNx() {
        nx = new int[N + 1][21];
        value = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            int cur = i;
            int x = authority[i];
            nx[cur][x]++;
            while (parent[cur] != 0 && x != 0) {
                cur = parent[cur];
                x--;
                if (x != 0) nx[cur][x]++;
                value[cur]++;
            }
        }
    }

    private void processQueries() throws IOException {
        for (int i = 1; i < Q; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int queryType = Integer.parseInt(st.nextToken());
            switch (queryType) {
                case 200:
                    int chat = Integer.parseInt(st.nextToken());
                    toggleNotice(chat);
                    break;
                case 300:
                    chat = Integer.parseInt(st.nextToken());
                    int power = Integer.parseInt(st.nextToken());
                    changePower(chat, power);
                    break;
                case 400:
                    int chat1 = Integer.parseInt(st.nextToken());
                    int chat2 = Integer.parseInt(st.nextToken());
                    changeParent(chat1, chat2);
                    break;
                case 500:
                    chat = Integer.parseInt(st.nextToken());
                    printCount(chat);
                    break;
                default:
                    break;
            }
        }
    }

    private void toggleNotice(int chat) {
        notice[chat] = !notice[chat];
        int cur = parent[chat];
        int num = 1;
        if (notice[chat]) {
            // 알림을 켤 때
            while (cur != 0) {
                for (int i = num; i <= 20; i++) {
                    value[cur] -= nx[chat][i];
                    if (i > num) nx[cur][i - num] -= nx[chat][i];
                }
                if (notice[cur]) break;
                cur = parent[cur];
                num++;
            }
        } else {
            // 알림을 끌 때
            while (cur != 0) {
                for (int i = num; i <= 20; i++) {
                    value[cur] += nx[chat][i];
                    if (i > num) nx[cur][i - num] += nx[chat][i];
                }
                if (notice[cur]) break;
                cur = parent[cur];
                num++;
            }
        }
    }

    private void changePower(int chat, int power) {
        int beforePower = authority[chat];
        power = Math.min(power, 20);
        authority[chat] = power;
    
        nx[chat][beforePower]--;
        if (!notice[chat]) {
            int cur = parent[chat];
            int num = 1;
            while (cur != 0) {
                if (beforePower >= num) value[cur]--;
                if (beforePower > num) nx[cur][beforePower - num]--;
                if (notice[cur]) break;
                cur = parent[cur];
                num++;
            }
        }
    
        nx[chat][power]++;
        if (!notice[chat]) {
            int cur = parent[chat];
            int num = 1;
            while (cur != 0) {
                if (power >= num) value[cur]++;
                if (power > num) nx[cur][power - num]++;
                if (notice[cur]) break;
                cur = parent[cur];
                num++;
            }
        }
    }

    private void changeParent(int chat1, int chat2) {
        boolean beforeNoticeChat1 = notice[chat1];
        boolean beforeNoticeChat2 = notice[chat2];
    
        // 알림 상태가 꺼져 있다면, 알림을 임시로 켜서 변경 사항을 적용합니다.
        if (!notice[chat1]) toggleNotice(chat1);
        if (!notice[chat2]) toggleNotice(chat2);
    
        // 부모를 교체합니다.
        int temp = parent[chat1];
        parent[chat1] = parent[chat2];
        parent[chat2] = temp;
    
        // 원래 알림 상태로 복구합니다.
        if (!beforeNoticeChat1) toggleNotice(chat1);
        if (!beforeNoticeChat2) toggleNotice(chat2);
    }

    private void printCount(int chat) throws IOException {
        bw.write(value[chat] + "\n");
        bw.flush();
    }
}