import java.util.*;
class Solution {
    static int N, KF,answer;
    static char data[][];
    static String[] KFS = {"A", "C", "F", "J", "M", "N", "R", "T"};
    public int solution(int n, String[] Data) {
        N=n;
        KF = 8;
        answer=0;
        data = new char[n][4];
        for(int idx=0;idx<n;idx++){
            String d = Data[idx];
            char c1 = d.charAt(0);
            char c2 = d.charAt(2);
            char calc = d.charAt(3);
            char diff = d.charAt(4);
            data[idx][0] = c1;
            data[idx][1]=c2;
            data[idx][2] = calc;
            data[idx][3]=diff;
        }
        String arr = "";
        boolean[] visited = new boolean[KF];
        perm(0,arr,visited);
        return answer;
    }
    public void perm(int cnt, String arr, boolean visited[]){
        if(cnt==KF){
            boolean flag = true;
            for(char d[]:data){
                int num = d[3]-'0';
                int diff = Math.abs(arr.indexOf(d[0])-arr.indexOf(d[1]))-1;
                if((d[2]=='='&&diff!=num)||(d[2]=='>'&&diff<=num)||(d[2]=='<'&&diff>=num)){
                    flag = false;
                    break;
                }
            }
            if(flag)answer++;
            return;
        }
        for(int i=0;i<KF;i++){
            if(visited[i])continue;
            visited[i] = true;
            perm(cnt+1,arr+KFS[i],visited);
            visited[i] = false;
        }
    }
}
