import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SEA_4014 { //swea 4014 활주로 건설 - 160분
	
	private static int n, x;
    private static int[][] map1, map2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            map1 = new int[n][n];
            map2 = new int[n][n];

            for (int i = 0; i < n; i++) { // 입력
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    map1[i][j] = num;
                    map2[j][i] = num;
                }
            }

            int answer = solution();
            System.out.println("#" + t + " " + answer);
        }
    }

    private static int solution() {
        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer += isOk(map1[i]);
            answer += isOk(map2[i]);
        }
        return answer;
    }

    private static int isOk(int[] array) { // 활주로 건설할 수 있으면 1, 없으면 0 리턴
        boolean[] check = new boolean[n];

        for (int i = 0; i < n - 1; i++) {
            int prev = array[i];
            int next = array[i + 1];

            if (Math.abs(prev - next) > 1) return 0;  // 2 이상 차이나면 return 0
            if (check[i + 1] || prev == next) continue; // 평지거나 이미 지형이 있다면 패스

            if (prev > next) { // 내려갈 때 : 3 3 2 2 1 1
                for (int j = i + 1; j <= i + x; j++) { // 지형 설치
                
                    // n을 넘어가거나, 평지가 아니거나, 이미 지형이 있으면 return 0
                    if (j == n || array[j] != next || check[j]) return 0;
                    
                    // 지형의 마지막은 값 그대로 가져가기 위한 if문
                    if (j != i + x) array[j] = next + 1;
                    
                    check[j] = true; // 지형 설치 체크
                }
            } else { // 올라갈 때 : 1 1 2 2 3 3
                for (int j = i; j > i - x; j--) {
                    if (j < 0 || array[j] != prev || check[j]) return 0;
                    if (j != i - x + 1) array[j] = prev + 1;
                    check[j] = true;
                }
            }
        }
        return 1;
    }

//	static int n, x, map[][], result;
//	static ArrayList<Integer> makeRoadAtRow;
//	static ArrayList<Integer> makeRoadAtCol;
//
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st;
//
//		int T = Integer.parseInt(br.readLine());
//		for (int t = 1; t < T+1; t++) {
//			st = new StringTokenizer(br.readLine());
//			n = Integer.parseInt(st.nextToken());
//			x = Integer.parseInt(st.nextToken());
//
//			map = new int[n][n];
//			for (int i = 0; i < n; i++) {
//				st = new StringTokenizer(br.readLine());
//				for (int j = 0; j < n; j++) {
//					map[i][j] = Integer.parseInt(st.nextToken());
//				}
//			}
//
//			result = 0;
//
//			for (int i = 0; i < n; i++) {
//
//				if(check(true, i)) { //row 검증
//					result++;
//				} else {
//					if (canConstruct(i,false)) { //i행 활주로 가능한지 검사
//						result++;
//					}
//				}
//
//
//				if (check(false, i) ) { //col 검증
//					result ++;
//				} else {
//					if (canConstruct(i,false)) { //i열 활주로 가능한지 검사
//						result++;
//					}
//				}
//			}
//
//			System.out.println(result);
//
//
//		}//for T
//	}//main
//
//	//행,열에 활주로 건설이 가능한지 확인
//	private static boolean canConstruct(int idx, boolean isRow) {
//		boolean[] visited = new boolean[n];
//		int cnt = 0;
//		int before = -1;
//		
//		if(isRow) {
//			before = map[idx][0];
//			
//			for(int c = 0 ; c < n ; ++c) {
//				int cur = map[idx][c];
//				
//				if(cur == before) {
//					cnt++;
//				} else {
//					if(cur == before + 1) {
//						// 지대가 높아지는 경우 
//						if(cnt >= x) {
//							// 경사로 설치
//							for(int i = c - x ; i < c ; ++i) {
//								if(visited[i]) return false;
//								visited[i] = true;
//							}
//							cnt = 1;
//						} else {
//							// 길이가 부족하여 경사로 설치 불가 
//							return false;
//						}
//					} else if(cur == before - 1) {
//						// 지대가 낮아지는 경우
//						if(c + x - 1 < n) {
//							// 경사로 설치
//							for(int i = c ; i < c + x ; ++i) {
//								if(visited[i] || map[idx][i] != cur) return false;
//								visited[i] = true;
//							}
//							cnt = 0;
//							c = c + x - 1;
//						} else {
//							// 길이가 부족하여 경사로 설치 불가
//							return false;
//						}
//					} else {
//						return false;
//					}
//					before = cur;
//				}
//			}
//		} else {
//			before = map[0][idx];
//			
//			for(int r = 0 ; r < n ; ++r) {
//				int cur = map[r][idx];
//				
//				if(cur == before) {
//					cnt++;
//				} else {
//					if(cur == before + 1) {
//						// 지대가 높아지는 경우 
//						if(cnt >= x) {
//							// 경사로 설치
//							for(int i = r - x ; i < r ; ++i) {
//								if(visited[i]) return false;
//								visited[i] = true;
//							}
//							cnt = 1;
//						} else {
//							// 길이가 부족하여 경사로 설치 불가 
//							return false;
//						}
//					} else if(cur == before - 1) {
//						// 지대가 낮아지는 경우
//						if(r + x - 1 < n) {
//							// 경사로 설치
//							for(int i = r ; i < r + x ; ++i) {
//								if(visited[i] || map[i][idx] != cur) return false;
//								visited[i] = true;
//							}
//							r = r + x - 1;
//							cnt = 0;
//						} else {
//							// 길이가 부족하여 경사로 설치 불가
//							return false;
//						}
//					} else {
//						return false;
//					}
//					before = cur;
//				}
//			}
//		}
//		
//		return true;
//	}
//
//	//행,열의 각 한 줄이 모두 동일한지 검사
//	private static boolean check(boolean isRow, int rowOrcol) {
//		int start = -1;
//
//		if(isRow) {
//			start = map[rowOrcol][0];
//			for(int c = 1 ; c < n ; ++c) {
//				if(map[rowOrcol][c] != start) return false;
//			}
//		} else {
//			start = map[0][rowOrcol];
//			for(int r = 1 ; r < n ; ++r) {
//				if(map[r][rowOrcol] != start) return false;
//			}
//		}
//
//		return true;
//	}//colCheck
}//class
