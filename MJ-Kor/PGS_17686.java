package algorithm_study.april.day_04_20;

import java.util.*;

class PGS_17686 {
    
    private static class File implements Comparable<File>{
        String head, original, lower;
        int number;
        
        public File(String fileName) {
            this.original = fileName;
            this.lower = fileName.toLowerCase();
            extract(lower);
        }
        
        public void extract(String fileName) {
            int numStartIdx = extractHead(fileName);
            int tailStartIdx = extractNumber(fileName, numStartIdx);
        }
        
        public int extractHead(String fileName) {
            StringBuilder sb = new StringBuilder();
            int idx = 0;
            char nowChar = fileName.charAt(idx);
            while(!Character.isDigit(nowChar)) {
                sb.append(nowChar);
                if(idx == (fileName.length() - 1)) break;
                nowChar = fileName.charAt(++idx);
            }
            this.head = sb.toString();
            return idx;
        }
        
        public int extractNumber(String fileName, int idx) {
            StringBuilder sb = new StringBuilder();
            char nowChar = fileName.charAt(idx);
            while(Character.isDigit(nowChar)) {
                sb.append(nowChar);
                if(idx == (fileName.length() - 1)) break;
                nowChar = fileName.charAt(++idx);
            }
            this.number = Integer.parseInt(sb.toString());
            return idx;
        }
                  
        @Override
        public int compareTo(File o) {

            int comp = this.head.compareTo(o.head);
            
            if(comp == 0) {
                return this.number - o.number;
            } else {
                return comp;
            }
        }
        
    } 
    
    public String[] solution(String[] files) {
        int N = files.length;
        String[] answer = new String[N];
        List<File> fileList = new LinkedList<>();
        for(String str : files) {
            fileList.add(new File(str));
        }
        Collections.sort(fileList);
        for(int i = 0; i < N; i++) {
            answer[i] = fileList.get(i).original;
        }
        return answer;
    }
}