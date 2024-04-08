import java.io.*;
import java.util.*;

public class BOJ_14889 {
    static int N, middle, idxA, idxB, sumA, sumB, answer;
    static int[][] arr;
    static int[] A, B;
    static boolean[] visited;

    static void helper(int depth, int start) {
        if (answer == 0) {
            return;
        }

        if (depth == middle) {
            makeTeam();
            calcAbility();
            return;
        }

        for (int i = start; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                helper(depth + 1, i + 1);
                visited[i] = false;
            }
        }
    }

    static void calcAbility() {
        for (int i = 0; i < middle - 1; i++) {
            for (int j = i + 1; j < middle; j++) {
                sumA += arr[A[i]][A[j]] + arr[A[j]][A[i]];
                sumB += arr[B[i]][B[j]] + arr[B[j]][B[i]];
            }
        }
        answer = Math.min(answer, Math.abs(sumA - sumB));
    }

    static void makeTeam() {
        A = new int[middle];
        B = new int[middle];
        idxA = idxB = sumA = sumB = 0;
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                A[idxA++] = i;
            } else {
                B[idxB++] = i;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        visited = new boolean[N];
        answer = Integer.MAX_VALUE;
        middle = N / 2;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        helper(0, 0);
        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}