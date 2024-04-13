import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
 
        int T = Integer.parseInt(br.readLine());
 
        for (int test_case = 1; test_case <= T; test_case++) {
            int N, X;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            int[][] board = new int[N][N];
 
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
 
            //연속된 값을 유지하며 검사
            //겹쳐지게 설치 안됨!
            int rowCnt = checkRow(N, X, board);
            int colCnt = checkCol(N, X, board);
            System.out.println("#" + test_case + " " + (rowCnt + colCnt));
        }
    }
 
    private static int checkRow(int N, int X, int[][] board) {
        int result = 0;
 
        for (int i = 0; i < N; i++) {
            boolean isValid = true;
            int[] visited = new int[N];
            Arrays.fill(visited, 1);
 
            for (int j = 1; j < N; j++) {
                int prev = board[i][j - 1];
                int now = board[i][j];
 
                if (now == prev) {
                    visited[j] = visited[j - 1] + 1;
                    continue;
                }
 
                //자신보다 높은 친구 만나면 자신의 연속된 값과 X 비교하여 활주로 결정
                if (prev < now) {
                    if (prev + 1 != now || visited[j - 1] < X) {
                        isValid = false;
                        break;
                    }
                } else {
                    if (prev - 1 != now) {
                        isValid = false;
                        break;
                    }
 
                    //자신보다 낮은 친구 만나면 자신의 연속된 값은 잊고, 낮은 놈의 연속된 값을 보고 결정
                    int cnt = 1;
                    while (j + 1 < N && board[i][j] == board[i][j + 1]) {
                        visited[j + 1] = visited[j] + 1;
                        cnt++;
                        j++;
                        if (visited[j] == X) {
                            visited[j] = 0;
                            break;
                        }
                    }
 
                    if (cnt < X) {
                        isValid = false;
                        break;
                    }
                }
            }
 
            if (isValid) {
                result++;
            }
        }
 
        return result;
    }
 
    private static int checkCol(int N, int X, int[][] board) {
        int result = 0;
 
        for (int j = 0; j < N; j++) {
            boolean isValid = true;
            int[] visited = new int[N];
            Arrays.fill(visited, 1);
 
            for (int i = 1; i < N; i++) {
                int prev = board[i - 1][j];
                int now = board[i][j];
 
                if (now == prev) {
                    visited[i] = visited[i - 1] + 1;
                    continue;
                }
 
                //자신보다 높은 친구 만나면 자신의 연속된 값과 X 비교하여 활주로 결정
                if (prev < now) {
                    if (prev + 1 != now || visited[i - 1] < X) {
                        isValid = false;
                        break;
                    }
                } else {
                    if (prev - 1 != now) {
                        isValid = false;
                        break;
                    }
 
                    //자신보다 낮은 친구 만나면 자신의 연속된 값은 잊고, 낮은 놈의 연속된 값을 보고 결정
                    int cnt = 1;
                    while (i + 1 < N && board[i][j] == board[i + 1][j]) {
                        visited[i + 1] = visited[i] + 1;
                        cnt++;
                        i++;
                        if (visited[i] == X) {
                            visited[i] = 0;
                            break;
                        }
                    }
 
                    if (cnt < X) {
                        isValid = false;
                        break;
                    }
                }
            }
 
            if (isValid) {
                result++;
            }
        }
 
        return result;
    }
}
