import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 8983. 사냥꾼 / 90분
public class BOJ_8983 {
    static StringBuilder sb = new StringBuilder();
    static int N, M, L, count, shootSpot[];
    static HashMap<Integer, Queue<Integer>> animals;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(sb.toString());
    }

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        shootSpot = new int[M];
        animals = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            shootSpot[i] = Integer.parseInt(st.nextToken());
        }

        int r, c;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            Queue<Integer> tmp = animals.getOrDefault(r, new ArrayDeque<>());
            tmp.offer(c);
            animals.put(r, tmp);
        }

        solve();
    }

    private static void solve() throws Exception {
        List<Integer> keySet = new ArrayList<>(animals.keySet());
//        Collections.sort(keySet, Collections.reverseOrder());
        Arrays.sort(shootSpot);
        for (int i = 0; i < keySet.size(); i++) {
            int key = keySet.get(i);
            int spot = Arrays.binarySearch(shootSpot, key);
            if (spot < 0) spot = findSpot(spot, key);
            Queue<Integer> animal = animals.get(key);
            int size = animal.size();
            for (int k = 0; k < size; k++) {
                int dist = Math.abs(shootSpot[spot] - key ) + animal.peek();
//                System.out.printf("range: %d, key: %d, dist: %d %n",L , key, dist);
                if (dist <= L) {
                    count++;
                    animal.poll();
                } else {
                    animal.offer(animal.poll());
                }
            }
        }

        sb.append(count);
    }

    private static int findSpot(int spot, int x) {
        spot = -spot - 1; // 가장 가까운 위치의 인덱스로 변환
        int leftSpot = spot - 1;
        int rightSpot = spot;

        // 왼쪽 사격 위치와의 거리 계산
        int leftDist = (leftSpot >= 0) ? x - shootSpot[leftSpot] : Integer.MAX_VALUE;
        // 오른쪽 사격 위치와의 거리 계산
        int rightDist = (rightSpot < shootSpot.length) ? shootSpot[rightSpot] - x : Integer.MAX_VALUE;

        // 가까운 위치 결정
        if (leftDist <= rightDist) {
            return (leftSpot >= 0) ? leftSpot : rightSpot;
        } else {
            return (rightSpot < shootSpot.length) ? rightSpot : leftSpot;
        }
    }
}