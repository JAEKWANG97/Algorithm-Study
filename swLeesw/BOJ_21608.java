import java.util.*;
import java.io.*;

public class Main {
    
    static int n, arr[][], student[][], sol;
    static int visited[][]; //우선순위 확인을 위한 배열
    static int dx[] = {0, 0, 1, -1};
    static int dy[] = {1, -1, 0, 0};
    
    static boolean isRange(int y, int x) {
        if (y >= 0 && x >= 0 && y < n && x < n) return true;
        else return false;
    }
    
    static void pick(int idx) {
        visited = new int[n][n];
        
        int sNum = student[idx][0]; //학생 번호
        int maxLove = -1; //좋아하는 학생 max
        int maxLoveY = -1; //최대값 좌표
        int maxLoveX = -1;
        int maxLoveCnt = 0; //같은게 몇개인지?
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 0) { //빈 칸 조사
                    int loveSum = 0; //좋아하는 사람 세기
                    for (int k = 0; k < 4; k++) {
                        int ny = i + dy[k];
                        int nx = j + dx[k];
                        if (isRange(ny, nx)) { //범위 안이면
                            boolean check = false;
                            for (int l = 1; l <= 4; l++) { //좋아하는 사람인지 확인
                                if (student[idx][l] == arr[ny][nx]) {
                                    check = true;
                                    break;
                                }
                            }
                            if (check) loveSum++; //있으면 사랑 더하기
                        }
                    }
                    visited[i][j] = loveSum;
                    if (maxLove < loveSum) {
                    	maxLove = loveSum;
                    	maxLoveY = i;
                    	maxLoveX = j;
                    	maxLoveCnt = 1;
                    } else if (maxLove == loveSum){
                    	maxLoveCnt++;
                    }
                }
            }
        }
        
        //1번 우선순위
        if (maxLoveCnt == 1) {
        	arr[maxLoveY][maxLoveX] = sNum;
        	return;
        }
        //2번 우선순위&& 3번 우선순위
        int maxSpace = -1;
        int maxSpaceY = -1;
        int maxSpaceX = -1;
        for (int i = 0; i < n; i++) {
        	for (int j = 0; j < n; j++) {
        		if (visited[i][j] == maxLove && arr[i][j] == 0) {
        			int spaceSum = 0;
        			for (int k = 0; k < 4; k++) {
        				int ny = i + dy[k];
        				int nx = j + dx[k];
        				if (isRange(ny, nx)) {
        					if (arr[ny][nx] == 0) {
        						spaceSum++;
        					}
        				}
        			}
        			
        			if (spaceSum > maxSpace) {
        				maxSpace = spaceSum;
        				maxSpaceY = i;
        				maxSpaceX = j;
        			}
        		}
        	}
        }

        arr[maxSpaceY][maxSpaceX] = sNum;
        return;
        
        
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        n = Integer.parseInt(br.readLine());
        
        arr = new int[n][n];
        student = new int[n * n][5];
        for (int i = 0; i < n * n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                student[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for (int i = 0; i < n * n; i++) {
            pick(i);
        }
        
        for (int i = 0; i < n; i++) {
        	for (int j = 0; j < n; j++) {
        		int curNum = arr[i][j];
        		
        		for (int k = 0; k < n * n; k++) {
        			if (student[k][0] == curNum) {
        				int flag = 0; //love 갯수
        				
        				for (int m = 0; m < 4; m++) { //인점 조사
        					int ny = i + dy[m];
        					int nx = j + dx[m];
        					if (isRange(ny, nx)) { //조항하는 학생 있으면 flag++
        						for (int l = 1; l <= 4; l++) {
        							if (student[k][l] == arr[ny][nx]) {
        								flag++;
        								break;
        							}
        						}
        					}
        				}
        				
        				if (flag == 1) {
        					sol += 1;
        				} else if (flag == 2) {
        					sol += 10;
        				} else if (flag == 3) {
        					sol += 100;
        				} else if (flag == 4) {
        					sol += 1000;
        				}
        				break;
        			}
        		}
        	}
        }
        System.out.println(sol);
    }
    
}
