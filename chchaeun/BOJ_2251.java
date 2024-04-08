import java.io.*;
import java.util.*;

public class BOJ_2251 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[] max = new int[3];

    static class Combination{
        int[] bottle;

        Combination(int a, int b, int c){
            this.bottle = new int[]{a, b, c};
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Combination that = (Combination) o;
            return Arrays.equals(bottle, that.bottle);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(bottle);
        }

        int[] move(int from, int to){
            int[] newBottle = bottle.clone();
            if (max[to] - bottle[to] <= bottle[from]){
                newBottle[from] -= max[to] - bottle[to];
                newBottle[to] = max[to];
            }else{
                newBottle[to] += bottle[from];
                newBottle[from] = 0;
            }

            return newBottle;
        }
    }

    static void put(Queue<Combination> queue, HashSet<Combination> visited, HashSet<Integer> answer, Combination comb){
        if (!visited.contains(comb)){
            if (comb.bottle[0] == 0){
                answer.add(comb.bottle[2]);
            }
            visited.add(comb);
            queue.add(comb);
        }
    }

    public static void main(String[] args) throws IOException{

        HashSet<Integer> answer = new HashSet<>();
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 3; i++){
            max[i] = Integer.parseInt(st.nextToken());
        }

        answer.add(max[2]);

        Queue<Combination> queue = new LinkedList<>();
        HashSet<Combination> visited = new HashSet<>();

        Combination init = new Combination(0, 0, max[2]);
        queue.add(init);
        visited.add(init);

        while (!queue.isEmpty()){
            Combination current = queue.poll();

            for (int i = 0; i < 3; i++){
                int[] temp1 = current.move(i, (i + 1) % 3);
                int[] temp2 = current.move(i, (i + 2) % 3);
                Combination comb1 = new Combination(temp1[0], temp1[1], temp1[2]);
                Combination comb2 = new Combination(temp2[0], temp2[1], temp2[2]);

                int[] temp3 = comb1.move(i, (i + 2) % 3);
                int[] temp4 = comb2.move(i, (i + 1) % 3);

                Combination comb3 = new Combination(temp3[0], temp3[1], temp3[2]);
                Combination comb4 = new Combination(temp4[0], temp4[1], temp4[2]);

                put(queue, visited, answer, comb1);
                put(queue, visited, answer, comb2);
                put(queue, visited, answer, comb3);
                put(queue, visited, answer, comb4);
            }
        }

        answer.stream().sorted().forEach((element) -> System.out.print(element + " "));
    }
}
