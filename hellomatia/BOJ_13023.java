package hellomatia;

import java.io.*;
import java.util.*;

class BOJ_13023 {

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static ArrayList<Integer>[] friendRelationships;
    static boolean[] visited;
    static boolean canABCDE = false;

    public static void main(String[] args) throws IOException {
        new BOJ_13023().solution();
    }

    public void solution() throws IOException {

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        friendRelationships = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            friendRelationships[i] = new ArrayList<>();
        }


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            friendRelationships[a].add(b);
            friendRelationships[b].add(a);
        }

        visited = new boolean[N];

        for (int i = 0; i < N; i++) {

            if (canABCDE) break;

            visited[i] = true;
            exploreFriendRelationship(i, 1);
            visited[i] = false;
        }

        if (canABCDE) {
            bw.write(1 + "\n");
        } else {
            bw.write(0 + "\n");
        }


        bw.flush();
        bw.close();
    }

    public void exploreFriendRelationship(int now, int count) {

        if (count == 5) {
            canABCDE = true;
            return;
        }

        for (int i = 0; i < friendRelationships[now].size(); i++) {

            int next = friendRelationships[now].get(i);

            if (visited[next]) {
                continue;
            }

            visited[next] = true;
            exploreFriendRelationship(next, count + 1);
            visited[next] = false;
        }
    }
}