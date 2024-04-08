import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17144 { //백준 17144 미세먼지 안녕! - 120분
    private static class Node {
        int x, y;

        public Node(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }
    }// Node

    static int r, c, t, map[][], upStandard, downStandard;
    static Node filter[];
    static ArrayDeque<Node> dust;

    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        init();
        while (t > 0) {
            // 미세먼지 확산
            spreaded();
            // 공기청정기 작동
            airfilter();
            // 시간 지남
            t--;
            
            dust = new ArrayDeque<>();
            for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if (map[i][j]>0) {
						dust.offer(new Node(i,j));
					}
				}
			}
        }
        
        
        
        int cnt = 0;
        for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j]!=-1) cnt += map[i][j];
			}
		}
        
        System.out.println(cnt);
    }// main

    private static void spreaded() {
        int[][] newMap = copyMap();
        while (!dust.isEmpty()) {
            Node node = dust.poll();
            int x = node.x;
            int y = node.y;
            
            ArrayDeque<Node> spreadNominate = new ArrayDeque<>();
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                
                if (0 <= nx && nx < r && 0 <= ny && ny < c && map[nx][ny] != -1) {
                    spreadNominate.offer(new Node(nx, ny));
                }
            }
            
            int spreadAmount = (int) map[x][y] / 5;
            int remainAmount = map[x][y] - spreadAmount*spreadNominate.size();
            
            newMap[x][y] += remainAmount;
            while(!spreadNominate.isEmpty()) {
                Node nextNode = spreadNominate.poll();
                newMap[nextNode.x][nextNode.y] += spreadAmount;
            }
        }
        
        //공기청정기 넣기
        for (Node nd : filter) {
            newMap[nd.x][nd.y] = -1; 
        }
        
        map = newMap;
        
    }// spread

    private static void airfilter() {
        // 윗구간 기준 행
        upStandard = filter[0].x;
        // 아랫구간 기준 행
        downStandard = filter[1].x;

        // 윗 구간
        // 밀리는 칸은 삭제해도 됨
        for (int i = upStandard - 1; i > 0; i--) {
            map[i][0] = map[i - 1][0];
        }
        for (int i = 0; i < c - 1; i++) {
            map[0][i] = map[0][i + 1];
        }
        for (int i = 0; i < upStandard; i++) {
            map[i][c - 1] = map[i + 1][c - 1];
        }
        for (int i = c - 1; i > 1; i--) {
            map[upStandard][i] = map[upStandard][i - 1];
        }
        map[upStandard][1] = 0;

        // 아랫 구간
        for (int i = downStandard + 1; i < r - 1; i++) {
            map[i][0] = map[i + 1][0];
        }
        for (int i = 0; i < c - 1; i++) {
            map[r - 1][i] = map[r - 1][i + 1];
        }
        for (int i = r - 1; i > downStandard; i--) {
            map[i][c - 1] = map[i - 1][c - 1];
        }
        for (int i = c - 1; i > 1; i--) {
            map[downStandard][i] = map[downStandard][i - 1];
        }
        map[downStandard][1] = 0;
    }

    
    private static int[][] copyMap() {
        int[][] newMap = new int[r][c];
        return newMap;
    }//copyMap

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        map = new int[r][c];
        filter = new Node[2];

        dust = new ArrayDeque<>();

        int fIdx = 0;
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0) {
                    dust.offer(new Node(i, j));
                } else if (map[i][j] == -1) {
                    filter[fIdx++] = new Node(i, j);
                }
            }
        }
    }// init
}// class
