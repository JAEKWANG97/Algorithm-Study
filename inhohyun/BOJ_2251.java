import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_2251 {
    static Set<String> visited = new HashSet<>();
    static List<Integer> list = new ArrayList<>();
    static int limitA, limitB, limitC;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        limitA = Integer.parseInt(st.nextToken());
        limitB = Integer.parseInt(st.nextToken());
        limitC = Integer.parseInt(st.nextToken());
        solution(0,0,limitC);

        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)+" ");
        }
        System.out.println(sb);
    }

    static void solution(int A, int B, int C) {
        String state = A + " " + B + " " + C;
        if (visited.contains(state)) { // 이미 해본 조합이면 건뛰
            return;
        }

        visited.add(state);

        if(A == 0) {

            list.add(C);

//			System.out.println(C);
        }
        if(A+B+C != limitC) {
            System.out.println("물이 소실되었음");
            System.exit(0); // 오류가 생기면 종류
        }

        //물통이 비어있지 않으면 물을 부을 수 있음
        if(A != 0) { // A의 물을 옮기기
            //B 물통에 옮기기
            if(B != limitB) {
                //담았을 때 초과하는 경우
                if(B+A > limitB) {
                    solution(B+A - limitB, limitB, C);
                }
                //초과하지 않는 겨웅
                else {
                    solution(0, B+A, C);
                }
            }
            //C 물통에 옮기기
            if(C != limitC) {
                //담았을 때 초과하는 경우
                if(A+C > limitC) {
                    solution(B+C - limitC, B, limitC);
                }
                //초과하지 않는 겨웅
                else {
                    solution(0, B, C+A);
                }
            }
        }

        if(B != 0) { // B의 물을 옮기기
            //A 물통에 옮기기
            if(A != limitA) {
                //담았을 때 초과하는 경우
                if(B+A > limitA) {
                    solution(limitA, B+A - limitA, C);
                }
                //초과하지 않는 겨웅
                else {
                    solution(B+A, 0, C);
                }
            }

            //C 물통에 옮기기
            if(C != limitC) {
                //담았을 때 초과하는 경우
                if(B+C > limitC) {
                    solution(B+C - limitC, B, limitC);
                }
                //초과하지 않는 겨웅
                else {
                    solution(0, B, C+A);
                }
            }
        }


        if(C != 0) { // C의 물을 옮기기
            //A 물통에 옮기기
            if(A != limitA) {
                //담았을 때 초과하는 경우
                if(C+A > limitA) {
                    solution(limitA, B, A+C - limitA);

                }
                //초과하지 않는 겨웅
                else {
                    solution(C+A, B, 0);
                }
            }

            //B 물통에 옮기기
            if(B != limitB) {
                //담았을 때 초과하는 경우
                if(B+C > limitB) {
                    solution(A, limitB, B+C - limitB);
                }
                //초과하지 않는 겨웅
                else {
                    solution(A, B+C, 0);
                }
            }
        }





    }
}
