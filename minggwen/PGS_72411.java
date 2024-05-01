import java.util.*;
class Solution {
    static String[] orders;
    static int[] course;
    static List<List<String>> indicates;
    static int[] indiMax;
    static List<Character> alpas;
    static int MAX;
    public String[] solution(String[] Orders, int[] Course) {
        orders = Orders;
        course = Course;
        indicates = new ArrayList<>();
        for(int c:course)indicates.add(new ArrayList<>());
        indiMax = new int[course.length];
        MAX = 0;
        for(int c:course){
            MAX = Math.max(c,MAX);
        }
        alpas = new ArrayList<>();
        for(String order:orders){
            for(int idx=0;idx<order.length();idx++){
                char c= order.charAt(idx);
                if(!alpas.contains(c))alpas.add(c);
            }
        }
        Collections.sort(alpas);
        boolean[] visited = new boolean[alpas.size()];
        comb(0,0,visited);
        System.out.println(indicates.toString());
        Queue<String> que = new PriorityQueue<>();
        for(int i=0;i<indicates.size();i++){
            for(String indi:indicates.get(i)){
                que.add(indi);
            }
        }
        String[] answer = new String[que.size()];
        int size = que.size();
        for(int i=0;i<size;i++){
            answer[i]=que.poll();
        }
        return answer;
    }
    public void comb(int idx, int cnt, boolean visited[]){
        for(int i=0;i<course.length;i++){
            int c=course[i];
            if(c==cnt){
                String str = "";
                for(int k=0;k<visited.length;k++){
                    if(visited[k])str+=alpas.get(k);
                }
                int tmpNum = isCourse(str);
                if(tmpNum<=1)continue;
                if(tmpNum>indiMax[i]){
                    indicates.get(i).clear();
                    indicates.get(i).add(str);
                    indiMax[i] = tmpNum;
                }else if(tmpNum==indiMax[i]){
                    indicates.get(i).add(str);
                }
            }
        }
        if(cnt==MAX) return;
        for(int i=idx;i<alpas.size();i++){
            if(visited[i])continue;
            visited[i]=true;
            comb(i,cnt+1,visited);
            visited[i]=false;
        }
    }
    private int isCourse(String str){
        int cnt = 0;
        for(String order:orders){
            boolean flag = true;
            for(int s=0;s<str.length();s++){
                if(order.indexOf(str.charAt(s))<0){
                    flag = false;
                    break;
                }
            }
            if(flag)cnt++;
        }
        return cnt;
    }
    
}
