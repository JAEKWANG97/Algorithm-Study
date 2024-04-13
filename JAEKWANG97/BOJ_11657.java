package backjoon.algoStudy.day8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_11657 {
    static class Node {
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static List<List<Node>> adjList = new ArrayList<>();
    static final Long INF = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        init();
        BellmanFord();
        System.out.println(sb.toString());
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList.get(from).add(new Node(to, weight));
        }
    }

    private static void BellmanFord() {
        Long[] distance = new Long[N + 1];
        Arrays.fill(distance, INF);
        distance[1] = 0L; // 가정: 1번 정점에서 시작

        // 모든 정점에 대해 N-1번 반복
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= N; j++) {
                for (Node node : adjList.get(j)) {
                    if (distance[j] != INF && distance[j] + node.weight < distance[node.to]) {
                        distance[node.to] = distance[j] + node.weight;
                    }
                }
            }
        }

        // 음수 가중치 사이클 검사
        for (int j = 1; j <= N; j++) {
            for (Node node : adjList.get(j)) {
                if (distance[j] != INF && distance[j] + node.weight < distance[node.to]) {
                    System.out.println("-1");
                    return;
                }
            }
        }

        // 결과 출력
        for (int i = 2; i <= N; i++) {
            if (distance[i] == INF) {
                sb.append("-1").append("\n");
            } else {
                sb.append(distance[i]).append("\n");
            }
        }
    }
}
