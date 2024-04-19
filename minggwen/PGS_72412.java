import java.util.*;
class Solution {
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        int[] scores = new int[info.length];
        HashSet<Integer> java = new HashSet<>();
        HashSet<Integer> python  = new HashSet<>();
        HashSet<Integer> cpp  = new HashSet<>();
        
        HashSet<Integer> backend  = new HashSet<>();
        HashSet<Integer> frontend  = new HashSet<>();
        
        HashSet<Integer> junior  = new HashSet<>();
        HashSet<Integer> senior  = new HashSet<>();
        
        HashSet<Integer> chicken  = new HashSet<>();
        HashSet<Integer> pizza  = new HashSet<>();
        for(int idx=0;idx<info.length;idx++){
            String[] i = info[idx].split(" ");
            if(i[0].equals("java")) java.add(idx);
            else if(i[0].equals("python")) python.add(idx);
            else if(i[0].equals("cpp"))cpp.add(idx);
            
            if(i[1].equals("backend")) backend.add(idx);
            else if(i[1].equals("frontend"))frontend.add(idx);
            
            if(i[2].equals("junior")) junior.add(idx);
            else if(i[2].equals("senior"))senior.add(idx);
            
            if(i[3].equals("chicken")) chicken.add(idx);
            else if(i[3].equals("pizza"))pizza.add(idx);
            
            scores[idx] = Integer.parseInt(i[4]);
        }
        for(int idx=0;idx<query.length;idx++){
            String s = query[idx];
            String[] q =s.split(" ");
            
            HashSet<Integer> all = new HashSet<>();
            for(int i=0;i<info.length;i++){
                if(scores[i]>=Integer.parseInt(q[q.length-1])){
                    all.add(i);
                }
            }
            for(String k:q){
                if(k.equals("java"))all.retainAll(java);
                else if(k.equals("python"))all.retainAll(python);
                else if(k.equals("cpp"))all.retainAll(cpp);
                else if(k.equals("backend"))all.retainAll(backend);
                else if(k.equals("frontend"))all.retainAll(frontend);
                else if(k.equals("junior"))all.retainAll(junior);
                else if(k.equals("senior"))all.retainAll(senior);
                else if(k.equals("pizza"))all.retainAll(pizza);
                else if(k.equals("chicken"))all.retainAll(chicken);
            }
            answer[idx]=all.size();
        }
        return answer;
    }
}