import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int N, M;
  static int[][] map;
  static boolean[][] visited;
  static int[] dx = { -1, 0, 1, 0 };
  static int[] dy = { 0, 1, 0, -1 };
  static int maxSum = Integer.MIN_VALUE;
  static int maxValue = 0;

  public static void main(String[] args) throws IOException {
    input();
    solve();
    System.out.println(maxSum);
  }

  private static void input() throws IOException {
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    map = new int[N][M];
    visited = new boolean[N][M]; // 전역 visited 배열 초기화
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        maxValue = Math.max(maxValue, map[i][j]);
      }
    }
  }

  private static void solve() {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        visited[i][j] = true;
        dfs(i, j, 1, map[i][j]);
        visited[i][j] = false;
        handleSpecialShape(i, j);
      }
    }
  }

  private static void dfs(int x, int y, int depth, int sum) {
    if (depth == 4) {
      maxSum = Math.max(maxSum, sum);
      return;
    }

    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];
      if (nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny]) {
        visited[nx][ny] = true;
        dfs(nx, ny, depth + 1, sum + map[nx][ny]);
        visited[nx][ny] = false;
      }
    }
  }

  private static void handleSpecialShape(int x, int y) {
    int sum = map[x][y];
    int min = Integer.MAX_VALUE;
    int count = 0; // 주변 칸이 유효한지 카운트하는 변수 추가

    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];

      if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
        sum += map[nx][ny];
        min = Math.min(map[nx][ny], min);
        count++; // 유효한 칸이면 카운트 증가
      }
    }

    // 주변 4칸 중 최소 3칸이 유효해야 'ㅗ' 모양 고려 가능
    if (count == 3) {
      maxSum = Math.max(maxSum, sum);
    } else if (count == 4) {
      maxSum = Math.max(maxSum, sum - min);
    }
  }

}
