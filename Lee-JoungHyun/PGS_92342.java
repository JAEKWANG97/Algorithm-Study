import java.util.*;

class Solution {
    int MaxValue = 1;
    LinkedList<int[]> list = new LinkedList<>();
    
    public int[] solution(int n, int[] info) {
        int[] answer = {};

        //System.out.println(Arrays.toString(peach));
        shoot(0, new int[11], n, info);
        if (list.size() == 0) {
            int[] ans = {-1};
            return ans;
        }

//        for (int[] ans : list) {
//            System.out.println(Arrays.toString(ans));
//        }
        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                for (int i = 10; i > -1; i--) {
                    if (o1[i] != o2[i])
                        return o2[i] - o1[i];
                }
                return 0;
            }
        });
        return list.get(0);
    }

    public void shoot(int cnt, int[] arrow, int laftCnt, int[] info) {
        if (cnt == 11 || laftCnt == 0) {
            if (laftCnt != 0)
                arrow[10] += laftCnt;
            int point = 0;
            for (int i = 0; i < 11; i++) {
                if (info[i] < arrow[i]) point += 10 - i;
                else if (info[i] != 0) point -= 10 - i;
            }

            

            if (point > MaxValue) {
                
                MaxValue = point;
                list.clear();
                list.add(arrow);
            }
            else if (point == MaxValue) {
                System.out.println(Arrays.toString(arrow) + ": " + point);
                list.add(arrow);
            }
            return;
        }
        //cnt 점 쏘기!!
        if (info[cnt] < laftCnt) {
            int[] nxtArrow = Arrays.copyOf(arrow, 11);
            nxtArrow[cnt] = info[cnt] + 1;
            shoot(cnt+1, nxtArrow, laftCnt - (info[cnt] + 1), info);
        }

        shoot(cnt+1, arrow, laftCnt, info);

    }

}