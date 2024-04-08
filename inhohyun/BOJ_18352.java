import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_18352 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 노드
        int m = Integer.parseInt(st.nextToken()); // 간선
        int k = Integer.parseInt(st.nextToken()); // 츨력해야되는 거리
        int x = Integer.parseInt(st.nextToken()); // 출발 노드

        List<Integer>[] edgeList = new List[n+1];

        for(int i = 1; i <= n; i++) {
            edgeList[i] = new LinkedList<>();
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            edgeList[a].add(b);
        }

        int[] distance = new int[n+1];
        Arrays.fill(distance, -1);

        Queue<Integer> q = new ArrayDeque<>();
        q.add(x);
        distance[x] = 0;

        List<Integer> answer = new ArrayList<>();

        while(!q.isEmpty()) {

            int cur = q.poll();

            if(distance[cur] > k) break;
            if(distance[cur] == k) {
                answer.add(cur);
            }
            for(int next : edgeList[cur]) {
                if(distance[next] != -1) continue;
                distance[next] = distance[cur] + 1;
                q.add(next);
            }
        }
        Collections.sort(answer);
        if(answer.size() == 0) {
            System.out.println(-1);
        }
        else {
            for(int i = 0; i < answer.size(); i++) {
                System.out.println(answer.get(i));
            }
        }




    }
}