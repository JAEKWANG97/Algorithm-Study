import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int C;

    private static int[] houses;
    private static int answer;

    public static void main(String[] args) throws IOException {
        init();
        binarySearch();
        printAnswer();
    }

    private static void binarySearch() {
        int minDistance = houses[0];
        int maxDistance = houses[N - 1] - houses[0];

        while (minDistance <= maxDistance) {
            int distance = (minDistance + maxDistance) / 2;

            int setRouterIdx = 0;
            int routerCount = 1;

            for (int i = 0; i < N; i++) {
                if (houses[i] - houses[setRouterIdx] >= distance) {
                    setRouterIdx = i;
                    routerCount++;
                }
            }

            if (routerCount < C) {
                maxDistance = distance - 1;
            } else {
                answer = minDistance;
                minDistance = distance + 1;
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        houses = new int[N];
        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(houses);
    }

    private static void printAnswer() {
        System.out.println(answer);
    }
}
