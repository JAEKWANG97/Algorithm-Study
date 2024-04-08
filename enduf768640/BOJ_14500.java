import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N, M;
    private static int[][] map;

    private static int answer;

    public static void main(String[] args) throws IOException {
        init();
        check();
        printAnswer();
    }

    private static void check() {
        checkShape1();
        checkShape2();
        checkShape3();
        checkShape4();
        checkShape5();
    }

    private static void checkShape1() {
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M - 3; x++) {
                answer = Math.max(answer, map[y][x] + map[y][x + 1] + map[y][x + 2] + map[y][x + 3]);
            }
        }

        for (int y = 0; y < N - 3; y++) {
            for (int x = 0; x < M; x++) {
                answer = Math.max(answer, map[y][x] + map[y + 1][x] + map[y + 2][x] + map[y + 3][x]);
            }
        }
    }

    private static void checkShape2() {
        for (int y = 0; y < N - 1; y++) {
            for (int x = 0; x < M - 1; x++) {
                answer = Math.max(answer, map[y][x] + map[y][x + 1] + map[y + 1][x] + map[y + 1][x + 1]);
            }
        }
    }

    private static void checkShape3() {
        for (int y = 0; y < N - 2; y++) {
            for (int x = 0; x < M - 1; x++) {
                answer = Math.max(answer, map[y][x] + map[y + 1][x] + map[y + 2][x] + map[y + 2][x + 1]);
            }
        }

        for (int y = 0; y < N - 2; y++) {
            for (int x = 0; x < M - 1; x++) {
                answer = Math.max(answer, map[y][x + 1] + map[y + 1][x + 1] + map[y + 2][x + 1] + map[y + 2][x]);
            }
        }

        for (int y = 0; y < N - 2; y++) {
            for (int x = 0; x < M - 1; x++) {
                answer = Math.max(answer, map[y][x] + map[y][x + 1] + map[y + 1][x + 1] + map[y + 2][x + 1]);
            }
        }

        for (int y = 0; y < N - 2; y++) {
            for (int x = 0; x < M - 1; x++) {
                answer = Math.max(answer, map[y][x] + map[y + 1][x] + map[y + 2][x] + map[y][x + 1]);
            }
        }

        for (int y = 0; y < N - 1; y++) {
            for (int x = 0; x < M - 2; x++) {
                answer = Math.max(answer, map[y][x] + map[y][x + 1] + map[y][x + 2] + map[y + 1][x + 2]);
            }
        }

        for (int y = 0; y < N - 1; y++) {
            for (int x = 0; x < M - 2; x++) {
                answer = Math.max(answer, map[y][x] + map[y][x + 1] + map[y][x + 2] + map[y + 1][x]);
            }
        }

        for (int y = 0; y < N - 1; y++) {
            for (int x = 0; x < M - 2; x++) {
                answer = Math.max(answer, map[y + 1][x] + map[y + 1][x + 1] + map[y + 1][x + 2] + map[y][x + 2]);
            }
        }

        for (int y = 0; y < N - 1; y++) {
            for (int x = 0; x < M - 2; x++) {
                answer = Math.max(answer, map[y][x] + map[y + 1][x] + map[y + 1][x + 1] + map[y + 1][x + 2]);
            }
        }
    }

    private static void checkShape4() {
        for (int y = 0; y < N - 2; y++) {
            for (int x = 0; x < M - 1; x++) {
                answer = Math.max(answer, map[y][x] + map[y + 1][x] + map[y + 1][x + 1] + map[y + 2][x + 1]);
            }
        }

        for (int y = 0; y < N - 2; y++) {
            for (int x = 0; x < M - 1; x++) {
                answer = Math.max(answer, map[y][x + 1] + map[y + 1][x + 1] + map[y + 1][x] + map[y + 2][x]);
            }
        }

        for (int y = 0; y < N - 1; y++) {
            for (int x = 0; x < M - 2; x++) {
                answer = Math.max(answer, map[y][x] + map[y][x + 1] + map[y + 1][x + 1] + map[y + 1][x + 2]);
            }
        }

        for (int y = 0; y < N - 1; y++) {
            for (int x = 0; x < M - 2; x++) {
                answer = Math.max(answer, map[y + 1][x] + map[y + 1][x + 1] + map[y][x + 1] + map[y][x + 2]);
            }
        }
    }

    private static void checkShape5() {
        for (int y = 0; y < N - 2; y++) {
            for (int x = 0; x < M - 1; x++) {
                answer = Math.max(answer, map[y][x] + map[y + 1][x] + map[y + 1][x + 1] + map[y + 2][x]);
            }
        }

        for (int y = 0; y < N - 2; y++) {
            for (int x = 0; x < M - 1; x++) {
                answer = Math.max(answer, map[y][x + 1] + map[y + 1][x + 1] + map[y + 1][x] + map[y + 2][x + 1]);
            }
        }

        for (int y = 0; y < N - 1; y++) {
            for (int x = 0; x < M - 2; x++) {
                answer = Math.max(answer, map[y + 1][x] + map[y + 1][x + 1] + map[y + 1][x + 2] + map[y][x + 1]);
            }
        }

        for (int y = 0; y < N - 1; y++) {
            for (int x = 0; x < M - 2; x++) {
                answer = Math.max(answer, map[y][x] + map[y][x + 1] + map[y][x + 2] + map[y + 1][x + 1]);
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void printAnswer() {
        System.out.println(answer);
    }
}
