import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SEA_4041 {
    static int n, x, map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            map = new int[n][n];
            // map 입력
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = searchX() + searchY();

            System.out.println("#"+tc+" "+answer);

        }
    }

    // 가로 탐색 -> 왼쪽부터 탐색 : tc : 2
    static int searchX() {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int len = 0; // 쓸 수 있는 거리
            int flag = 1; // 활주로를 놓을 수 있는지 판별
            boolean condition = true;
            for (int j = 0; j < n - 1 && condition; j++) {
                len++; // 가용할 수 있는 길이
                // 1이상 차이나면 못 만듦
                if (Math.abs(map[i][j] - map[i][j + 1]) > 1) {
                    flag = 0;
                    break;
                }
                // 같은 높이면 판별할 필요 없음
                if (map[i][j] == map[i][j + 1])
                    continue;

                // 높은게 나왔을 때
                if (map[i][j] < map[i][j+1]) {
                    // 사용할 수 있는 거리가 x보다 짧다면
                    if (x > len) {
                        flag = 0;
                        break;
                    }
                    // 거리가 충분하면 경사로를 설치하고 넘어가기
                    else {
                        len = 0; // 경사로를 놓았으니 가용할 수 있는 거리 초기화

                    }
                }
                // 낮은게 나왔을 때
                else if (map[i][j] > map[i][j + 1]) {
                    len = 0; // 이전까지 가용할 수 있는 거리는 의미 없음
                    for (int tmp = j + 1; tmp < j + 1 + x; tmp++) { // 앞에 경사로를 넣을 거리가 있는지 판별
//						System.out.println(tmp);
                        if (tmp >= n || map[i][tmp] != map[i][j+1]) { // 경사로를 놓기 전에 끝이 왔다면, x까지의 거리를 확보하지 못하고 높이가 달라지면
                            flag = 0;
                            condition = false; // 어차피 안되니 j의 for문 종료
                            break;
                        }

                    }
                    //무작정 이동하면 안됨
                    j += x -1;
                    len = -1;
                }
            }
            if (flag == 1) {
//				System.out.println("x sucess " + i);
                cnt++;
            }


        }
//		System.out.println("x : "+ cnt);
        return cnt;
    }
    //세로 판별하기
    static int searchY() {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int len = 0; // 쓸 수 있는 거리
            int flag = 1; // 활주로를 놓을 수 있는지 판별
            boolean condition = true;
            for (int j = 0; j < n - 1 && condition; j++) {
                len++; // 가용할 수 있는 길이
                // 1이상 차이나면 못 만듦
                if (Math.abs(map[j][i] - map[j+1][i]) > 1) {
                    flag = 0;
                    condition = false; // 어차피 안되니 j의 for문 종료
                    break;
                }
                // 같은 높이면 판별할 필요 없음
                if (map[j][i] == map[j+1][i])
                    continue;

                // 높은게 나왔을 때
                if (map[j][i] < map[j+1][i]) {
                    // 사용할 수 있는 거리가 x보다 짧다면
                    if (x > len) {
                        flag = 0;
                        condition = false; // 어차피 안되니 j의 for문 종료
                        break;
                    }
                    // 거리가 충분하면 경사로를 설치하고 넘어가기
                    else {
                        len = 0; // 경사로를 놓았으니 가용할 수 있는 거리 초기화

                    }
                }
                // 낮은게 나왔을 때
                else if (map[j][i] > map[j+1][i]) {
                    len = 0; // 이전까지 가용할 수 있는 거리는 의미 없음
                    for (int tmp = j + 1; tmp < j + 1 + x; tmp++) { // 앞에 경사로를 넣을 거리가 있는지 판별
                        if (tmp >= n || map[tmp][i] != map[j+1][i]) { // 경사로를 놓기 전에 끝이 왔다면, x까지의 거리를 확보하지 못하고 높이가 달라지면
                            flag = 0;
                            condition = false; // 어차피 안되니 j의 for문 종료
                            break;
                        }
                    }
                    //경사로를 무사히 놓음, 경사로 이후로 이동
                    j += x-1;
                    len = -1;
                }
            }
            if (flag == 1)
            {
                cnt++;
//				System.out.println("y sucess " + i);
            }


        }
//		System.out.println("y : "+ cnt);
        return cnt;
    }
}
