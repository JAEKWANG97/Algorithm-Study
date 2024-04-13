import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_21608 {
    static int N;
    static int[][] classroom;
    static int[][] preferences;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        classroom = new int[N][N];
        preferences = new int[N * N + 1][4];

        for (int i = 0; i < N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 4; j++) {
                preferences[student][j] = Integer.parseInt(st.nextToken());
            }
            placeStudent(student);
        }

        System.out.println(calculateSatisfaction());
    }

    private static void placeStudent(int student) {
        int[][] candidate = new int[N * N][3]; // 0: 좋아하는 학생 수, 1: 비어있는 칸 수, 2: index
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (classroom[i][j] == 0) {
                    int favorite = 0, empty = 0;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                            if (classroom[nx][ny] == 0) {
                                empty++;
                            } else {
                                for (int pref : preferences[student]) {
                                    if (classroom[nx][ny] == pref) {
                                        favorite++;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    candidate[cnt][0] = favorite;
                    candidate[cnt][1] = empty;
                    candidate[cnt][2] = i * N + j;
                    cnt++;
                }
            }
        }

        int maxFavorite = -1, maxEmpty = -1, chosenSpot = -1;
        for (int i = 0; i < cnt; i++) {
            if (candidate[i][0] > maxFavorite || (candidate[i][0] == maxFavorite && candidate[i][1] > maxEmpty)) {
                maxFavorite = candidate[i][0];
                maxEmpty = candidate[i][1];
                chosenSpot = candidate[i][2];
            }
        }
        classroom[chosenSpot / N][chosenSpot % N] = student;
    }

    private static int calculateSatisfaction() {
        int satisfaction = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int count = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                        for (int pref : preferences[classroom[i][j]]) {
                            if (classroom[nx][ny] == pref) {
                                count++;
                                break;
                            }
                        }
                    }
                }
                satisfaction += Math.pow(10, count - 1);
            }
        }

        return satisfaction;
    }
}
