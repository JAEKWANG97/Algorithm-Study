package backjoon.algoStudy.day9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class BOJ_1976 {
    static int[] parent;
    static int[][] city;

    public static void main(String[] args) throws IOException {
        String answer = "YES";
        ArrayList<Integer> trip = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        city = new int[N + 1][N + 1];
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    union(i, j);
                }
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            trip.add(Integer.parseInt(st.nextToken()));
        }
        int check = find(trip.get(0));
        for (int i = 1; i < trip.size(); i++) {
            if (find(trip.get(i)) != check) {
                answer = "NO";
                break;
            }
        }
        System.out.println(answer);
    }

    public static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        int y = find(parent[x]);
        parent[x] = y;
        return y;
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) {
            return;
        }
        parent[y] = x;
    }
}