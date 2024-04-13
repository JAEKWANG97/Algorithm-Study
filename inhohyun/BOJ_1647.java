import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1647 {
    static class Node implements Comparable<Node>{
        int a, b, value;

        Node(int a, int b, int value){
            this.a = a;
            this.b= b;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {

            return Integer.compare(this.value, o.value);
        }
    }
    static int[] parents;
    static ArrayList<Node> road = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parents = new int[n+1];

        for(int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        for(int i = 0; i <m; i ++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            road.add(new Node(a,b,c));
        }

        Collections.sort(road); // 유지비 기준 길 오름차순 정렬
        int sum = 0;
        int max = -1;

        //크루스칼
        for(Node node : road) {
            //연결 가능여부
            if(find(node.a) != find(node.b)) {
                union(node.a, node.b);
                sum += node.value;
                max = Math.max(max, node.value);
            }
        }
        sum -= max; //마을을 분리하기 위해 최대 길은 제거
        System.out.println(sum);

    }

    static int find(int a) {
        if(parents[a] == a) return a;

        return parents[a] = find(parents[a]);

    }

    static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot < bRoot)
            parents[bRoot] = parents[aRoot];
        else {
            parents[aRoot] = parents[bRoot];
        }
    }
}
