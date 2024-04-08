import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import javax.print.attribute.standard.PrintQuality;

/**
 * BOJ_9205
 */
public class Main {
  static class Location {
    int x;
    int y;
    int dist;

    public Location(int x, int y) {
      this.x = x;
      this.y = y;

    }

    @Override
    public boolean equals(Object o) {
      if (this == o)
        return true;
      if (o == null || getClass() != o.getClass())
        return false;
      Location location = (Location) o;
      return x == location.x && y == location.y;
    }

  }

  static StringBuilder sb = new StringBuilder();
  static int T;

  static List<Location> convenienceStoreList;
  static int N;
  static Location start;
  static Location target;
  static boolean flag;
  static FastInput fastInput = new FastInput();

  public static void main(String[] args) throws NumberFormatException, IOException {
    fastInput.initFI();
    T = fastInput.nextInt();

    for (int i = 0; i < T; i++) {
      init();
      dfs(start, 20, new boolean[N + 1]);
      if (!flag) {
        sb.append("sad").append("\n");
      } else {
        sb.append("happy").append("\n");
      }
    }
    System.out.println(sb.toString());
  }

  private static void init() throws NumberFormatException, IOException {
    N = fastInput.nextInt();

    int startX = fastInput.nextInt();
    int startY = fastInput.nextInt();
    convenienceStoreList = new ArrayList<>();
    flag = false;
    start = new Location(startX, startY);

    for (int i = 0; i < N; i++) {

      int x = fastInput.nextInt();
      int y = fastInput.nextInt();

      convenienceStoreList.add(new Location(x, y));
    }

    int targetX = fastInput.nextInt();
    int targetY = fastInput.nextInt();

    target = new Location(targetX, targetY);
    convenienceStoreList.add(target);
  }

  private static int calDist(Location from, Location to) {
    return Math.abs(from.x - to.x) + Math.abs(from.y - to.y);
  }

  private static void dfs(Location location, int beer, boolean[] visited) {
    if (flag) {
      return;
    }

    if (location.equals(target) && !flag) {
      flag = true;
      return;
    }

    int maxDist = beer * 50;

    for (int i = 0; i < convenienceStoreList.size(); i++) {
      if (!visited[i]) {
        if (calDist(location, convenienceStoreList.get(i)) <= maxDist) {
          visited[i] = true;
          dfs(convenienceStoreList.get(i), 20, visited);
        }
      }
    }
  }
}

class FastInput {
  private static final int DEFAULT_BUFFER_SIZE = 1 << 16;
  private static DataInputStream inputStream;
  private static byte[] buffer;
  private static int curIdx, maxIdx;

  protected static void initFI() {
    inputStream = new DataInputStream(System.in);
    buffer = new byte[DEFAULT_BUFFER_SIZE];
    curIdx = maxIdx = 0;
  }

  protected static int nextInt() throws IOException {
    int ret = 0;
    byte c = read();
    while (c <= ' ')
      c = read(); // 공백 무시
    boolean neg = false;
    if (c == '-') { // 음수 체크
      neg = true;
      c = read(); // 부호 다음 숫자로 이동
    }
    do {
      ret = ret * 10 + c - '0';
    } while ((c = read()) >= '0' && c <= '9');
    return neg ? -ret : ret; // 음수면 결과를 음수로 반환
  }

  private static byte read() throws IOException {
    if (curIdx == maxIdx) {
      maxIdx = inputStream.read(buffer, curIdx = 0, DEFAULT_BUFFER_SIZE);
      if (maxIdx == -1)
        buffer[0] = -1;
    }
    return buffer[curIdx++];
  }
}
