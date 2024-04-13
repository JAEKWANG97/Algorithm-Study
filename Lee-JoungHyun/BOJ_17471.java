//1. 그냥 부분조합으로 뽑아버리기??
//10개의 부분조합 -> 얼마 안됨 -> 비트마스킹!
//1. 해당 부분조합이 가능한지? -> BFS?

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17471 {
    static int N, areaCnt[], answer = Integer.MAX_VALUE;
    static ArrayList<Integer>[] graph;
    static ArrayList<Integer> teamA, teamB;
    static HashSet<ArrayList<Integer>> hs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        hs = new HashSet<>();
        N = Integer.parseInt(br.readLine());
        areaCnt = new int[N];
        graph = new ArrayList[N];
        teamA = new ArrayList<>();
        teamB = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            areaCnt[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int tmp = Integer.parseInt(st.nextToken());
            for (int j = 0; j < tmp; j++) {
                graph[i].add(Integer.parseInt(st.nextToken())-1);
            }
        }   // 입력 끝

//        for (int i = 0; i < N; i++)
//            System.out.println(graph[i]);

        // 1. 조합 뽑기
        makeSet(0, 0);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
    static void makeSet(int cnt, int code) {
        if (cnt == N) {
            // code 값으로 팀 나눠 전달
            teamA.clear(); // 선택
            teamB.clear(); // 미선택
            //System.out.println(code);
            for (int i = 0; i < cnt; i++) {
                if ((code & (1 << i)) > 0) {
                    teamA.add(i);
                } else teamB.add(i);
            }
            if (teamA.size() == 0 || teamB.size() == 0 || hs.contains(teamA)) return;
            hs.add(teamA);
            hs.add(teamB);
            int diff = calcDiff();
            if (diff < answer && validate()) {
                answer = diff;
            }
            return;
        }
        // 내자리 뽑기
        makeSet(cnt+1, code + (1 << cnt));
        // 내자리 안뽑기
        makeSet(cnt+1, code);
    }
    static int calcDiff() {
        int a = 0, b = 0;
        for (int tmp : teamA) {
            a += areaCnt[tmp];
        }
        for (int tmp : teamB) {
            b += areaCnt[tmp];
        }
        return Math.abs(a - b);
    }
    static boolean validate() {
        // 아

        Queue<Integer> queue = new LinkedList<>();
        int now;
        boolean visited[] = new boolean[N];

        // 1. teamA가 모두 연결되어 있는지 확인
        queue.add(teamA.get(0));
        visited[teamA.get(0)] = true;
        while (!queue.isEmpty()) {
            now = queue.poll();
            for (int nxt : graph[now]) {
                if (teamA.contains(nxt) && !visited[nxt]) {
                    visited[nxt] = true;
                    queue.add(nxt);
                }
            }
        }
        queue.add(teamB.get(0));
        visited[teamB.get(0)] = true;
        while (!queue.isEmpty()) {
            now = queue.poll();
            for (int nxt : graph[now]) {
                if (teamB.contains(nxt) && !visited[nxt]) {
                    visited[nxt] = true;
                    queue.add(nxt);
                }
            }
        }

        for (int i = 0; i < N; i++)
            if(!visited[i]) return false;
        return true;
    }
}
