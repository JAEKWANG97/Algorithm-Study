import java.util.*;
class Solution {
    static String[] id_list;
    public int[] solution(String[] id_lists, String[] report, int k) {
        id_list = id_lists;
        int[] answer = new int[id_lists.length];
        List<List<Integer>> arr =new ArrayList<>();
        for(int i=0;i<id_lists.length;i++)arr.add(new ArrayList<>());
        for(String r:report){
            String s1 = r.split(" ")[0];
            String s2 = r.split(" ")[1];
            int idx1 = idx(s1);
            int idx2 = idx(s2);
            if(!arr.get(idx2).contains(idx1))arr.get(idx2).add(idx1);
        }
        for(int idx=0;idx<arr.size();idx++){
            if(arr.get(idx).size()>=k){
                for(int tmp:arr.get(idx))answer[tmp]++;
            }
        }
        return answer;
    }
    private static int idx(String str){
        for(int idx=0;idx<id_list.length;idx++){
            if(id_list[idx].equals(str)){
                return idx;
            }
        }
        return -1;
    }
}
