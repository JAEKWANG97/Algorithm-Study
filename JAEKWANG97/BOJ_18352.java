package backjoon.algoStudy.day8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_18352 {


    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static int N, M, K, X; // 도시의 개스, 도로의 개수, 거리 정보, 출발 도시의 번호
    private static List<List<Integer>> adjList = new ArrayList<>();
    private static List<Integer> answerList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();
        bfs();
        if (!answerList.isEmpty()) {
            Collections.sort(answerList);
            for (Integer i : answerList) {
                System.out.println(i);
            }
        } else {
            System.out.println(-1);
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 정점 개수
        M = Integer.parseInt(st.nextToken()); // 간선 개수
        K = Integer.parseInt(st.nextToken()); // 원하는 거리
        X = Integer.parseInt(st.nextToken()); // 시작 노드

        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList.get(from).add(to);
        }

    }

    private static void bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        queue.add(X);
        visited[X] = true;
        int distance = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                if (distance == K) {
                    answerList.add(cur);
                }
                for (Integer i : adjList.get(cur)) {
                    if (visited[i]) {
                        continue;
                    }
                    queue.add(i);
                    visited[i] = true;
                }
            }
            distance += 1;
            if (distance > K) {
                return;
            }
        }
    }
}

