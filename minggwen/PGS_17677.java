import java.util.*;
class PGS_17677 {
    public int solution(String str1, String str2) {
        double answer=0;
        List<String> s1 = new ArrayList<>();
        List<String> s2 = new ArrayList<>();
        List<String> sum = new ArrayList<>();
        for(int i=0;i<=str1.length()-2;i++){
            if(isChar(str1.charAt(i))&&isChar(str1.charAt(i+1))){
                String tmp = str1.substring(i,i+2).toUpperCase();
                s1.add(tmp);
                sum.add(tmp);
            }
        }
        for(int i=0;i<=str2.length()-2;i++){
            if(isChar(str2.charAt(i))&&isChar(str2.charAt(i+1))){
                String tmp = str2.substring(i,i+2).toUpperCase();
                s2.add(tmp);
                sum.add(tmp);
            }
        }
        if(s1.size()==0&&s2.size()==0){
            return 65536;
        }
        int sumNum = sum.size();
        int crossNum = 0;
        boolean visited[] = new boolean[s2.size()];
        for(int i=0; i<s1.size();i++){
            for(int j=0; j<s2.size();j++){
                if(s1.get(i).equals(s2.get(j))&&!visited[j]){
                    visited[j]=true;
                    crossNum++;
                    break;
                }
            }
        }
        System.out.println(s1.toString());
        answer = ((double)crossNum*1.0)/((double)(sumNum-crossNum)*1.0);
        return (int)(answer*65536.0);
    }
    private boolean isChar(char c){
        return Character.isAlphabetic(c);
    }
}
